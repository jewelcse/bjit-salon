package com.bjit.salon.service.exception;

public class SalonNameAlreadyTakenException extends RuntimeException {

    public SalonNameAlreadyTakenException(String message){
        super(message);
    }
}
