package com.affinityartgallary.artgallary.data.repository;

import com.affinityartgallary.artgallary.data.model.ArtWork;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtWorkRepository extends MongoRepository<ArtWork,String> {
}
