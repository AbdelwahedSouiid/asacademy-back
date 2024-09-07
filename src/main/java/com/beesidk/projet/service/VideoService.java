package com.beesidk.projet.service;


import com.beesidk.projet.entity.Categorie;
import com.beesidk.projet.entity.Video;
import com.beesidk.projet.interfaces.IVideoService;
import com.beesidk.projet.repository.CategorieRepository;
import com.beesidk.projet.repository.VideoRepository;
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
public class VideoService implements IVideoService {
    private MongoTemplate mongoTemplate;

    private VideoRepository repo;
    private CategorieRepository categorieRepository;

    @Override
    public List<Video> retrieveAll() {
        return repo.findAll();
    }

    @Override
    public Video retrieve(String id) {
        return repo.findById(id).orElse(null);
    }


    @Override
    public Video add(Video video) {
        video.setDateAjout(LocalDateTime.now());
        return repo.save(video);
    }

    @Override
    public void remove(String id) {
        repo.deleteById(id);
    }

    @Override
    public Video modify(Video Video) {
        return repo.save(Video);
    }


    @Override
    public List<Video> search(String titre, String category) {
        Query query = new Query();

        if (titre != null && !titre.isEmpty()) {
            query.addCriteria(Criteria.where("titre").regex(titre, "i"));
        }

        if (category != null && !category.isEmpty()) {
            Categorie categorie = categorieRepository.findByNom(category);
            if (categorie != null) {
                String categorieId = categorie.getId();
                if (categorieId != null && !categorieId.isEmpty() && ObjectId.isValid(categorieId)) {
                    query.addCriteria(Criteria.where("categorie.$id").is(new ObjectId(categorieId)));
                }
            }
        }

        return mongoTemplate.find(query, Video.class);
    }

}
