package com.salvocuccurullo.apps.mongo.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Review implements Serializable{

	private String username;
	private String review;
	private float vote;
	private Date created;
	private Date updated;
	
    public Review() {
        super();
        this.created = new Date();
        this.updated = new Date();
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getReview() {
        return review;
    }
    public void setReview(String review) {
        this.review = review;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getUpdated() {
        return updated;
    }
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    public float getVote() {
        return vote;
    }
    public void setVote(float vote) {
        this.vote = vote;
    }
	
	
}
