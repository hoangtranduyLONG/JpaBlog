package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.IBlogService;
import com.codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryControllerRESTful {

    @Autowired
    public IBlogService blogService;

    @Autowired
    public ICategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<Iterable<Category>> findAllCategory() {
        List<Category> categories = (List<Category>) categoryService.findAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping( "/view/{id}")
    public ResponseEntity<Iterable<Blog>> getCategory(@PathVariable("id") Long id) {
        ResponseEntity<Iterable<Blog>> reBlogList = null;
        Optional<Category> category;
        Iterable<Blog> blogList;
        category = categoryService.findById(id);
        if (category != null) {
            blogList = blogService.findAllByCategory(category.get());
            reBlogList = new ResponseEntity<>(blogList, HttpStatus.OK);
        } else {
            reBlogList = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return reBlogList;
    }
}
