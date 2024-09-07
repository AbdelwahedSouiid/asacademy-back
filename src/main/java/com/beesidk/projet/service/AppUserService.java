package com.beesidk.projet.service;


import com.beesidk.projet.entity.AppRole;
import com.beesidk.projet.entity.AppUser;
import com.beesidk.projet.interfaces.IService;
import com.beesidk.projet.repository.AppUserRepository;
import com.beesidk.projet.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class AppUserService implements IService<AppUser> {

    private AppUserRepository repo;
    private RoleRepository roleRepo;
    private PasswordEncoder passwordEncoder;


    @Override
    public List<AppUser> retrieveAll() {
        return repo.findAll();
    }

    @Override
    public AppUser retrieve(String id) {
        return repo.findById(id).orElse(null);
    }


    public AppUser add(AppUser appUser) {
        // Encoder le mot de passe avant de sauvegarder l'utilisateur
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));

        // Assigner le rôle ADMIN par défaut
        Optional<AppRole> role = roleRepo.findByName("USER");
        List<AppRole> roles = new ArrayList<>();
        roles.add(role.orElseThrow(() -> new RuntimeException("Role ADMIN not found")));
        appUser.setRoles(roles);

        // Sauvegarder l'utilisateur
        return repo.save(appUser);
    }

    @Override
    public void remove(String id) {
        repo.deleteById(id);
    }

    @Override
    public AppUser modify(AppUser AppUser) {
        return repo.save(AppUser);
    }

    public AppUser retrieveByEmail(String userEmail) {
        AppUser user = repo.findByEmail(userEmail).orElse(null);
        return user;
    }
}
