package com.affinityartgallary.artgallary.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter

public class AddArtWorkRequest {
    private String title;
    private String medium;
    private String dimension;
    private String imageUrl;


    public AddArtWorkRequest(String title, String medium, String dimension, String imageUrl) {
        this.title = title;
        this.medium = medium;
        this.dimension = dimension;
        this.imageUrl = imageUrl;
    }
}
