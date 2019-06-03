package com.salvocuccurullo.apps.mongo.models;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable{

	private String username;
	private String review;
	private Date created;
	private Date updated;
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
	
	
}
