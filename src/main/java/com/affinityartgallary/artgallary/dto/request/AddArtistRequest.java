package com.affinityartgallary.artgallary.dto.request;

import lombok.Data;

@Data
public class AddArtistRequest {
    private String name;
    private String yearOfBirth;
    private String artistBio;
    private String imageUrl;

}
