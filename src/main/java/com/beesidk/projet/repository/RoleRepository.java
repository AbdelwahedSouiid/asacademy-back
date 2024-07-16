package com.beesidk.projet.repository;

import com.beesidk.projet.entity.AppRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends MongoRepository<AppRole, String> {

    Optional<AppRole> findByName(String nom);
}
