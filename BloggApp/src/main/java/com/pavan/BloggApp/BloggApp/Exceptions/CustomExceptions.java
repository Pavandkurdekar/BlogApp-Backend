package com.pavan.BloggApp.BloggApp.Exceptions;

@SuppressWarnings("serial")
public class CustomExceptions extends Exception{
	

	public String getMessageforuser(int id)
	{
		return "User With Id "+id+" Not Found!";
	}
	
	
	public String getMessageforcategory(int id)
	{
		return "Category With Id "+id+" Not Found!";
	}
	
	
	public String getMessageforpost(int id)
	{
		return "Post With Id "+id+" Not Found!";
	}

}
