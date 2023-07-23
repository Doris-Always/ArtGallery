package com.affinityartgallary.artgallary.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Setter
@Getter

public class AddArtWorkRequest {
    private String title;
    private String medium;
    private String dimension;
    private MultipartFile image;


    public AddArtWorkRequest(String title, String medium, String dimension, MultipartFile image) {
        this.title = title;
        this.medium = medium;
        this.dimension = dimension;
        this.image = image;
    }
}
