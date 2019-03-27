package com.salvocuccurullo.apps.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CoverRepository extends MongoRepository<Cover, String> {

	public Cover findByFileName(String fileName);
    public List<Cover> findByName(String name);
    public List<Cover> findAll();
    public List<Cover> findByType(String type);
    public Cover getById(String id);
    
    @Query("{year : 0}")
    public List<Cover> findByNullYearQuery();

    @Query("{username : null}")
    public List<Cover> findByNullUserQuery();

    @Query("{ 'name': ?0, 'author': ?1}")
    public List<Cover> findByNameAndAuthor(String name, String author);
    
    /* Example
    	@Query("{ 'campaignId': ?0, 'options.path': ?1}")
    	public Dialog findByIdAndPath(String id, String path);
    */
    
}

