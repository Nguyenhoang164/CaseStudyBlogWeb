package com.example.blog.Repository;

import com.example.blog.Model.Blog;
import com.example.blog.Model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBlogRepository extends CrudRepository<Blog,Integer> {
    void deleteBlogByCustomerId(int id);
    Page<Blog> findAll(Pageable pageable);
    Iterable<Blog> findBlogsByCustomerId(int id);
}
