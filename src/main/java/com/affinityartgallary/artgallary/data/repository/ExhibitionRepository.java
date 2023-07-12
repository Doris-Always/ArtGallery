package com.affinityartgallary.artgallary.data.repository;

import com.affinityartgallary.artgallary.data.model.Exhibition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExhibitionRepository extends MongoRepository<Exhibition,String> {
}
