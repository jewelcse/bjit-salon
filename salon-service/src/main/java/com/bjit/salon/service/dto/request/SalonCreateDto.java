package com.bjit.salon.service.dto.request;


import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SalonCreateDto {
    private long userId;
    private String name;
    private String description;
    private String address;
    private Timestamp openingTime;
    private Timestamp closingTime;
    private String contractNumber;
}
