package com.beesidk.projet.service;

import com.beesidk.projet.entity.Reclamation;
import com.beesidk.projet.interfaces.IService;
import com.beesidk.projet.repository.ReclamationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReclamationService implements IService<Reclamation> {
    private ReclamationRepository repo;
    private FastApiService fastApiService;

    @Override
    public List<Reclamation> retrieveAll() {
        return repo.findAll();
    }

    @Override
    public Reclamation retrieve(String id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Reclamation add(Reclamation reclamation) {
        // Appeler le service FastAPI pour prédire si la réclamation est du spam
        boolean isSpam = fastApiService.isSpam(reclamation).block();  // Reclamation entière passée à FastAPI

        // Mettre à jour le statut de spam dans l'objet Reclamation
        reclamation.setSpam(isSpam);

        // Sauvegarder la réclamation dans le repository
        return repo.save(reclamation);
    }


    @Override
    public void remove(String id) {
        repo.deleteById(id);
    }

    @Override
    public Reclamation modify(Reclamation reclamation) {
        return repo.save(reclamation);
    }
}
