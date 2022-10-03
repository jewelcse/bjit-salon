package com.bjit.salon.reservation.service.serviceImpl;

import com.bjit.salon.reservation.service.dto.request.ReservationCreateDto;
import com.bjit.salon.reservation.service.entity.EWorkingStatus;
import com.bjit.salon.reservation.service.entity.Reservation;
import com.bjit.salon.reservation.service.exception.StaffAlreadyEngagedException;
import com.bjit.salon.reservation.service.repository.ReservationRepository;
import com.bjit.salon.reservation.service.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    @Override
    public void createReservation(ReservationCreateDto reservationCreateDto) {

        // todo: perform rest call for getting the staff activity
        //  for the particular date for the staff
        // todo: compare the time if it is valid then create new reservation
        //  otherwise, return error message
        boolean isAvailableAndFree = true;

        if (isAvailableAndFree){
            Reservation newReservation = Reservation.builder()
                    .staffId(reservationCreateDto.getStaffId())
                    .consumerId(reservationCreateDto.getConsumerId())
                    .reservationDate(reservationCreateDto.getReservationDate())
                    .startTime(reservationCreateDto.getStartTime())
                    .endTime(reservationCreateDto.getEndTime())
                    .workingStatus(EWorkingStatus.INITIATED)
                    .paymentMethod(reservationCreateDto.getPaymentMethod())
                    .build();
            reservationRepository.save(newReservation);
        }else{
            throw new StaffAlreadyEngagedException("The staff is not free right now, please try another time");
        }
    }
}
