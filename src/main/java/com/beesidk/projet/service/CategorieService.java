package com.beesidk.projet.service;

import com.beesidk.projet.entity.Categorie;
import com.beesidk.projet.entity.Categorie;
import com.beesidk.projet.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CategorieService implements IService<Categorie> {

    private CategoryRepository repo;

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
        repo.deleteById(id);
    }

    @Override
    public Categorie modify(Categorie Categorie) {
        return repo.save(Categorie);
    }
}
