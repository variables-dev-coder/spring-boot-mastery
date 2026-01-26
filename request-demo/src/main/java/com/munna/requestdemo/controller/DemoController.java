package com.munna.requestdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoController {
	
	// Path Variable
    @GetMapping("/employees/{id}")
    public String getEmployeeById(@PathVariable int id) {
        return "Employee ID received: " + id;
    }

    // Request Param
    @GetMapping("/search")
    public String searchEmployee(
            @RequestParam String name,
            @RequestParam(required = false, defaultValue = "IT") String dept) {

        return "Name: " + name + ", Dept: " + dept;
    }

    // Request Header
    @GetMapping("/client-info")
    public String getClientInfo(
            @RequestHeader("User-Agent") String userAgent) {

        return "User-Agent: " + userAgent;
    }

    // All Together (REAL API)
    @GetMapping("/products/{id}")
    public String getProduct(
            @PathVariable int id,
            @RequestParam(defaultValue = "INR") String currency,
            @RequestHeader(value = "Authorization", required = false) String token) {

        return "Product ID: " + id +
               ", Currency: " + currency +
               ", Token: " + token;
    }

}
