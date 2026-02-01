package com.munna.validationdemo.dto;

import jakarta.validation.constraints.*;

public class UserRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(
        regexp = "^[0-9]{10}$",
        message = "Mobile number must be exactly 10 digits"
    )
    private String mobile;

    @Min(value = 18, message = "Age must be 18 or above")
    private int age;

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
}
