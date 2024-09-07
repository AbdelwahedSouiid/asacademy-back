package com.beesidk.projet.repository;

import com.beesidk.projet.entity.AppUser;
import com.beesidk.projet.entity.Avis;
import com.beesidk.projet.entity.Cour;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvisRepository extends MongoRepository<Avis, String> {
    boolean existsAvisByCourAndUser(Cour cour, AppUser appUser);

    List<Avis> findAvisByCour(Cour cour);
}
