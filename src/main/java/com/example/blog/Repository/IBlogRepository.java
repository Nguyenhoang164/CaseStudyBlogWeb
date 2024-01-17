package com.example.blog.Repository;

import com.example.blog.Model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlogRepository extends CrudRepository<Blog,Integer> {
}
