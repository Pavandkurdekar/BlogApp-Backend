package com.pavan.BloggApp.BloggApp.controlllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userservice;
	
	
	//COntroller to create user
	@PostMapping("/createuser")
	public User create_user(@RequestBody User user)
	{
		User newuser = userservice.createuser(user);
		return newuser;
	}
	
	
	
	//COntroller to get All Users
	@GetMapping("/getallusers")
	public List<User> getallusers()
	{
		List<User> user = userservice.getallusers();
		return user;
	}
	
	
	
	//COntroller to get User By Id
	@GetMapping("/getuserbyid/{id}")
	public String getuserby_id(@PathVariable int id)
	{
		
		String response = userservice.getuserbyid(id);
		
		return response;
		
		
	}
	
	
	//controller to update user
	@PutMapping("/updateuser/{id}")
	public String updateuser(@RequestBody User user ,@PathVariable int id)
	{
		String response = userservice.updateuser(user, id);
		
		return response;
	}
	
	
	
	
	//Controller to delete user by id
	@DeleteMapping("/deleteuser/{id}")
	public String deleteuser(@PathVariable int id)
	{
		String response = userservice.deleteuser(id);
		return response;
	}
	
	
	
	

}
