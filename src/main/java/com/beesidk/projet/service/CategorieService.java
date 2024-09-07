package com.beesidk.projet.service;

import com.beesidk.projet.entity.Categorie;
import com.beesidk.projet.entity.Cour;
import com.beesidk.projet.interfaces.IService;
import com.beesidk.projet.repository.CategorieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CategorieService implements IService<Categorie> {

    private CategorieRepository repo;

    @Override
    public List<Categorie> retrieveAll() {
        return repo.findAll();
    }

    @Override
    public Categorie retrieve(String id) {


        return repo.findById(id).orElse(null);
    }


    @Override
    public Categorie add(Categorie Categorie) {
        return repo.save(Categorie);
    }

    @Override
    public void remove(String id) {
        Categorie Categorie = repo.findById(id).orElse(null);

        if (Categorie != null) {
            List<Cour> cours = Categorie.getCours();
            for (Cour cour : cours) {
                cour.setCategorie(null);
            }
            repo.delete(Categorie);
        }

    }

    @Override
    public Categorie modify(Categorie Categorie) {
        return repo.save(Categorie);
    }
}
