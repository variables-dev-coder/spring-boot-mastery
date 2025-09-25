package com.munna.springboot.day5.dto;

public class UpdateTodoRequest {
	private String title;         // optional
    private String description;   // optional
    private Boolean completed;    // optional

    public UpdateTodoRequest() {}

    // getters & setters
    public String getTitle() {
    	return title; 
    	}
    public void setTitle(String title) { 
    	this.title = title; 
    	}
    public String getDescription() {
    	return description; 
    	}
    public void setDescription(String description) {
    	this.description = description;
    	}
    public Boolean getCompleted() {
    	return completed;
    	}
    public void setCompleted(Boolean completed) {
    	this.completed = completed;
    	}
}
