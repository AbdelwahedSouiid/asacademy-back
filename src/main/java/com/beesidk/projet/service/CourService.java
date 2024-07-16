package com.beesidk.projet.service;

import com.beesidk.projet.entity.Cour;
import com.beesidk.projet.repository.CourRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CourService implements IService<Cour> {

    public CourRepository repo;


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
        return repo.save(cour);
    }

    @Override
    public void remove(String id) {
        repo.deleteById(id);
    }

    @Override
    public Cour modify(Cour cour) {
        return repo.save(cour);
    }
}
