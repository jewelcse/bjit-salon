package com.bjit.salon.reservation.service.dto.request;


import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class StaffActivityCheckDto {

    private long staffId;
    private Timestamp reservationDate;
    private Timestamp startTime;
}
