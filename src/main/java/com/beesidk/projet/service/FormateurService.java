package com.beesidk.projet.service;

import com.beesidk.projet.entity.Formateur;
import com.beesidk.projet.interfaces.IService;
import com.beesidk.projet.repository.FormateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class FormateurService implements IService<Formateur> {

    FormateurRepository repo;

    @Override
    public List<Formateur> retrieveAll() {
        return repo.findAll();
    }

    @Override
    public Formateur retrieve(String id) {
        return repo.findById(id).orElse(null);
    }


    @Override
    public Formateur add(Formateur Formateur) {
        return repo.save(Formateur);
    }

    @Override
    public void remove(String id) {
        repo.deleteById(id);
    }

    @Override
    public Formateur modify(Formateur Formateur) {
        return repo.save(Formateur);
    }
}
