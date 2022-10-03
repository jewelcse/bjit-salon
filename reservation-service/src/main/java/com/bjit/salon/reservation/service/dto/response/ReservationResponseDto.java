package com.bjit.salon.reservation.service.dto.response;

import com.bjit.salon.reservation.service.entity.EPaymentMethod;
import com.bjit.salon.reservation.service.entity.EWorkingStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ReservationResponseDto {

    private long staffId;   // staff id
    private long consumerId; // user id
    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private EPaymentMethod paymentMethod;
    private EWorkingStatus workingStatus;
}
