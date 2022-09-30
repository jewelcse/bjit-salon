package com.bjit.salon.salon.staff.service.ag.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class SalonDetailsDto {

    private SalonResponseDto salon;
    private List<StaffResponseDto> staffs;

}
