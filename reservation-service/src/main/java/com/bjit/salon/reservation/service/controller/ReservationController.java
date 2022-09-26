package com.bjit.salon.reservation.service.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reservations/api/v1")
public class ReservationController {

    @GetMapping("/hi")
    public String sayHi(){
        return "Hi, from reservation service!";
    }
}
