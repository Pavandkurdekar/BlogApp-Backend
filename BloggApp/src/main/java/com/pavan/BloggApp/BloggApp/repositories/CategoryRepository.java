package com.pavan.BloggApp.BloggApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.BloggApp.BloggApp.Entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
