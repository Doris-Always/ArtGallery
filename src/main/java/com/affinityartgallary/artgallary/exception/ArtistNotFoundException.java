package com.affinityartgallary.artgallary.exception;

import java.util.function.Supplier;

public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(String message) {
        super(message);
    }


}