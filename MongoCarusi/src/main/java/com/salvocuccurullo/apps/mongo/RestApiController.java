package com.salvocuccurullo.apps.mongo;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

	@Autowired
	private CoverRepository repository;

    @RequestMapping("/getCovers")
    public ArrayList<Cover> 
    	getCovers( @RequestParam(value="coverName", defaultValue="") String coverName){
    	
    		ArrayList<Cover> covers = new ArrayList<Cover>();
    		
			String message = "";
			String result = "success";
    		
    		if (coverName.equals("")) {
    			return covers;
    		}
    	
    		covers = (ArrayList<Cover>)repository.findByName(coverName);
    		
    		for (Cover cover: covers) {
    			System.out.println(cover.fileName + " -> " + cover.name);
    			System.out.println("---------------------");
    		}
    		
    		return covers;
    }

    @RequestMapping("/getAllCovers")
    public ArrayList<Cover> 
    	getAllCovers(){
    	
    		ArrayList<Cover> covers = new ArrayList<Cover>();
    		
			String message = "";
			String result = "success";
    		
    		covers = (ArrayList<Cover>)repository.findAll();
    		
    		for (Cover cover: covers) {
    			System.out.println(cover.fileName + " -> " + cover.name);
    			System.out.println("---------------------");
    		}
    		
    		return covers;
    }    

    @RequestMapping("/getRandomCover")
    public Cover 
    	getRandomCover(){
    	
    		ArrayList<Cover> covers = new ArrayList<Cover>();
    		
			String message = "";
			String result = "success";
    		
    		covers = (ArrayList<Cover>)repository.findByType("remote");
    		
    		if (covers.size()==0)
    			return null;
    		
    		int randomNum = ThreadLocalRandom.current().nextInt(0, covers.size());
    		
    		Cover cover = covers.get(randomNum);
    		System.out.println("------- Random Cover -----");
    		System.out.println(cover.fileName + " -> " + cover.name);
    		System.out.println("---------------------");
    		
    		return cover;
    }
    
    @RequestMapping(value="/createCover", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonObject 
    	createCover( 
    				@RequestParam(value="coverName", defaultValue="") String coverName,
    				@RequestParam(value="fileName", defaultValue="") String fileName,
    				@RequestParam(value="author", defaultValue="") String author
    			){
    	
    			String message = "Document successfully created on MongoDB.";
    			String result = "success";
    	
    			if (coverName.equals("") || fileName.equals("")) {
        			message = "Cover name and/or file Name cannot be blank!";
        			result = "failure";	
    			}
    			else {
	    			try {
	    				Cover cover = new Cover(fileName, coverName, author);
	    				cover.setLocation(fileName);
	    				cover.setType("remote");
	    				repository.save(cover);
	    			}
	    			catch(Exception eee) {
	        			message = eee.toString();
	        			result = "failure";	
	    			}
    			}
    			
    			JsonObject json = new JsonObject(message,result);
    		    		
    		return json;
    }    
    
}

