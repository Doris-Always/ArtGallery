package com.affinityartgallary.artgallary.exception;

public class NewsAlreadyExistException extends RuntimeException{
    public NewsAlreadyExistException(String message){
        super(message);
    }
}
