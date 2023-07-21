package com.affinityartgallary.artgallary.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateNewsRequest{
        private String title;
        private String body;
        private Date date;
        private String imageUrl;
}
