package com.beesidk.projet.service;


import com.beesidk.projet.entity.AppRole;
import com.beesidk.projet.entity.AppRole;
import com.beesidk.projet.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService implements IService<AppRole> {

    private RoleRepository repo;


    @Override
    public List<AppRole> retrieveAll() {
        return repo.findAll();
    }

    @Override
    public AppRole retrieve(String id) {
        return repo.findById(id).orElse(null);
    }


    @Override
    public AppRole add(AppRole AppRole) {
        return repo.save(AppRole);
    }

    @Override
    public void remove(String id) {
        repo.deleteById(id);
    }

    @Override
    public AppRole modify(AppRole AppRole) {
        return repo.save(AppRole);
    }

}
