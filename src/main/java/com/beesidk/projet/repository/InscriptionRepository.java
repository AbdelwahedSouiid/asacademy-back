package com.beesidk.projet.repository;

import com.beesidk.projet.entity.Inscription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionRepository extends MongoRepository<Inscription, String> {
}

