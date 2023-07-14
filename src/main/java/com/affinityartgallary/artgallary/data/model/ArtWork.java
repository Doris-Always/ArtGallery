package com.affinityartgallary.artgallary.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtWork {
    @Id
    private String id;
    private String title;
    private String medium;
    private String dimension;
    private String imageUrl;
    private String artistId;
}
