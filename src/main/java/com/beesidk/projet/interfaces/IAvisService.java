package com.beesidk.projet.interfaces;

import com.beesidk.projet.entity.Avis;
import com.beesidk.projet.entity.Cour;

import java.util.List;

public interface IAvisService extends IService<Avis> {
    List<Avis> getAvisByCour(String cour);
}
