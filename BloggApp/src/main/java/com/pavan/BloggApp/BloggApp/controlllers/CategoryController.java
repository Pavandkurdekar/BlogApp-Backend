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
import com.pavan.BloggApp.BloggApp.Entities.Category;
import com.pavan.BloggApp.BloggApp.services.CategoryServices;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	
	@Autowired
	CategoryServices catservice;
	
	//CREATE CONTROLLER
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody Category cat)
	{
		
		ResponseEntity<?> result = catservice.createcategory(cat);
		return result;
		
	}
	
	
	
	//GET CONTROLLER
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<?> get(@PathVariable int id)
	{
		ResponseEntity<?> result = catservice.getcategory(id);
		return result;
	}
	
	
	
	//GET ALL CONTROLLER
	@GetMapping("/getall")
	public List<Category> getall()
	{
		List<Category> result = catservice.getallcategory();
		return result;
	}
	
	
	
	//UPDATE CONTROLLER
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@RequestBody Category cat,@PathVariable int id)
	{
		ResponseEntity<?> result = catservice.updatecategory(id, cat);
		return result;
	}
	
	
	
    //DELETE CONTROLLER
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable int id)
	{
		ResponseEntity<String> response = catservice.deletecategory(id);
		return response;
	}
}
