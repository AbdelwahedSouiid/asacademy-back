package com.beesidk.projet.service;

import com.beesidk.projet.entity.*;
import com.beesidk.projet.enums.PaiementType;
import com.beesidk.projet.interfaces.ICourService;

import com.beesidk.projet.repository.*;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;


@Service
@AllArgsConstructor
public class CourService implements ICourService {

    private MongoTemplate mongoTemplate;

    private final AvisRepository avisRepository;
    private final InscriptionRepository inscriptionRepository;
    public CourRepository repo;
    public CategorieRepository categorieRepo;
    public TagRepository tagRepo;

    @Override
    public List<Cour> retrieveAll() {
        return repo.findAll();
    }

    @Override
    public Cour retrieve(String id) {
        return repo.findById(id).orElse(null);
    }


    @Override
    public Cour add(Cour cour) {
        List<Tag> tags = cour.getTags();
        cour.setDateCreation(LocalDateTime.now());
        return repo.save(cour);
    }

    @Override
    public void remove(String id) {
        Cour cour = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Delete all associated avis and inscriptions
        List<Avis> avisToDelete = cour.getAvis();
        List<Inscription> inscriptionsToDelete = cour.getInscriptions();
        if (avisToDelete != null && !avisToDelete.isEmpty()) {
            avisRepository.deleteAll(avisToDelete);
        }
        if (inscriptionsToDelete != null && !inscriptionsToDelete.isEmpty()) {
            inscriptionRepository.deleteAll(inscriptionsToDelete);
        }
        // Now delete the course
        repo.deleteById(id);
    }

    @Override
    public Cour modify(Cour cour) {
        return repo.save(cour);
    }


    @Override
    public List<Cour> searchByDateBetween(LocalDateTime from, LocalDateTime to) {
        List<Cour> cours = repo.findByDateCreationBetween(from, to);
        return cours;
    }

    @Override
    public List<Cour> searchByDuration(int duree) {
        List<Cour> cours = repo.findByDureeLessThan(duree);
        return cours;
    }

    @Override
    public List<Cour> searchByPrice(float price) {
        return repo.findByPrixLessThan(price);
    }

    @Override
    public List<Cour> search(String tag, String name, String categorieNom, PaiementType paiementType) {

        // Créer un objet Query vide
        Query query = new Query();

        // Ajouter des critères conditionnellement
        if (name != null && !name.isEmpty()) {
            query.addCriteria(Criteria.where("name").regex(name, "i"));  // recherche insensible à la casse
        }


        if (categorieNom != null && !categorieNom.isEmpty()) {
            Categorie categorie = categorieRepo.findByNom(categorieNom);

            // Vérifier si la catégorie existe
            if (categorie != null) {
                String categorieId = categorie.getId();

                // Vérifier si l'ID est non nul, non vide, et un ObjectId valide
                if (categorieId != null && !categorieId.isEmpty() && ObjectId.isValid(categorieId)) {
                    query.addCriteria(Criteria.where("categorie.$id").is(new ObjectId(categorieId)));
                }
            }
        }
        if (paiementType != null) {
            query.addCriteria(Criteria.where("paiement").is(paiementType));
        }

        // Exécuter la requête avec MongoTemplate
        return mongoTemplate.find(query, Cour.class);


    }


}
