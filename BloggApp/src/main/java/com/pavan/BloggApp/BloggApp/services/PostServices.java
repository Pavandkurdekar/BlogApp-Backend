package com.pavan.BloggApp.BloggApp.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pavan.BloggApp.BloggApp.Entities.Category;
import com.pavan.BloggApp.BloggApp.Entities.Post;
import com.pavan.BloggApp.BloggApp.Entities.User;
import com.pavan.BloggApp.BloggApp.Exceptions.CustomExceptions;
import com.pavan.BloggApp.BloggApp.repositories.CategoryRepository;
import com.pavan.BloggApp.BloggApp.repositories.PostRepository;
import com.pavan.BloggApp.BloggApp.repositories.UserRepo;

@Service
public class PostServices {

    @Autowired
    PostRepository postrepo;

    @Autowired
    UserRepo userrepo;

    @Autowired
    CategoryRepository catrepo;


    
    
    // CREATE POST
    public ResponseEntity<?> createpost(Post post, int uid, int catid) {

        Optional<User> user = userrepo.findById(uid);
        Optional<Category> category = catrepo.findById(catid);

        if (user.isPresent()) {
        	User u = user.get();
            try {
                if (category.isPresent()) {
                	Category c = category.get();
                	Post post1 = new Post();
                    post1.setAddeddate(new Date());
                    post1.setTitle(post.getTitle());
                    post1.setContent(post.getContent());
                    post1.setImagename(post.getImagename());
                    post1.setUser(u);
                    post1.setCategory(c);
                    Post post2 = postrepo.save(post1);
                    return ResponseEntity.ok().body(post2);
                } else {
                    throw new CustomExceptions();
                }
            } catch (CustomExceptions ex) {
                String exception = ex.getMessageforcategory(catid);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
            }
        } else {
            try {
                throw new CustomExceptions();
            } catch (CustomExceptions ex1) {
                String exception2 = ex1.getMessageforuser(uid);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception2);            }
        }
    }

	
	//UPDATE POST
	
	//DELETE POST
	
	//GET ALL POST
	
	//GET SINGLE POST BY ID
	
	//GET POSTS BY CATEGORY
	
	//GET ALL POSTS BY USER
	
	//SEARCH POSTS

}
