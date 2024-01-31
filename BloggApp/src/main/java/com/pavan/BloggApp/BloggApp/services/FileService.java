package com.pavan.BloggApp.BloggApp.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
	
	public String uploadimage(String path, MultipartFile file) throws IOException
	{
		
		
		//EXTRACT FILE NAME FROM THE FILE
		String name = file.getOriginalFilename();
		
		
		//GENERATE RANDOM FILE NAME
		String randomid = UUID.randomUUID().toString();		
		String filename1 = randomid.concat(name.substring(name.lastIndexOf(".")));
		
		
		
		//CREATE FULL FILE PATH TO SAVE THE FILE
		String filepath = path + File.separator + filename1;
		
		
		//CREATE FOLDER IF NOT CREATED
		File f = new File(path);
		if(!f.exists())
		{
			f.mkdir();
		}
		
		
		//COPY FILE
		Files.copy(file.getInputStream(), Paths.get(filepath));
		
		
		System.out.println(filepath);
		return filename1;
	}
	
	
	//RETURN IMAGE
	public InputStream getimage(String path, String filename) throws FileNotFoundException
	{
		String fullpath = path + File.separator + filename;
		InputStream is = new FileInputStream(fullpath);
		
		return is;
		
	}
	
	

}
