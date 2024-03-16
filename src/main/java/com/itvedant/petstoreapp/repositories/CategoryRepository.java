package com.itvedant.petstoreapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.itvedant.petstoreapp.entities.Category;

public interface CategoryRepository 
    extends CrudRepository<Category, Integer>{

    
}
