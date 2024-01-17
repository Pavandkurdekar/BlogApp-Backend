package com.pavan.BloggApp.BloggApp.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
	public ResponseEntity<?> updateuser(User user , int id)
	{
		
		try {
			Optional<User> fuser = repo.findById(id);
			
			
			if (fuser.isPresent())
			{
				User existinguser = fuser.get();
				
				existinguser.setName(user.getName());
				existinguser.setEmail(user.getEmail());
				existinguser.setPassword(user.getPassword());
				existinguser.setAbout(user.getAbout());
				
				repo.save(existinguser);
				
				return ResponseEntity.ok().body(existinguser);
			}
			else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with the id "+id+" not found!!!");
			}
		}catch(ResponseStatusException e)
		{
			return ResponseEntity.status(e.getStatusCode()).body("Exception Caught : "+e.getReason());
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
		}
	
			
	}
	
	
	
	
	//Method to get user by id
	public ResponseEntity<?> getuserbyid(int id)
	{
		try {
			Optional<User> id_user = repo.findById(id);
			
			if(id_user.isPresent())
			{
				User u = id_user.get();
				//String u1 = u.toString();
				return ResponseEntity.ok(u);
			}
		
		else {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
	         }
	}
		catch(ResponseStatusException e)
		{
			return ResponseEntity.status(e.getStatusCode()).body("Exception Occured : "+e.getReason());
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
		}
	}
	
	
	
	// Method to delete user
	public ResponseEntity<?> deleteuser(int id) {
	   
		try {
			Optional<User> id_user = repo.findById(id);

	        if (id_user.isPresent()) 
	        {
	            repo.deleteById(id);
	            return ResponseEntity.ok().body("User with id " +id+ " Deleted Successfully!!!");
	        }  
	        else 
	        {
	          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
	        }
		}
		catch(ResponseStatusException e)
		{
		  return ResponseEntity.status(e.getStatusCode()).body("Exception Caught : "+e.getReason());	
		}
	    catch(Exception e)
		{
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
		}
	}

}
