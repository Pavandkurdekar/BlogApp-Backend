package com.pavan.BloggApp.BloggApp.Entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="users")

public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="user_name")
	@NotEmpty
	@Size(min = 4, message = "Name Must Contain Atleast 4 Characters!!!")
	private String name;
	
	
	@Column(unique = true)
	@Email(message = "Please Enter Valid Email!!!")
	private String email;
	
	
	@NotEmpty
	@Size(min = 4, max = 12, message = "Password Must Be Between 4 to 13 Characters!!!")
	private String password;
	
	
	@NotEmpty( message = "About Section Cannot Be Empty!!! ")
	private String about;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAbout() {
		return about;
	}


	public void setAbout(String about) {
		this.about = about;
	}


	public User(int id, String name, String email, String password, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about=" + about
				+ "]";
	}
	
	
	

}
