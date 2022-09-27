package com.bjit.salon.auth.service.exceptions;

public class UserEmailAlreadyTakenException extends RuntimeException{

    public UserEmailAlreadyTakenException(String message){
        super(message);
    }
}
