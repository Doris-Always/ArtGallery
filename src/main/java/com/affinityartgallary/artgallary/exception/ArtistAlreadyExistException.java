package com.affinityartgallary.artgallary.exception;

public class ArtistAlreadyExistException extends RuntimeException {
    public ArtistAlreadyExistException(String message) {
        super(message);
    }
}
