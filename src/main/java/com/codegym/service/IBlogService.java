package com.codegym.service;
import com.codegym.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface IBlogService extends IGeneralService<Blog> {

    Page<Blog> findAll(Pageable pageable);

    Page<Blog> findAllByNameContaining(String name, Pageable pageable);

    Page<Blog> findAllByOrderByDateTime(Pageable pageable);

}
