package com.pavan.BloggApp.BloggApp.Entities;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty
	@Size(min = 3)
	private String  categorytitle;
	
	
	@NotEmpty
	@Size(min = 8)
	private String categorydescription;
	
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<Post>();


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCategorytitle() {
		return categorytitle;
	}


	public void setCategorytitle(String categorytitle) {
		this.categorytitle = categorytitle;
	}


	public String getCategorydescription() {
		return categorydescription;
	}


	public void setCategorydescription(String categorydescription) {
		this.categorydescription = categorydescription;
	}


	public Category(int id, @NotBlank @Size(min = 3) String categorytitle,
			@NotBlank @Size(min = 8) String categorydescription) {
		super();
		this.id = id;
		this.categorytitle = categorytitle;
		this.categorydescription = categorydescription;
	}


	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Category [id=" + id + ", categorytitle=" + categorytitle + ", categorydescription="
				+ categorydescription + ", posts=" + posts + "]";
	}
	
	
	

}
