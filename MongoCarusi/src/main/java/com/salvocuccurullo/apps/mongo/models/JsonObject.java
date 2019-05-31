package com.salvocuccurullo.apps.mongo.models;

import java.util.HashMap;

public class JsonObject {

	String message = "";
	String result = "";
	HashMap<String,Object> payload = new HashMap<String,Object>();
	
	public JsonObject(String message, String result) {
		this.message = message;
		this.result = result;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public HashMap<String, Object> getPayload() {
		return payload;
	}

	public void setPayload(HashMap<String, Object> payload) {
		this.payload = payload;
	}
	
	
	
}
