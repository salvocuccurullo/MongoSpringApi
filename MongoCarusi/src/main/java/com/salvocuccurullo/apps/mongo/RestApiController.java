package com.salvocuccurullo.apps.mongo;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class RestApiController {

	@Autowired
	private CoverRepository repository;
	
	@Autowired
	private Environment env;
	
	private static Logger logger = LogManager.getLogger(RestApiController.class);

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
    			logger.info(cover.fileName + " -> " + cover.name);
    			logger.info("---------------------");
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
    		
    		logger.info("Get all covers: " + new Integer(covers.size()).toString() + " found.");
    		for (Cover cover: covers) {
    			logger.info("(" + cover.type + ") " + cover.fileName + " -> " + cover.name);
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
    		logger.info("New random cover requested: " + cover.fileName + " -> " + cover.name);
    		
    		return cover;
    }

    @RequestMapping("/getRemoteCovers")
    public ArrayList<Cover> 
    	getRemoteCovers(){
    	
    		ArrayList<Cover> covers = new ArrayList<Cover>();
    		
			String message = "";
			String result = "success";
    		
    		covers = (ArrayList<Cover>)repository.findByType("remote");
    		
    		return covers;
    }    
    
    
    @RequestMapping(value="/createCover", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonObject 
    	createCover( 
    				@RequestParam(value="coverName", defaultValue="") String coverName,
    				@RequestParam(value="fileName", defaultValue="") String fileName,
    				@RequestParam(value="author", defaultValue="") String author,
    				@RequestParam(value="year", defaultValue="0") short year
    			){
    	
    			String message = "Document successfully created on MongoDB.";
    			String result = "success";
    	
    			if (coverName.equals("") || fileName.equals("")) {
        			message = "Cover name and/or file Name cannot be blank!";
        			result = "failure";	
    			}
    			else {
	    			try {
	    				String remotePath = env.getProperty("remote.repo.baseurl","");
	    				Cover cover = new Cover(fileName, coverName, author);
	    				cover.setLocation(remotePath+fileName);
	    				cover.setType("remote");
	    				cover.setYear(year);
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
    
	@RequestMapping(value = "/createCover2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public JsonObject 
		createCover2(@RequestBody Cover cover, UriComponentsBuilder ucBuilder) {
	    logger.info("Creating Cover : {}", cover);
	
	    String message = "Document successfully created on MongoDB.";
		String result = "success";

		if (cover.name.equals("") || cover.fileName.equals("")) {
			message = "Cover name and/or file Name cannot be blank!";
			result = "failure";	
		}
		else {
			try {
				String remotePath = env.getProperty("remote.repo.baseurl","");
				Cover ncover = new Cover(cover.fileName, cover.name, cover.author);
				ncover.setLocation(remotePath + cover.fileName);
				ncover.setType("remote");
				ncover.setYear(cover.year);
				ncover.setUsername(cover.username);
				repository.save(ncover);
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

