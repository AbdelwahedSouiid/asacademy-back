package com.beesidk.projet.repository;

import com.beesidk.projet.entity.Categorie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends MongoRepository<Categorie, String> {
}
