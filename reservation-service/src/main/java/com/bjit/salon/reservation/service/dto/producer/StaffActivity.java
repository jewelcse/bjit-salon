package com.bjit.salon.reservation.service.dto.producer;

import com.bjit.salon.reservation.service.entity.EPaymentMethod;
import com.bjit.salon.reservation.service.entity.EWorkingStatus;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class StaffActivity {
    private long staffId;
    private long consumerId;
    private LocalDate workingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private EWorkingStatus workingStatus;
}