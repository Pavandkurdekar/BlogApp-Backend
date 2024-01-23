package com.pavan.BloggApp.BloggApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.BloggApp.BloggApp.Entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
