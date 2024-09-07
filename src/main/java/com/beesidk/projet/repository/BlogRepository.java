package com.beesidk.projet.repository;

import com.beesidk.projet.entity.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {

    List<Blog> findByTitle(String title);
}
