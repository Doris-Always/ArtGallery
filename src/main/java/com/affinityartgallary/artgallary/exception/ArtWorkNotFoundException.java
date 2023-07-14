package com.affinityartgallary.artgallary.exception;

public class ArtWorkNotFoundException extends RuntimeException{
    public ArtWorkNotFoundException(String message) {
        super(message);
    }
}
