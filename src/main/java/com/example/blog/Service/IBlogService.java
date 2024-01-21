package com.example.blog.Service;

import com.example.blog.Model.Blog;
import com.example.blog.Model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBlogService extends IGenerateService<Blog> {
    Iterable<Blog> findBlogByCustomerId(Customer customer);
}
