package com.affinityartgallary.artgallary.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class ArtWork {
    @Id
    private String id;
    private String name;
    private String year;
    private String dimension;
    private String imageUrl;
    private String additionalInfo;
}
