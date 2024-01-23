package com.pavan.BloggApp.BloggApp.controlllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

}
