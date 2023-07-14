package com.affinityartgallary.artgallary.dto.request;

import lombok.Data;

@Data
public class UpdateArtWorkRequest {
    private String title;
    private String medium;
    private String dimension;
    private String imageUrl;
}
