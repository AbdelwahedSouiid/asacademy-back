package com.beesidk.projet.interfaces;

import com.beesidk.projet.entity.Cour;
import com.beesidk.projet.enums.PaiementType;

import java.time.LocalDateTime;
import java.util.List;

public interface ICourService extends IService<Cour> {

    List<Cour> search(String tag, String category, String name, PaiementType paiementType);

    List<Cour> searchByDateBetween(LocalDateTime from, LocalDateTime to);

    List<Cour> searchByDuration(int duree);

    List<Cour> searchByPrice(float price);

}
