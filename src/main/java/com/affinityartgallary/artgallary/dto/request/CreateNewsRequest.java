package com.affinityartgallary.artgallary.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class CreateNewsRequest {
    private String title;
    private String body;
    private MultipartFile image;
}
