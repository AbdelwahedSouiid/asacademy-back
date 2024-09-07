package com.beesidk.projet.repository;


import com.beesidk.projet.entity.Cour;
import com.beesidk.projet.entity.Tag;
import com.beesidk.projet.enums.PaiementType;
import com.mongodb.DBRef;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface CourRepository extends MongoRepository<Cour, String> {
    List<Cour> findByDateCreationBetween(LocalDateTime from, LocalDateTime to);

    List<Cour> findByDureeLessThan(int duree);

    List<Cour> findByPrixLessThan(float price);

    /*
    @Query("{ " +
            "'name': { $regex: ?0, $options: 'i' }, " +
            "'categorie.$id': ObjectId(?1), " +
            "'paiement': ?2 " +
            "}")
    List<Cour> searchCours(String name, String categorie, PaiementType paiementType);
*/

}
