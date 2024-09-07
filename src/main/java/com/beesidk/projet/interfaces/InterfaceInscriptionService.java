package com.beesidk.projet.interfaces;


import com.beesidk.projet.entity.AppUser;
import com.beesidk.projet.entity.Inscription;

import java.util.List;

public interface InterfaceInscriptionService extends IService<Inscription> {

    List<Inscription> getAllInscriptionByUser(String user);

    boolean isInscriptionExist(String userId, String courId);
}
