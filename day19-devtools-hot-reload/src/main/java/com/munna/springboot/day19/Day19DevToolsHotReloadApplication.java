package com.munna.springboot.day19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day19DevToolsHotReloadApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day19DevToolsHotReloadApplication.class, args);
	}

}


/*
 
 Dependencies

Spring Web
Spring Boot DevTools


day19-devtools-hot-reload
 └── src
     └── main
         ├── java
         │   └── com.munna.springboot.day19
         │       ├── Day19DevToolsHotReloadApplication.java
         │       └── controller
         │           └── TestController.java
         └── resources
             └── application.properties


Why runtime scope?
DevTools:
	Active only during development
	Automatically excluded from production builds



=> What is Spring Boot DevTools?
	DevTools is a developer productivity tool that:
		Detects classpath changes
		Restarts application automatically
		Enables faster development feedback

=> How Hot Reload Works Internally?
		Spring Boot uses two classloaders:
		
| ClassLoader         | Responsibility              |
| ------------------- | --------------------------- |
| Base ClassLoader    | Loads stable dependencies   |
| Restart ClassLoader | Loads your application code |


=> When you change code:
		Only restart classloader reloads
		JVM is NOT restarted
		Faster than normal restart
		
=> Restart vs LiveReload

| Feature    | Description                         |
| ---------- | ----------------------------------- |
| Restart    | Java / config change → auto restart |
| LiveReload | HTML / CSS change → browser refresh |
| DevTools   | Controls both                       |


=> Important Rules (Interview Focus)

		DevTools disabled in production
		Not active when running:
		
java -jar app.jar

Best for REST API + UI dev



 */
