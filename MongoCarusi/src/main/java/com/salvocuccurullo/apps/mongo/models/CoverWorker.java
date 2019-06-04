package com.salvocuccurullo.apps.mongo.models;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class CoverWorker {

	@Id
	private String id;
	
    @NotNull
    private String name;	
    
    @NotNull
    private String author;	
	
    private String fileName;
	private String type = "local";
	private short year;
	private String location;
	private String username;
	private boolean visibleToAll = true;
	private String spotifyUrl = "";
	private String spotifyAlbumUrl = "";
	private String review;
	private float vote;
	
	
	public CoverWorker(String fileName, String name, String author) {
		this.fileName = fileName;
		this.name = name;
		this.author = author;
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

	public String getSpotifyAlbumUrl() {
		return spotifyAlbumUrl;
	}

	public void setSpotifyAlbumUrl(String spotifyAlbumUrl) {
		this.spotifyAlbumUrl = spotifyAlbumUrl;
	}

    public String getReview() {
        return review;
    }


    public void setReview(String review) {
        this.review = review;
    }


    public float getVote() {
        return vote;
    }


    public void setVote(float vote) {
        this.vote = vote;
    }
}
