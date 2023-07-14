package com.affinityartgallary.artgallary.data.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Builder
@Document
@Data
public class Artist {
    @Id
    private String id;
    private String name;
    private String yearOfBirth;
    private String artistBio;
    private String imageUrl;
    private Category category;
    private List<ArtWork> artWorkList;
    private List<Exhibition> exhibitions;
}
