package com.affinityartgallary.artgallary.data.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Builder
@Document
@Data
public class Exhibition {
    @Id
    private String id;
    private String imageUrl;
    private String exhibitionName;
    private String year;
    private String location;
    private String artistId;

}
