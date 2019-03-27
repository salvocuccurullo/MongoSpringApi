package com.salvocuccurullo.apps.mongo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.data.annotation.Id;

public class Cover {

	@Id
	private String id;
	
	private String fileName;
	private String name;
	private String type = "local";
	private String author;
	private short year;
	private String location;
	private String created;
	private String username;
	private boolean visibleToAll = true;
	private String spotifyUrl = "";
	
	private Date create_ts;
	private Date update_ts;
	
	public Cover() {
		this.created = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
	}
	
	public Cover(String fileName, String name, String author) {
		this.fileName = fileName;
		this.name = name;
		this.author = author;
		this.create_ts = new Date();
		this.update_ts = new Date();
		this.created = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
	}
	
	
	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public Date getCreate_ts() {
		return create_ts;
	}

	public void setCreate_ts(Date create_ts) {
		this.create_ts = create_ts;
	}

	public Date getUpdate_ts() {
		return update_ts;
	}

	public void setUpdate_ts(Date update_ts) {
		this.update_ts = update_ts;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isNullOrEmpty() {
		return this.name == null || this.author == null || this.name.equals("") || this.author.equals("");
	}
	
	public boolean idNullButFileName() {
		return (this.id == null || this.id.equals("")) && ( this.fileName == null || this.fileName.equals(""));
	}
	
	public boolean isVisibleToAll() {
		return visibleToAll;
	}

	public void setVisibleToAll(boolean visibleToAll) {
		this.visibleToAll = visibleToAll;
	}

	public String getSpotifyUrl() {
		return spotifyUrl;
	}

	public void setSpotifyUrl(String spotifyUrl) {
		this.spotifyUrl = spotifyUrl;
	}
}
