package com.pavan.BloggApp.BloggApp.controlllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pavan.BloggApp.BloggApp.Entities.User;
import com.pavan.BloggApp.BloggApp.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userservice;
	
	
	//Controller to create user
	@PostMapping("/createuser")
	public ResponseEntity<?> create_user(@RequestBody User user)
	{
		ResponseEntity<?> newuser = userservice.createuser(user);
		return newuser;
	}
	
	
	
	//Controller to get All Users
	@GetMapping("/getallusers")
	public List<User> getallusers()
	{
		List<User> user = userservice.getallusers();
		return user;
	}
	
	
	
	//Controller to get User By Id
	@GetMapping("/getuserbyid/{id}")
	public ResponseEntity<?> getuserby_id(@PathVariable int id) 
	{
		
		ResponseEntity<?> response = userservice.getuserbyid(id);
		
		return response;	
		
	}
	
	
	//controller to update user
	@PutMapping("/updateuser/{id}") 
	public ResponseEntity<?> updateuser(@Valid @RequestBody User user ,@PathVariable int id) 
	{
		ResponseEntity<?> response = userservice.updateuser(user, id);
		
		return response;
	}
	
	
	
	
	//Controller to delete user by id
	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<?> deleteuser(@PathVariable int id) 
	{
		ResponseEntity<?> response = userservice.deleteuser(id);
		return response;
	}

}
