package com.munna.springboot.day4.clean.repository;

import org.springframework.stereotype.Repository;

@Repository
public class GreetingRepository {
	public String getMessage() {
		return "Message from @Repository Layer!";
	}

}
