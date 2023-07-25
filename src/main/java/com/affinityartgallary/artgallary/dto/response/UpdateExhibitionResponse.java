package com.affinityartgallary.artgallary.dto.response;

import lombok.Data;

@Data
public class UpdateExhibitionResponse {
    private String id;
    private String exhibitionName;
    private final String message = "updated successfully";
}
