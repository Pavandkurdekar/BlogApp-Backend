package com.pavan.BloggApp.BloggApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pavan.BloggApp.BloggApp.Entities.Category;
import com.pavan.BloggApp.BloggApp.repositories.CategoryRepository;

import jakarta.validation.Valid;

@Service
public class CategoryServices {
	
	@Autowired
	CategoryRepository catrepo;
	
	
	
	//CREATE CATEGORY
	public ResponseEntity<?> createcategory(@Valid Category cat)
	{
		
		try {
			
			Category response = catrepo.save(cat);
			return ResponseEntity.ok().body(response);
			//throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Custom Message");
		}
		catch (Exception e) {
			
			return ResponseEntity.badRequest().body("Please Make Sure That The Title Must Contain Minimum Of 3 "
					                            + "Characters And Description Must Contain Minimum Of 8 Characters!!! ");
		
		}
		
	}
	
	
	//GET CATEGORY
	public ResponseEntity<?> getcategory(int id)
	{
		try {
			Optional<Category> cat = catrepo.findById(id);
			
			if(cat.isPresent())
			{
				Category cat1 = cat.get();
				return ResponseEntity.ok().body(cat1);
			}
			else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Category With The id "+id+" not found!!!");
			}
		}
		catch(ResponseStatusException e)
		{
			return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
		}
		catch(Exception ex)
		{
			return ResponseEntity.internalServerError().body("Internal Server Error!!!");
		}
	}
	
	
	
	//GET ALL CATEGORY
	public List<Category> getallcategory()
	{
		
			List<Category> cats = catrepo.findAll();
			return cats;
		
	}
	
	
	
	//UPDATE CATEGORY
	public ResponseEntity<?> updatecategory(@Valid int id, Category obj)
	{
		try {
			Optional<Category> cat = catrepo.findById(id);
			
			if(cat.isPresent())
			{
				Category cat1 = cat.get();
				cat1.setCategorytitle(obj.getCategorytitle());
				cat1.setCategorydescription(obj.getCategorydescription());
				catrepo.save(cat1);
				return ResponseEntity.ok().body(cat1);
			}
			else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Category With The id "+id+" not found!!!");
			}
		}
		catch(ResponseStatusException e)
		{
			return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
		}
		catch(Exception ex)
		{
			return ResponseEntity.badRequest().body("Please Make Sure That The Title Must Contain Minimum Of 3"
					                +" Characters And Description Must Contain Minimum Of 8 Characters!!!");
		}
	}
	
	
	
	//DELETE CATEGORY
	public ResponseEntity<String> deletecategory(int id)
	{
		try {
			Optional<Category> cat = catrepo.findById(id);
			
			if(cat.isPresent())
			{
				catrepo.deleteById(id);
				return ResponseEntity.ok().body("Category With The id "+id+" Deleted Successfully!!!");
			}
			else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Category With The id "+id+" not found!!!");
			}
		}
		catch(ResponseStatusException e)
		{
			return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
		}
		catch(Exception ex)
		{
			return ResponseEntity.internalServerError().body("Internal Server Error");
		}
	}
	
	

}
