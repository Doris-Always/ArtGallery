package com.affinityartgallary.artgallary.data.repository;

import com.affinityartgallary.artgallary.data.model.News;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends MongoRepository<News,String> {
    Optional<News> findByTitle(String title);
}
