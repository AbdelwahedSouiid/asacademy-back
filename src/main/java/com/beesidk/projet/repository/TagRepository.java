package com.beesidk.projet.repository;

import com.beesidk.projet.entity.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends MongoRepository<Tag, String> {

    List<Tag> findAllByName(String name);

}
