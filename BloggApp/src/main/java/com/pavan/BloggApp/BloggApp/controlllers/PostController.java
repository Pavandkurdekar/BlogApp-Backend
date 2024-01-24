package com.pavan.BloggApp.BloggApp.controlllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pavan.BloggApp.BloggApp.Entities.Post;
import com.pavan.BloggApp.BloggApp.services.PostServices;

@RestController
@RequestMapping("/Post")
public class PostController {
	
	@Autowired
	PostServices postservice;
	
	//CREATE POST
	@PostMapping("/create/user/{uid}/category/{catid}")
	public ResponseEntity<?> createpost(@RequestBody Post post, @PathVariable int uid, @PathVariable int catid)
	{
		ResponseEntity<?> response = postservice.createpost(post, uid, catid); 
		
		return response;
	}
	
	//GET ALL POST BY CATEGORY
	@GetMapping("/get/{id}/posts")
	public ResponseEntity<?> getpostbycategory(@PathVariable int id) 
	{
		ResponseEntity<?> response = postservice.getpostbycategory(id);
		return response;
	}
 
	
	//GET ALL POST BY USER
	@GetMapping("/get/user/{id}/posts")
	public ResponseEntity<?> getpostbyuser(@PathVariable int id) 
	{
		ResponseEntity<?> response = postservice.getpostsbyuser(id);
		
		return response;
	}
 
   
	
	//GET ALL POSTS
	@GetMapping("/get/allpost")
	public ResponseEntity<?> getallpost()
	{
		ResponseEntity<?> response = postservice.getallposts();
		
		return response;
	}
	
	
	
	//GET POST BY ID
	@GetMapping("/getpost/{id}")
	public ResponseEntity<?> getpostbyid(@PathVariable int id)
	{
		ResponseEntity<?> response = postservice.getpostbyid(id);
		
		return response;
	}
	
	
}
