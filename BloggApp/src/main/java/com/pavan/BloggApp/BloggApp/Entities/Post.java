package com.pavan.BloggApp.BloggApp.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postid;
	
	private String title;
	
	private String content;
	
	private String imagename;
	
	private Date addeddate;
	
	
	@ManyToOne
	private Category category;
	
	
	@ManyToOne
	private User user;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "post" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Comments> comments = new ArrayList<Comments>() ;

	

	public List<Comments> getComments() {
		return comments;
	}


	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}


	public int getPostid() {
		return postid;
	}


	public void setPostid(int postid) {
		this.postid = postid;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getImagename() {
		return imagename;
	}


	public void setImagename(String imagename) {
		this.imagename = imagename;
	}


	public Date getAddeddate() {
		return addeddate;
	}


	public void setAddeddate(Date addeddate) {
		this.addeddate = addeddate;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Post(int postid, String title, String content, String imagename, Date addeddate, Category category,
			User user) {
		super();
		this.postid = postid;
		this.title = title;
		this.content = content;
		this.imagename = imagename;
		this.addeddate = addeddate;
		this.category = category;
		this.user = user;
	}


	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
