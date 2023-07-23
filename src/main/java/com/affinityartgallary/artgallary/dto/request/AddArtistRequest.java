package com.affinityartgallary.artgallary.dto.request;

import com.affinityartgallary.artgallary.data.model.Category;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddArtistRequest {
    private String name;
    private String yearOfBirth;
    private String artistBio;
    private MultipartFile image;
//    private String category;

}
