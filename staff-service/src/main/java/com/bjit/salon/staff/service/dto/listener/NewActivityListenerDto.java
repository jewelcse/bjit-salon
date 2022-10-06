package com.bjit.salon.staff.service.dto.listener;

import com.bjit.salon.staff.service.entity.EWorkingStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class NewActivityListenerDto {

    private long staffId;
    private long consumerId;
    private LocalDate workingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private EWorkingStatus workingStatus;
}
