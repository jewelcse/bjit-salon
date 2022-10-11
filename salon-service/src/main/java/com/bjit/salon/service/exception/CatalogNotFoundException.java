package com.bjit.salon.service.exception;

public class CatalogNotFoundException extends RuntimeException {

    public CatalogNotFoundException(String message){
        super(message);
    }
}
