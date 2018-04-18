package com.salvocuccurullo.apps.mongo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoverRepository extends MongoRepository<Cover, String> {

	public Cover findByFileName(String fileName);
    public List<Cover> findByName(String name);
    public List<Cover> findAll();
    public List<Cover> findByType(String type);
    public Cover findById(String id);
	
}
