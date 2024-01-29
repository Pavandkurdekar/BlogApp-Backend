package com.pavan.BloggApp.BloggApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavan.BloggApp.BloggApp.Entities.Category;
import com.pavan.BloggApp.BloggApp.Entities.Post;
import com.pavan.BloggApp.BloggApp.Entities.User;

public interface PostRepository extends JpaRepository<Post, Integer>{

Object findByCategoryTitle = null;

public	List<Post> findByCategory(Category category);

public List<Post> findByUser(User user);

public List<Post> findByTitleContaining(String keyword);

List<Post> findAllByCategory_Id(int categoryId);










}
