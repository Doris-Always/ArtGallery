package com.affinityartgallary.artgallary.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateArtWorkRequest {
    private String title;
    private String medium;
    private String dimension;
    private MultipartFile image;
}
