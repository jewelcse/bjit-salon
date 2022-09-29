package com.bjit.salon.service.mapper;


import com.bjit.salon.service.dto.request.SalonCreateDto;
import com.bjit.salon.service.dto.request.SalonUpdateDto;
import com.bjit.salon.service.dto.response.SalonResponseDto;
import com.bjit.salon.service.entiry.Salon;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalonMapper {

    Salon toSalon(SalonCreateDto salonCreateDto);
    Salon toSalon(SalonUpdateDto salonUpdateDto);
    SalonResponseDto toSalonResponse(Salon salon);
    List<SalonResponseDto> toListOfSalonResponseDto(List<Salon> salons);
}
