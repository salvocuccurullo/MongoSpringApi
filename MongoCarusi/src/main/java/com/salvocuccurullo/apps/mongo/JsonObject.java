package com.salvocuccurullo.apps.mongo;

public class JsonObject {

	String message = "";
	String result = "";
	
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
	
	
	
}
