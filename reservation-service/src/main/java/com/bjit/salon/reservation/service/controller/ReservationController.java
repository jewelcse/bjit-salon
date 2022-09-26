package com.bjit.salon.reservation.service.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bjit.salon.reservation.service.util.ConstraintsUtil.RESERVATION_SERVICE_APPLICATION_BASE_API;

@RestController
@RequestMapping(RESERVATION_SERVICE_APPLICATION_BASE_API)
public class ReservationController {

    @GetMapping("/hi")
    public String sayHi(){
        return "Hi, from reservation service!";
    }
}
