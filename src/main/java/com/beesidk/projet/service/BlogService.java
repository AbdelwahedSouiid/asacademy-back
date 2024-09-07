package com.beesidk.projet.service;

import com.beesidk.projet.entity.Blog;
import com.beesidk.projet.interfaces.IBlogService;
import com.beesidk.projet.interfaces.IService;
import com.beesidk.projet.repository.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BlogService implements IBlogService {

    private BlogRepository repo;

    @Override
    public List<Blog> retrieveAll() {
        return repo.findAll();
    }

    @Override
    public Blog retrieve(String id) {
        return repo.findById(id).orElse(null);
    }


    @Override
    public Blog add(Blog blog) {
        blog.setDateCreation(LocalDateTime.now());
        return repo.save(blog);
    }

    @Override
    public void remove(String id) {
        repo.deleteById(id);
    }

    @Override
    public Blog modify(Blog Blog) {
        return repo.save(Blog);
    }

    @Override
    public List<Blog> getAllBlogsByTitle(String title) {
        return repo.findByTitle(title);
    }
}
