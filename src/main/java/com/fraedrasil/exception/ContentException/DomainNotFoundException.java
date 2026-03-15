package com.fraedrasil.exception.ContentException;

public class DomainNotFoundException extends RuntimeException{
    public DomainNotFoundException(String message){
        super(message);
    }
}
