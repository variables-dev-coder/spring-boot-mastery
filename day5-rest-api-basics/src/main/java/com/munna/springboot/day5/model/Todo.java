package com.munna.springboot.day5.model;

public class Todo {
	private Long id;
    private String title;
    private String description;
    private boolean completed;

    public Todo() {}

    public Todo(Long id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    // getters & setters
    public Long getId() {
    	return id; 
    	}
    
    public void setId(Long id) {
    	this.id = id; 
    	}
    
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
    
    public boolean isCompleted() {
    	return completed; 
    	}
    
    public void setCompleted(boolean completed) {
    	this.completed = completed; 
    	}
}
