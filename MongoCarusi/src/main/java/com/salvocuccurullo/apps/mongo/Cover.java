package com.salvocuccurullo.apps.mongo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.data.annotation.Id;

public class Cover {

	@Id
	public String id;
	
	public String fileName;
	public String name;
	public String type = "local";
	public String author;
	public short year;
	public String location;
	public String created;
	
	public Cover() {
		
	}
	
	public Cover(String fileName, String name, String author) {
		this.fileName = fileName;
		this.name = name;
		this.author = author;
		this.created = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
	}
	
    @Override
    public String toString() {
        return String.format(
                "Cover[id=%s, fileName='%s', name='%s']",
                id, fileName, name);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public short getYear() {
		return year;
	}

	public void setYear(short year) {
		this.year = year;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
}
