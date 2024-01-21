package com.example.blog.Service.Impl;

import com.example.blog.Model.Blog;
import com.example.blog.Model.Customer;
import com.example.blog.Repository.IBlogRepository;
import com.example.blog.Service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepository blogRepository;


    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public void save(Blog blog) {
       blogRepository.save(blog);
    }

    @Override
    public Optional<Blog> findById(int id) {
        return blogRepository.findById(id);
    }

    @Override
    public void remove(int id) {
      blogRepository.deleteById(id);
    }

    @Override
    public void update(Blog blog) {
        blogRepository.deleteById(blog.getId());
        blogRepository.save(blog);
    }

    @Override
    public Iterable<Blog> findBlogByCustomerId(Customer customer) {
        return blogRepository.findBlogsByCustomerId(customer.getId());
    }
}
