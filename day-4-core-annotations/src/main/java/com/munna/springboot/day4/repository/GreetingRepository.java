package com.munna.springboot.day4.repository;

import org.springframework.stereotype.Repository;

@Repository
public class GreetingRepository {
	public String getMessage() {
		return "Hello from @Repository Bean!";
		
	}

}
