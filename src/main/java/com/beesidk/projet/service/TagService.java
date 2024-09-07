package com.beesidk.projet.service;

import com.beesidk.projet.entity.Tag;
import com.beesidk.projet.interfaces.IService;
import com.beesidk.projet.interfaces.ITagService;
import com.beesidk.projet.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TagService implements ITagService {


    private TagRepository repo;

    @Override
    public List<Tag> retrieveAll() {
        return repo.findAll();
    }

    @Override
    public Tag retrieve(String id) {
        return repo.findById(id).orElse(null);
    }


    @Override
    public Tag add(Tag Tag) {
        return repo.save(Tag);
    }

    @Override
    public void remove(String id) {
        repo.deleteById(id);
    }

    @Override
    public Tag modify(Tag Tag) {
        return repo.save(Tag);
    }

    @Override
    public List<Tag> retrieveByTag(String tag) {
        return repo.findAllByName(tag);
    }
}
