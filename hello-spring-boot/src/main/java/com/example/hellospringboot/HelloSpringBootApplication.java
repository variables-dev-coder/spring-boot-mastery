package com.example.hellospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringBootApplication.class, args);
	}

}

/*
Step 1: Understand Spring Boot vs Spring Framework

Spring Framework → Core framework, requires lots of XML/configurations, 
	boilerplate code. You manually configure beans, servlet container, dependencies.

Spring Boot → Built on top of Spring Framework, but auto-configures everything.
	No XML needed (uses annotations).
	Comes with an embedded server (Tomcat by default).
	Provides Spring Initializr to quickly bootstrap projects.
	Production-ready features: Actuator, metrics, health checks.

Think of it as:
Spring = raw ingredients
Spring Boot = ready-made meal with everything included 🍲


 

*/