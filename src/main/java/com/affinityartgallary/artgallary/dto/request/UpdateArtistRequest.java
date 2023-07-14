package com.affinityartgallary.artgallary.dto.request;

import com.affinityartgallary.artgallary.data.model.Category;
import lombok.Data;

@Data
public class UpdateArtistRequest {
    private String name;
    private String yearOfBirth;
    private String artistBio;
    private String imageUrl;
    private Category category;
}
