package com.bjit.salon.reservation.service.controller;


import com.bjit.salon.reservation.service.dto.ReservationCreateDto;
import com.bjit.salon.reservation.service.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.bjit.salon.reservation.service.util.ConstraintsUtil.RESERVATION_SERVICE_APPLICATION_BASE_API;


@RequiredArgsConstructor
@RestController
@RequestMapping(RESERVATION_SERVICE_APPLICATION_BASE_API)
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reservations")
    public ResponseEntity<String> makeReservation(@RequestBody ReservationCreateDto reservationCreateDto) {
        reservationService.createReservation(reservationCreateDto);
        return ResponseEntity.ok("Reservation created success");
    }


}
