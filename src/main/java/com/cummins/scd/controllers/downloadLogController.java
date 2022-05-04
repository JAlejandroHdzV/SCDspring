package com.cummins.scd.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = {"http://localhost:4200"}) //descomentar cuando se haga deploy local. comentar las demas.
@CrossOrigin(origins = {"https://mxca-abomsd-70-dev-ac.cummins.com"}) //descomentar cuando se haga deploy a dev. comentar las demas.

@RestController
@RequestMapping("/download")
public class downloadLogController {
	@Value("${catalina.home}")
    private String catalinaHome;
	
	@GetMapping("log")
    public ResponseEntity<Object> downloadLogFile(){
		
		System.out.println("Entro a downloadLogFile");
		//StringBuilder filecontent= new StringBuilder();
		try {
		//filecontent.append("hola mundo");
		String fileName="log.txt";
		
		File log = ResourceUtils.getFile(catalinaHome+"/logs/DES.log");

		//Read File Content
		String content = new String(Files.readAllBytes(log.toPath()));
		//System.out.println(content);
		
		
		
		FileWriter filewriter= new FileWriter(fileName);
		filewriter.write(content.toString());
		filewriter.flush();
		
		File file= new File(fileName);
		InputStreamResource resource= new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers= new HttpHeaders();
		
		   headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		   headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		   headers.add("Pragma", "no-cache");
		   headers.add("Expires", "0");
		      
		   ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(
		      MediaType.parseMediaType("application/txt")).body(resource);
		   return responseEntity;
		}catch(Exception e) {
			
			
		}
		return null;
	}
}


