package com.beesidk.projet.service;

import com.beesidk.projet.entity.AppUser;
import com.beesidk.projet.entity.Cour;
import com.beesidk.projet.entity.Inscription;
import com.beesidk.projet.interfaces.InterfaceInscriptionService;
import com.beesidk.projet.repository.AppUserRepository;
import com.beesidk.projet.repository.CourRepository;
import com.beesidk.projet.repository.InscriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class InscriptionService implements InterfaceInscriptionService {

    private InscriptionRepository repo;
    private CourRepository courRepository;
    private AppUserRepository userRepository;

    @Override
    public List<Inscription> retrieveAll() {
        return repo.findAll();
    }

    @Override
    public Inscription retrieve(String id) {

        return repo.findById(id).orElse(null);
    }


    @Override
    public Inscription add(Inscription inscription) {
        Cour cour = inscription.getCour();
        AppUser user = inscription.getUser();

        if (repo.existsByUserAndCour(user, cour)) {
            return inscription;
        } else {
            Inscription savedInscription = repo.save(inscription);

            cour.getInscriptions().add(savedInscription);
            user.getInscriptions().add(inscription);

            courRepository.save(cour);
            userRepository.save(user);

            return savedInscription;
        }

    }

    @Override
    public void remove(String id) {
        repo.deleteById(id);
    }

    @Override
    public Inscription modify(Inscription Inscription) {
        return repo.save(Inscription);
    }

    @Override
    public List<Inscription> getAllInscriptionByUser(String email) {
        AppUser user = userRepository.findByEmail(email).get();
        List<Inscription> inscriptions = repo.findAllByUser(user);
        return inscriptions;
    }

    @Override
    public boolean isInscriptionExist(String userId, String courId) {
        Cour cour = courRepository.findById(courId).get();
        AppUser user = userRepository.findById(userId).get();
        boolean isInscriptionExist = repo.existsByUserAndCour(user, cour);
        return isInscriptionExist;
    }
}
