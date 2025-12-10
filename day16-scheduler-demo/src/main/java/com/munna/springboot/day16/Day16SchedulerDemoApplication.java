package com.munna.springboot.day16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Day16SchedulerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day16SchedulerDemoApplication.class, args);
	}

}

// @EnableScheduling activates Spring scheduling.