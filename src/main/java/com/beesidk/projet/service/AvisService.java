package com.beesidk.projet.service;


import com.beesidk.projet.entity.AppUser;
import com.beesidk.projet.entity.Avis;
import com.beesidk.projet.entity.Cour;
import com.beesidk.projet.interfaces.IAvisService;
import com.beesidk.projet.interfaces.IService;
import com.beesidk.projet.repository.AppUserRepository;
import com.beesidk.projet.repository.AvisRepository;
import com.beesidk.projet.repository.CourRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AvisService implements IAvisService {

    public AvisRepository repo;
    public CourRepository courRepository;
    public AppUserRepository userRepository;

    @Override
    public List<Avis> retrieveAll() {
        return repo.findAll();
    }

    @Override
    public Avis retrieve(String id) {
        return repo.findById(id).orElse(null);
    }


    @Override
    public Avis add(Avis avis) {
        Cour cour = avis.getCour();
        AppUser user = avis.getUser();
        if (repo.existsAvisByCourAndUser(cour, user)) {
            return avis;
        } else {
            avis.setDateAvis(LocalDateTime.now());
            Avis savedAvis = repo.save(avis);
            cour.getAvis().add(savedAvis);
            user.getAvis().add(savedAvis);
            courRepository.save(cour);
            userRepository.save(user);
            return savedAvis;
        }

    }

    @Override
    public void remove(String id) {
        repo.deleteById(id);
    }

    @Override
    public Avis modify(Avis Avis) {
        return repo.save(Avis);
    }


    @Override
    public List<Avis> getAvisByCour(String courId) {

        Cour Cour = courRepository.findById(courId).get();
        return repo.findAvisByCour(Cour);
    }
}
