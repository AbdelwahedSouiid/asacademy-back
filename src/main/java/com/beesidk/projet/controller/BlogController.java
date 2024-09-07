package com.beesidk.projet.controller;


import com.beesidk.projet.entity.Blog;

import com.beesidk.projet.entity.Cour;
import com.beesidk.projet.interfaces.IBlogService;
import com.beesidk.projet.interfaces.IService;
import com.beesidk.projet.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    private IBlogService BlogService;


    // http://localhost:8089/projet/Blog/retrieve-all-Blog
    @Operation(description = "Ce web service permet de récupérer toutes les Blogs de la base de données")
    @GetMapping("/retrieve-all-Blog")
    public List<Blog> getBlogs() {
        List<Blog> listBlogs = BlogService.retrieveAll();

        return listBlogs;
    }

    // http://localhost:8089/projet/Blog/retrieve-Blog/8
    @GetMapping("/retrieve-Blog/{Blog-id}")
    public Blog retrieveBlog(@PathVariable("Blog-id") String id) {
        Blog Blog = BlogService.retrieve(id);
        return Blog;
    }

    // http://localhost:8089/projet/Blog/add-Blog
    @PostMapping("/add-Blog")
    public Blog addBlog(@RequestBody Blog c) {

        Blog Blog = BlogService.add(c);
        return Blog;
    }

    // http://localhost:8089/projet/Blog/remove-Blog/{Blog-id}
    @DeleteMapping("/remove-Blog/{Blog-id}")
    public void removeBlog(@PathVariable("Blog-id") String id) {
        BlogService.remove(id);
    }

    // http://localhost:8089/projet/Blog/modify-Blog
    @PutMapping("/modify-Blog")
    public ResponseEntity<?> modifyBlog(@RequestBody Blog c) {

        try {
            Blog blog = BlogService.modify(c);
            return ResponseEntity.ok(blog);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating cour: " + e.getMessage());
        }
    }

    // http://localhost:8089/exam/Cour/search
    @GetMapping("/retrieve-By-Title/{searchTerm}")
    public List<Blog> searchByTitle(@PathVariable("searchTerm") String title) {
        return BlogService.getAllBlogsByTitle(title);
    }


}
