package com.pavan.BloggApp.BloggApp.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.pavan.BloggApp.BloggApp.Entities.Comments;
import com.pavan.BloggApp.BloggApp.Entities.Post;
import com.pavan.BloggApp.BloggApp.Exceptions.CustomExceptions;
import com.pavan.BloggApp.BloggApp.repositories.CommentRepository;
import com.pavan.BloggApp.BloggApp.repositories.PostRepository;

@Service
public class CommentServices {
	
	
	@Autowired
	PostRepository postrepo;
	
	@Autowired
	CommentRepository commentrepo;
	
	public ResponseEntity<?> addcomment(Comments comment, int postid)
	{
		
		
		try {
			
			
			Optional<Post> post = postrepo.findById(postid); 
			if(post.isPresent())
			{
				
				Post post1 = post.get();
				comment.setPost(post1);
				
				Comments comment1 = commentrepo.save(comment);
				
				return ResponseEntity.status(HttpStatus.CREATED).body(comment1);
				
			}
			else {
				throw new CustomExceptions();
			}
			
		}
		catch(CustomExceptions ex)
		{
			String response = ex.getMessageforpost(postid);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		
	}
	
	
	
	
	//DELETE COMMENT
	public ResponseEntity<?> deletecomment(int id)
	{
	
			
			Optional<Comments> c = commentrepo.findById(id);
			if(c.isPresent())
			{
				commentrepo.deleteById(id);
				return ResponseEntity.status(HttpStatus.OK).body("COMMENT DELETED!");
				
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("COMMENT NOT FOUND!");
			}
		
		
	}
	

}
