package com.pavan.BloggApp.BloggApp.controlllers;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.pavan.BloggApp.BloggApp.Entities.Post;
import com.pavan.BloggApp.BloggApp.services.FileService;
import com.pavan.BloggApp.BloggApp.services.PostServices;
import jakarta.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("/Post")
public class PostController {
	
	@Autowired
	PostServices postservice;
	
	
    @Autowired
    FileService fileservice;
    
    
    @Value("${project.image}")
    String path;
    
    
	//CREATE POST
	@PostMapping("/create/user/{uid}/category/{catid}")
	public ResponseEntity<?> createpost(@ModelAttribute Post post, @PathVariable int uid, @PathVariable int catid, @RequestParam("image") MultipartFile image) throws IOException
	{
		ResponseEntity<?> response = postservice.createpost(post, uid, catid, image); 
		
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
	public ResponseEntity<?> getpostbyid(@PathVariable int id  ) 
	{
		ResponseEntity<?> response1 = postservice.getpostbyid(id);
		
		return response1;
	}
	
	
	
	
	//UPDATE POST BY ID
	@PutMapping("/update/post/{id}")
	public ResponseEntity<?> updatepost(@RequestBody Post editedpost, @PathVariable int id)
	{
		ResponseEntity<?> response = postservice.updatepost(editedpost,id);
		return response;
	}
	
	
	
	
	//DELETE POST BY ID
	@DeleteMapping("/delete/post/{id}")
	public ResponseEntity<?> deletepost(@PathVariable int id)
	{
		ResponseEntity<?> response = postservice.deletepost(id);
		return response;
	}
	
	
	
	
	//SEARCH POST BY POST TITLE
	@GetMapping("/search/post/{keyword}")
	public ResponseEntity<?> searchpost(@PathVariable String keyword)
	{
		ResponseEntity<?> response = postservice.searchpost(keyword);
		return response;
	}
	
	
	
	
	//SEARCH POST BY POST CATEGORY
	@GetMapping("/search/category/{keyword}")
	public ResponseEntity<?> searchviacategory(@PathVariable String keyword)
	{
		ResponseEntity<?> response = postservice.searchbycategory(keyword);
		return response;
	}
	
	
	
	
	//GET IMAGE(POST) BY IMAGE NAME
	@GetMapping("get/image/{imagename}")
	public void downloadimage(@PathVariable String imagename, HttpServletResponse response) throws IOException
	{
		
		InputStream resource = fileservice.getimage(path, imagename);
		response.setContentType(org.springframework.http.MediaType.ALL_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
	}
	
	
}
