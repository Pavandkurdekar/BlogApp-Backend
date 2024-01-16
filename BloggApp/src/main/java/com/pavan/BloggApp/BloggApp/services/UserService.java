package com.pavan.BloggApp.BloggApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pavan.BloggApp.BloggApp.Entities.User;
import com.pavan.BloggApp.BloggApp.repositories.UserRepo;


@Service
public class UserService{
	
	@Autowired
	UserRepo repo;
	
	
	
	
	//Method to create an user
	public User createuser(User user)
	{
	User newuser = repo.save(user);
	return newuser;
	}
	
	
	
	//Method to get all users
	public List<User> getallusers()
	{
		List<User> allusers = repo.findAll();
		
		return allusers;
	}
	
	
	
	
	//Method to update user
	public User updateuser(User user , int id)
	{
		
		
		Optional<User> fuser = repo.findById(id);
		User existinguser = fuser.get();
		
		existinguser.setName(user.getName());
		existinguser.setEmail(user.getEmail());
		existinguser.setPassword(user.getPassword());
		existinguser.setAbout(user.getAbout());
		
		
		return existinguser;
	}
	
	
	//Method to get user by id
	public User getuserbyid(int id)
	{
		Optional<User> id_user = repo.findById(id);
		
		User fuser = id_user.get();
		
		return fuser;
	}
	
	
	//Method to delete user
	public String deleteuser(int id)
	{
    Optional<User> id_user = repo.findById(id);
		
    if(id_user.isPresent())
        {
	       repo.deleteById(id);
	       return "User with id" +id+" got deleted!!!";
        }
    else 
        {
	       return "User with the "+id+" not found";
        }
	
		
	}
	
	

}
