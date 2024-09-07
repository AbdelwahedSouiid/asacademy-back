package com.beesidk.projet.repository;

import com.beesidk.projet.entity.Categorie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategorieRepository extends MongoRepository<Categorie, String> {
    Categorie findByNom(String nom);
}

