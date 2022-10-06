package com.bjit.salon.reservation.service.mapper;

import com.bjit.salon.reservation.service.dto.response.ReservationResponseDto;
import com.bjit.salon.reservation.service.entity.Reservation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationResponseDto toReservationResponse(Reservation reservation);
    List<ReservationResponseDto> toReservationResponseList(List<Reservation> reservations);



}