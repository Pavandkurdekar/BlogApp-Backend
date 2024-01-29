package com.pavan.BloggApp.BloggApp.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pavan.BloggApp.BloggApp.Entities.Category;
import com.pavan.BloggApp.BloggApp.Entities.Post;
import com.pavan.BloggApp.BloggApp.Entities.User;
import com.pavan.BloggApp.BloggApp.Exceptions.CustomExceptions;
import com.pavan.BloggApp.BloggApp.repositories.CategoryRepository;
import com.pavan.BloggApp.BloggApp.repositories.PostRepository;
import com.pavan.BloggApp.BloggApp.repositories.UserRepo;

@Service
public class PostServices {

    @Autowired
    PostRepository postrepo;

    @Autowired
    UserRepo userrepo;

    @Autowired
    CategoryRepository catrepo;

    
    
    // CREATE POST
    public ResponseEntity<?> createpost(Post post, int uid, int catid) {

        Optional<User> user = userrepo.findById(uid);
        Optional<Category> category = catrepo.findById(catid);

        if (user.isPresent()) {
        	User u = user.get();
            try {
                if (category.isPresent()) {
                	Category c = category.get();
                	Post post1 = new Post();
                    post1.setAddeddate(new Date());
                    post1.setTitle(post.getTitle());
                    post1.setContent(post.getContent());
                    post1.setImagename(post.getImagename());
                    post1.setUser(u);
                    post1.setCategory(c);
                    Post post2 = postrepo.save(post1);
                    return ResponseEntity.ok().body(post2);
                } else {
                    throw new CustomExceptions();
                }
            } catch (CustomExceptions ex) {
                String exception = ex.getMessageforcategory(catid);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
            }
        } else {
            try {
                throw new CustomExceptions();
            } catch (CustomExceptions ex1) {
                String exception2 = ex1.getMessageforuser(uid);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception2);            }
        }
    }
    
    

    //FIND ALL POST BY CATEGORY
    public ResponseEntity<?> getpostbycategory(int id)  {
    	
    	Optional<Category> cat = catrepo.findById(id);
    	try {
    		if(cat.isPresent())
        	{
        		Category cat1 = cat.get();
        		
        			List<Post> posts = postrepo.findByCategory(cat1);
        			return ResponseEntity.ok().body(posts);
        	}
    		else {
    			throw new CustomExceptions();
    		}
    	}
    	catch(CustomExceptions ex)
    	{
    		String exception = ex.getMessageforcategory(id);
    		System.out.println(exception);
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    	}
    }
  
    
    
    //GET ALL POSTS BY USER
    public ResponseEntity<?> getpostsbyuser(int id)
    {
    	Optional<User> u = userrepo.findById(id);
    	try {
    		
    		if(u.isPresent())
    		{
    			User u1 = u.get();
    			List<Post> posts = postrepo.findByUser(u1);
    			if(posts.isEmpty())
    			{
    				return ResponseEntity.status(HttpStatus.OK).body("This User Does Not Have Any Posts!!!");
    			}
    			else {
    				return ResponseEntity.ok().body(posts);
    			}
    			
    		}
    		else {
    			throw new CustomExceptions();
    		}
    		
    	}
    	catch(CustomExceptions ex)
    	{
    		String response = ex.getMessageforuser(id);
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    	}
    }
    
    
    
 	//GET ALL POST
    public ResponseEntity<?> getallposts()
    {
    	try {
    		List<Post> posts = postrepo.findAll();
    		if(posts.isEmpty())
    		{
    			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL SERVER ERROR!!!");
    		}
    		else {
    			return ResponseEntity.ok().body(posts);
    		}
    	}
    	catch(ResponseStatusException ex)
    	{
    		return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
    	}
    }
    
    
    
 	
  	//GET SINGLE POST BY ID
    public ResponseEntity<?> getpostbyid(int id)
    {
    	try {
    		Optional<Post> post = postrepo.findById(id);
    		if(post.isPresent())
    		{
    			Post post1 = post.get();
    			System.out.println(post1.getUser());
    			System.out.println(post1.getCategory());
    			return ResponseEntity.ok().body(post1);
    		}
    		else {
    			throw new CustomExceptions();
    		}
    	}
    		catch(CustomExceptions ex)
    		{
    			String exception = ex.getMessageforpost(id);
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    		}
    	
    }
    
    
    
    
    //UPDATE POST BY ID
	public ResponseEntity<?> updatepost(Post editedpost, int id) {
		
		try {
			Optional<Post> post = postrepo.findById(id);
			if(post.isPresent())
			{
				
				Post newpost = post.get();
				newpost.setTitle(editedpost.getTitle());
			    newpost.setContent(editedpost.getContent());
			    newpost.setImagename(editedpost.getImagename());
			    Post newpost1 = postrepo.save(newpost);
			    return ResponseEntity.ok().body(newpost1);
			    
			}
			else {
				throw new CustomExceptions();
			}
		}
		catch(CustomExceptions ex)
		{
			
			String exception = ex.getMessageforpost(id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
		}
		
	}
	
	
	
    
	//DELETE POST BY ID
    public ResponseEntity<?> deletepost(int id)
    {
    	try {
    		Optional<Post> post = postrepo.findById(id);
    		if(post.isPresent())
    		{
    			postrepo.deleteById(id);
    			return ResponseEntity.status(HttpStatus.OK).body("Post With Id "+id+" Delted Successfully!!");
    		}
    		else {
    			throw new CustomExceptions();
    		}
    	}
    	catch(CustomExceptions ex)
		{
			
			String exception = ex.getMessageforpost(id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
			
		}
    }
    
    
    
    
    //SEARCH POST BY POST TITLE
    public ResponseEntity<?> searchpost(String keyword)
    {
    
    	try {
    		List<Post> posts = postrepo.findByTitleContaining(keyword);
    		if(posts.isEmpty())
    		{
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Posts Found By The Title "+keyword+"!!");
    			
    		}
    		
    		else {
    			
    			return ResponseEntity.ok().body(posts);

    		}
    	}
    		
    		catch(Exception ex)
    		{
    			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL SERVER ERROR!!");
    		}
    }
    
    
    
    
    //SEARCH POST BY POST CATEGORY
    public  ResponseEntity<?> searchbycategory(String keyword)
    {
    	try {
    		
    		Optional<Category> cat = catrepo.findByCategorytitleContaining(keyword);
    		if(cat.isPresent())
    		{
    			Category cat1 =cat.get();
    			List<Post> posts = postrepo.findAllByCategory_Id(cat1.getId());
    			return ResponseEntity.ok().body(posts);
    		}
    		else {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO POSTS AVAILABLE UNDER THE CATEGORY "+keyword+"!!");
    		}
        
    }
    	catch(Exception ex)
    	{
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL SERVER ERROR!!");
    	}
    	
  
    	
    }
    
    
    

}
