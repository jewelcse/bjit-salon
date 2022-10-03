package com.bjit.salon.reservation.service.dto.request;


import com.bjit.salon.reservation.service.entity.EPaymentMethod;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ReservationCreateDto {

    private long staffId;   // staff id
    private long consumerId; // user id
    private Timestamp reservationDate;
    private Timestamp startTime; // start time
    private Timestamp endTime; // end time
    private EPaymentMethod paymentMethod; // payment method
}
