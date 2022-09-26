package com.bjit.salon.payment.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payments/api/v1")
public class PaymentController {

    @GetMapping("/pay")
    public String sayHi(){
        return "Hi, from payment service!";
    }
}
