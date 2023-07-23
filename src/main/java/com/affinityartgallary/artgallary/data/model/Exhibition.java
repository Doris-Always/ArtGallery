package com.affinityartgallary.artgallary.data.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Document
@Data
public class Exhibition {
    @Id
    private String id;
    private String image;
    private String exhibitionName;
    private String year;
    private String location;
    private String artistId;

}
