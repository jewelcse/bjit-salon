package com.bjit.salon.staff.service.dto.response;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class StaffResponseDto {
    private String address;
    private long salonId;
    private long userId;
    private boolean isAvailable;
    private String contractNumber;
    private double salary;
    private String employeementDate;
    private String employeementType;
}
