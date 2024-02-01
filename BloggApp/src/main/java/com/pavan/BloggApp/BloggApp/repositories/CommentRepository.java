package com.pavan.BloggApp.BloggApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.BloggApp.BloggApp.Entities.Comments;

public interface CommentRepository  extends JpaRepository<Comments, Integer>{

}
