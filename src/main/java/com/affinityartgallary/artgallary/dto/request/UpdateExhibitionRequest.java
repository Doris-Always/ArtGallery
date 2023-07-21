package com.affinityartgallary.artgallary.dto.request;

import lombok.Data;

@Data
public class UpdateExhibitionRequest {
    private String imageUrl;
    private String exhibitionName;
    private String year;
    private String location;
}
