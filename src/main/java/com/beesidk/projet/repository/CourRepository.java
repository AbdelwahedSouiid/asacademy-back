package com.beesidk.projet.repository;


import com.beesidk.projet.entity.Cour;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourRepository extends MongoRepository<Cour, String> {
}
