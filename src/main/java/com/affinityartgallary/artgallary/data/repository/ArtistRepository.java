package com.affinityartgallary.artgallary.data.repository;

import com.affinityartgallary.artgallary.data.model.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends MongoRepository<Artist,String> {
    Artist findByName(String name);
}



