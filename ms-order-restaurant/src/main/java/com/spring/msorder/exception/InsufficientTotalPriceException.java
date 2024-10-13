package com.spring.msorder.exception;

public class InsufficientTotalPriceException extends RuntimeException{
    public InsufficientTotalPriceException(String message){
        super(message);
    }
}
