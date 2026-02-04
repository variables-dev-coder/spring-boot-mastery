package com.munna.exceptiondemo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    private String email;

    public String getName() {
    	return name; 
    	}
    
    public String getEmail() {
    	return email; 
    	}

    public void setName(String name) {
    	this.name = name; 
    	}
    
    public void setEmail(String email) {
    	this.email = email; 
    	}
}
