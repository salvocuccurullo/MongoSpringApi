package com.salvocuccurullo.apps.mongo;

import org.springframework.data.annotation.Id;

public class Cover {

	@Id
	public String id;
	
	public String fileName;
	public String name;
	public String type;
	public String author;
	public short year;
	public String location;
	
	public Cover() {
		
	}
	
	public Cover(String fileName, String name) {
		this.fileName = fileName;
		this.name = name;
	}
	
    @Override
    public String toString() {
        return String.format(
                "Cover[id=%s, fileName='%s', name='%s']",
                id, fileName, name);
    }

	
}
