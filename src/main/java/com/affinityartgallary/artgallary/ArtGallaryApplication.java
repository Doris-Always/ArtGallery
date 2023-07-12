package com.affinityartgallary.artgallary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ArtGallaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtGallaryApplication.class, args);
    }

}
