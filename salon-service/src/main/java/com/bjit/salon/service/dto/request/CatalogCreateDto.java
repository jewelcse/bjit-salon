package com.bjit.salon.service.dto.request;

import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CatalogCreateDto {

    private String name;
    private String description;
    private int approximateTimeForCompletion; // IN MINUTES
    private double payableAmount;
}
