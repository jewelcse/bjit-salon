package com.bjit.salon.reservation.service.service;

import com.bjit.salon.reservation.service.dto.request.ReservationCreateDto;

public interface ReservationService {
    void createReservation(ReservationCreateDto reservationCreateDto);

    boolean isStaffAvailableAndFree();
}
