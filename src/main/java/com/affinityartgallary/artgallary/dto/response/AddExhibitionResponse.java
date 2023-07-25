package com.affinityartgallary.artgallary.dto.response;

import lombok.Data;

@Data
public class AddExhibitionResponse {
    private String id;
    private String artistId;
    private String exhibitionName;
    private final String message ="added successfully";
}
