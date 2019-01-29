package com.salvocuccurullo.apps.mongo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    		logger.info("Get covers by name called. Query param: " + coverName);
    		covers = (ArrayList<Cover>)repository.findByName(coverName);
    		logger.info("Get covers by name result:" + new Integer(covers.size()).toString() + " covers found.");

    		for (Cover cover: covers) {
    			logger.debug(cover.getFileName() + " -> " + cover.getName());
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
    			logger.debug("(" + cover.getType() + ") " + cover.getFileName() + " -> " + cover.getName());
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
    		logger.info("New random cover requested: " + cover.getFileName() + " -> " + cover.getName());
    		
    		return cover;
    }

    @RequestMapping("/getRemoteCovers")
    public ArrayList<Cover> 
    	getRemoteCovers(){
    	
    		ArrayList<Cover> covers = new ArrayList<Cover>();
    		
			String message = "";
			String result = "success";
			
			logger.info("Get remote covers called.");
    		covers = (ArrayList<Cover>)repository.findByType("remote");
    		
    		return covers;
    }

    @RequestMapping("/getStats")
    public JsonObject 
    	getStats(){

    	logger.info("Get stats called.");
    	
		String message = "";
		String result = "success";
	
		JsonObject res = new JsonObject(message, result);
		HashMap<String,Object> payload = new HashMap<String,Object>();

		try {
			ArrayList<Cover> covers = (ArrayList<Cover>)repository.findByType("remote");
			payload.put("remote_covers", covers.size());

			covers = (ArrayList<Cover>)repository.findByType("local");
			payload.put("local_covers", covers.size());

			covers = (ArrayList<Cover>)repository.findByNullYearQuery();
			payload.put("covers_null_year", covers.size());

			covers = (ArrayList<Cover>)repository.findByNullUserQuery();
			payload.put("covers_null_user", covers.size());
			
		}
		catch(Exception eee){
			logger.error(eee.toString());
			message = "failure";
			result = eee.toString();
		}

		res.setMessage(message);
		res.setResult(result);
		res.setPayload(payload);
		
		return res;
    }
        
	@RequestMapping(value = "/createCover2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public JsonObject 
		createCover2(@RequestBody Cover cover, UriComponentsBuilder ucBuilder) {
	    
		logger.info("Create/Update Cover called");
	
	    String message = "Document successfully created on MongoDB.";
		String result = "success";

		if (cover.isNullOrEmpty()) {				// GENERAL CHECK NAME AND AUTHOR ARE MANDATORY
			message = "Cover name and author cannot be blank!";
			result = "failure";	
		}
		else if ( cover.idNullButFileName() ) {		// NEW COVER CASE - FILE NAME CANNOT BE NULL
			message = "Cover file Name cannot be blank!";
			result = "failure";	
		}
		else {
			try {
				
				String remotePath = env.getProperty("remote.repo.baseurl","");
				Cover e_cover = (Cover)repository.findById(cover.getId());
				
				if (e_cover != null) {						// UPDATE CASE
					
						String location = "";
						String fileName = "";
						short year = 0;
						if (cover.getFileName() != null && !cover.getFileName().equals("")) {
							fileName = cover.getFileName();
							location = remotePath + cover.getFileName();
						}
						else {
							fileName = e_cover.getFileName();
							location = e_cover.getLocation();
						}
						
						if (cover.getYear() != 0)
							year = cover.getYear();
						else
							year = e_cover.getYear();

						e_cover.setName(cover.getName());
						e_cover.setAuthor(cover.getAuthor());
						e_cover.setFileName(fileName);
						e_cover.setLocation(location);
						e_cover.setUsername(cover.getUsername());
						e_cover.setYear(year);
						e_cover.setUpdate_ts(new Date());
						repository.save(e_cover);
						message = "Document successfully updated on MongoDB.";
				}
				else {										// INSERT CASE
					Cover ncover = new Cover(cover.getFileName(), cover.getName(), cover.getAuthor());
					ncover.setLocation(remotePath + cover.getFileName());
					ncover.setType("remote");
					ncover.setYear(cover.getYear());
					ncover.setUsername(cover.getUsername());
					repository.save(ncover);
				}
			}
			catch(Exception eee) {
				logger.error(eee.toString());
    			message = eee.toString();
    			result = "failure";	
			}
		}
		
		JsonObject json = new JsonObject(message,result);
	    		
	return json;

	}
}

