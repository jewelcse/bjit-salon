package com.bjit.salon.service.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CatalogResponseDto {

    private long id;
    private String name;
    private String description;
    private int approximateTimeForCompletion; // IN MINUTES
    private double payableAmount;
}
