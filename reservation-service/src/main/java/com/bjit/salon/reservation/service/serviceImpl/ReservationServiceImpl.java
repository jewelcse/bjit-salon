package com.bjit.salon.reservation.service.serviceImpl;


import com.bjit.salon.reservation.service.dto.producer.StaffActivityCreateDto;
import com.bjit.salon.reservation.service.dto.request.CatalogRequest;
import com.bjit.salon.reservation.service.dto.request.ReservationCreateDto;
import com.bjit.salon.reservation.service.dto.request.ReservationStartsDto;
import com.bjit.salon.reservation.service.dto.response.ReservationResponseDto;
import com.bjit.salon.reservation.service.entity.Catalog;
import com.bjit.salon.reservation.service.entity.EWorkingStatus;
import com.bjit.salon.reservation.service.entity.Reservation;
import com.bjit.salon.reservation.service.exception.ReservationNotFoundException;
import com.bjit.salon.reservation.service.exception.ReservationTimeOverlapException;
import com.bjit.salon.reservation.service.exception.StaffAlreadyEngagedException;
import com.bjit.salon.reservation.service.mapper.ReservationMapper;
import com.bjit.salon.reservation.service.producer.ReservationProducer;
import com.bjit.salon.reservation.service.repository.CatalogRepository;
import com.bjit.salon.reservation.service.repository.ReservationRepository;
import com.bjit.salon.reservation.service.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bjit.salon.reservation.service.util.MethodsUtil.minutesToLocalTime;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CatalogRepository catalogRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationProducer reservationProducer;

    @Override
    public void createReservation(ReservationCreateDto reservationCreateDto) {
        if (reservationCreateDto.getEndTime().isAfter(reservationCreateDto.getStartTime())) {
            List<Reservation> currentReservationByStaff = reservationRepository
                    .findAllByStaffIdAndReservationDate(reservationCreateDto.getStaffId(), reservationCreateDto.getReservationDate());
            if (currentReservationByStaff.size() == 0) {
                // if there are no reservation in this date,
                //  no need to check. create a new reservation on this day
                saveReservation(reservationCreateDto);
            } else {
                // getting the lastCompletedOrProcessing reservation bcz, before that time
                // there is no possible to make any reservation
                Optional<Reservation> lastCompletedOrProcessingReservation = currentReservationByStaff
                        .stream().filter(item -> item.getWorkingStatus().equals(EWorkingStatus.COMPLETED)
                                || item.getWorkingStatus().equals(EWorkingStatus.PROCESSING))
                        .reduce((first, second) -> second);

                if (lastCompletedOrProcessingReservation.isPresent()) {
                    if (reservationCreateDto.getStartTime().isAfter(lastCompletedOrProcessingReservation.get().getEndTime())) {
                        // now have to check the new reservation with the advanced(if it has any INITIATED reservations)
                        //  if no INITIATED reservation are there, so create new reservation else
                        //  check with the existing INITIATED reservations, if you have any available slot then create new reservation
                        List<Reservation> initiatedReservations = getInitiatedReservations(currentReservationByStaff);
                        if (initiatedReservations.size() == 0) {
                            // means there are no advanced reservations, so create reservation
                            saveReservation(reservationCreateDto);
                        } else {
                            // have to compare with the all INITIATED reservations
                            checkingReservation(reservationCreateDto, initiatedReservations);
                        }
                    } else {
                        throw new ReservationTimeOverlapException("Invalid Time for reservation: start time should be after, all previous completed or processing work!");
                    }
                } else {
                    // if no completed or processing work, then obviously all are initiative work list
                    List<Reservation> initiatedReservations = getInitiatedReservations(currentReservationByStaff);
                    checkingReservation(reservationCreateDto, initiatedReservations);
                }
            }
        } else {
            throw new ReservationTimeOverlapException("Invalid Time: end time must be greater than start time");
        }
    }

    private List<Reservation> getInitiatedReservations(List<Reservation> currentReservationByStaff) {
        return currentReservationByStaff
                .stream().filter(reservation -> reservation.getWorkingStatus().equals(EWorkingStatus.INITIATED))
                .collect(Collectors.toList());

    }

    private void checkingReservation(ReservationCreateDto reservationCreateDto, List<Reservation> initiatedReservations) {
        Optional<Reservation> hasPreviousReservations = initiatedReservations
                .stream().filter(reservation -> reservation.getEndTime().isBefore(reservationCreateDto.getStartTime()))
                .reduce((item1, item2) -> item2); // get the immediate previous

        Optional<Reservation> hasNextReservations = initiatedReservations
                .stream().filter(reservation -> reservation.getStartTime().isAfter(reservationCreateDto.getEndTime()))
                .reduce((item1, item2) -> item2); // get the immediate next

        if (hasPreviousReservations.isPresent() && hasNextReservations.isPresent()) {
            saveReservation(reservationCreateDto);
        }else if(hasPreviousReservations.isPresent()){ // if no hasNextReservation
            saveReservation(reservationCreateDto);
        }else if(hasNextReservations.isPresent()){ // if no hasPreviousReservation
            saveReservation(reservationCreateDto);
        } else {
            throw new ReservationTimeOverlapException("Invalid Time overlapping...");
        }
    }

    private void saveReservation(ReservationCreateDto reservationCreateDto) {
        boolean alreadyHasReservation = reservationRepository
                .existsByStartTimeAndEndTime(reservationCreateDto.getStartTime(), reservationCreateDto.getEndTime());
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
                .services(reservationMapper.toCatalogs(reservationCreateDto.getServices()))
                .totalPayableAmount(totalPayableAmount)
                .build();
        reservationRepository.save(newReservation);

    }

    @Override
    public List<ReservationResponseDto> getAllReservationByStaff(long id) {
        // todo: set the services to the response
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

        StaffActivityCreateDto newActivity = StaffActivityCreateDto
                .builder()
                .staffId(currentReservation.get().getStaffId())
                .consumerId(currentReservation.get().getConsumerId())
                .reservationId(currentReservation.get().getId())
                .startTime(currentReservation.get().getStartTime())
                .endTime(currentReservation.get().getEndTime())
                .workingDate(currentReservation.get().getReservationDate())
                .workingStatus(currentReservation.get().getWorkingStatus())
                .build();

        reservationProducer.createNewActivity(newActivity);

    }


}
