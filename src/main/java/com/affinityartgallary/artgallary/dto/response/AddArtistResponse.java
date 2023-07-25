package com.affinityartgallary.artgallary.dto.response;

import lombok.Data;

@Data
public class AddArtistResponse {
    private String id;
    private String name;
    private final String message = "added successfully";

}
