package com.pavan.BloggApp.BloggApp.controlllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pavan.BloggApp.BloggApp.Entities.Comments;
import com.pavan.BloggApp.BloggApp.services.CommentServices;

@RequestMapping("/comment")
public class CommentController {

	
	
	@Autowired
	CommentServices comservice;
	
	
	
	@PostMapping("/addcomment/{postid}")
	public ResponseEntity<?> addcomment(@RequestBody Comments comment, @PathVariable int postid)
	{
		
		ResponseEntity<?> response = comservice.addcomment(comment, postid);
		return response;
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletecomment(@PathVariable int id)
	{
		
		ResponseEntity<?> response = comservice.deletecomment(id);
		return response;
		
	}
	
	
}
