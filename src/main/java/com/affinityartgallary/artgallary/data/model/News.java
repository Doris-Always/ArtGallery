package com.affinityartgallary.artgallary.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class News {
    @Id
    private String id;
    private String title;
    private Date date;
    private String imageUrl;
}
