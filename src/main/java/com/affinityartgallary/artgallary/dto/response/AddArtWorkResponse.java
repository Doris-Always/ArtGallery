package com.affinityartgallary.artgallary.dto.response;

import lombok.Data;

@Data
public class AddArtWorkResponse {
    private String id;
    private String artistId;
    private final String message =  "added successfully";
}
