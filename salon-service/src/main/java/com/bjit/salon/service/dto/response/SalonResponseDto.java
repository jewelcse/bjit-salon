package com.bjit.salon.service.dto.response;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SalonResponseDto {
    private long id;
    private String name;
    private String description;
    private String address;
    private long userId;
    private double reviews;
    private Timestamp openingTime;
    private Timestamp closingTime;
    private String contractNumber;
}
