package com.munna.springboot.day5.dto;



public class CreateTodoRequest {
	
	@NotBlank(message = "title is required")
    private String title;
    private String description;

    public CreateTodoRequest() {}
    public CreateTodoRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }

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
}

