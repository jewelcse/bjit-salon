package com.bjit.salon.reservation.service.serviceImpl;

import com.bjit.salon.reservation.service.dto.producer.StaffActivity;
import com.bjit.salon.reservation.service.dto.request.CatalogRequest;
import com.bjit.salon.reservation.service.dto.request.ReservationCreateDto;
import com.bjit.salon.reservation.service.dto.request.ReservationStartsDto;
import com.bjit.salon.reservation.service.dto.response.ReservationResponseDto;
import com.bjit.salon.reservation.service.entity.EWorkingStatus;
import com.bjit.salon.reservation.service.entity.Reservation;
import com.bjit.salon.reservation.service.exception.ReservationNotFoundException;
import com.bjit.salon.reservation.service.exception.StaffAlreadyEngagedException;
import com.bjit.salon.reservation.service.mapper.ReservationMapper;
import com.bjit.salon.reservation.service.producer.ReservationProducer;
import com.bjit.salon.reservation.service.repository.ReservationRepository;
import com.bjit.salon.reservation.service.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.bjit.salon.reservation.service.util.MethodsUtil.minutesToLocalTime;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationProducer reservationProducer;

    @Override
    public void createReservation(ReservationCreateDto reservationCreateDto) {

        boolean isAdvancedReservation = false;
        boolean isAvailableAndFree = false;

        List<Reservation> currentDayReservations = reservationRepository.findAllByReservationDate(reservationCreateDto.getReservationDate());

        if (currentDayReservations.size() == 0) {
            saveReservation(reservationCreateDto);
        }else {
            isAdvancedReservation = currentDayReservations
                    .stream().anyMatch(item -> item.getWorkingStatus().equals(EWorkingStatus.INITIATED)
                            && reservationCreateDto.getEndTime().isBefore(item.getStartTime()));

            isAvailableAndFree = currentDayReservations.stream().anyMatch(
                    item -> item.getWorkingStatus().equals(EWorkingStatus.PROCESSING)
                            || item.getWorkingStatus().equals(EWorkingStatus.INITIATED)
                                    && reservationCreateDto.getStartTime().isAfter(item.getEndTime()));
        }
        if (isAdvancedReservation || isAvailableAndFree) {
            saveReservation(reservationCreateDto);
        } else {
            throw new StaffAlreadyEngagedException("The staff is not free right now, please try another time");
        }
    }

    @Override
    public List<ReservationResponseDto> getAllReservationByStaff(long id) {
        return reservationMapper.toReservationResponseList(reservationRepository.findAllByStaffId(id));
    }

    @Override
    public void startWorking(ReservationStartsDto reservationStartsDto) {
        Optional<Reservation> currentReservation = reservationRepository.findById(reservationStartsDto.getId());

        if (currentReservation.isEmpty()) {
            throw new ReservationNotFoundException("The reservation not found for id: " + reservationStartsDto.getId());
        }

        currentReservation.get().setWorkingStatus(reservationStartsDto.getStatus());
        reservationRepository.save(currentReservation.get());

        // todo: an event will be published to the staff service

        StaffActivity newActivity = StaffActivity
                .builder()
                .staffId(currentReservation.get().getStaffId())
                .consumerId(currentReservation.get().getConsumerId())
                .startTime(currentReservation.get().getStartTime())
                .endTime(currentReservation.get().getEndTime())
                .workingDate(currentReservation.get().getReservationDate())
                .workingStatus(currentReservation.get().getWorkingStatus())
                .build();

        reservationProducer.createNewActivity(newActivity);

    }

    private void saveReservation(ReservationCreateDto reservationCreateDto) {

        boolean alreadyHasReservation = reservationRepository
                .existsByStartTimeAndEndTime(reservationCreateDto.getStartTime(),reservationCreateDto.getEndTime());

        if (alreadyHasReservation){
            throw new StaffAlreadyEngagedException("The reservation has already taken");
        }
        
        double totalPayableAmount = reservationCreateDto.getServices()
                .stream().filter(service ->service.getPayableAmount() != 0.0)
                .mapToDouble(CatalogRequest::getPayableAmount).sum();
        
        int totalRequiredTime = reservationCreateDto.getServices()
                .stream().filter(service -> service.getApproximateTimeForCompletion() != 0)
                .mapToInt(CatalogRequest::getApproximateTimeForCompletion).sum();


        Reservation newReservation = Reservation.builder()
                .staffId(reservationCreateDto.getStaffId())
                .consumerId(reservationCreateDto.getConsumerId())
                .reservationDate(reservationCreateDto.getReservationDate())
                .startTime(reservationCreateDto.getStartTime())
                .endTime(minutesToLocalTime(totalRequiredTime))
                .workingStatus(EWorkingStatus.INITIATED)
                .paymentMethod(reservationCreateDto.getPaymentMethod())
                .totalPayableAmount(totalPayableAmount)
                .services(reservationMapper.toCatalogs(reservationCreateDto.getServices()))
                .build();

        System.out.println("127 line:"+newReservation.toString());
        reservationRepository.save(newReservation);
    }
}
