package com.beesidk.projet.interfaces;

import com.beesidk.projet.entity.Blog;

import java.util.List;

public interface IBlogService extends IService<Blog> {

    List<Blog> getAllBlogsByTitle(String title);
}
