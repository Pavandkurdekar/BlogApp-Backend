package com.pavan.BloggApp.BloggApp.Exceptions;

public class CustomExceptions extends Exception{
	
	public String getMessageforuser(int id)
	{
		return "User With Id "+id+" Not Found";
	}
	
	
	public String getMessageforcategory(int id)
	{
		return "Category With Id "+id+" Not Found";
	}

}
