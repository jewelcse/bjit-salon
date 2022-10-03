package com.bjit.salon.reservation.service.dto.request;


import com.bjit.salon.reservation.service.entity.EPaymentMethod;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ReservationCreateDto {

    private long staffId;   // staff id
    private long consumerId; // user id
    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private EPaymentMethod paymentMethod; // payment method
}
