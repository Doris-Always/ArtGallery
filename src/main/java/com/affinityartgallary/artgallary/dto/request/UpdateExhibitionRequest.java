package com.affinityartgallary.artgallary.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateExhibitionRequest {
    private MultipartFile image;
    private String exhibitionName;
    private String year;
    private String location;
}
