package com.bjit.salon.auth.service.exceptions;

public class UsernameAlreadyTakenException extends RuntimeException{

    public UsernameAlreadyTakenException(String message){
        super(message);
    }
}
