package com.beesidk.projet.service;

import com.beesidk.projet.entity.Inscription;
import com.beesidk.projet.repository.InscriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class InscriptionService implements IService<Inscription> {

    private InscriptionRepository repo;


    @Override
    public List<Inscription> retrieveAll() {
        return repo.findAll();
    }

    @Override
    public Inscription retrieve(String id) {
        return repo.findById(id).orElse(null);
    }


    @Override
    public Inscription add(Inscription Inscription) {
        return repo.save(Inscription);
    }

    @Override
    public void remove(String id) {
        repo.deleteById(id);
    }

    @Override
    public Inscription modify(Inscription Inscription) {
        return repo.save(Inscription);
    }
}
