package com.bjit.salon.service.exception;

public class SalonNotFoundException extends RuntimeException {

    public SalonNotFoundException(String message){
        super(message);
    }
}
