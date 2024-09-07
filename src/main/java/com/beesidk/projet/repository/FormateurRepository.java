package com.beesidk.projet.repository;

import com.beesidk.projet.entity.Formateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormateurRepository extends MongoRepository<Formateur, String> {
}
