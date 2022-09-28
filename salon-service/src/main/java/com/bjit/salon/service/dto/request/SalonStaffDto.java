package com.bjit.salon.service.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SalonStaffDto {

    private long userId;
    private long salonId;
}
