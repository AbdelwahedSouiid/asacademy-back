package com.beesidk.projet.repository;


import com.beesidk.projet.entity.Message;
import com.beesidk.projet.entity.Reclamation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamationRepository extends MongoRepository<Reclamation, String> {
}
