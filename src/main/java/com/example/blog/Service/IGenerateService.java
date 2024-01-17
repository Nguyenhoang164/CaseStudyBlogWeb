package com.example.blog.Service;

import com.example.blog.Model.Blog;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findAll();
    void save(T t);
    Optional<T> findById (int id);
    void remove (int id);
    void update(T t);

}
