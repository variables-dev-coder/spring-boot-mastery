package com.springBoot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringBoot2Application {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringBoot2Application.class, args);
	}

}


/*
 
 Day 2: Project Structure & Main Class
 -------------------------------------
 
 1. Project Structure in Spring Boot

When you generate a Spring Boot project using Spring Initializr 
(Maven), you’ll see a standard folder layout:


hello-spring-boot2/
 └── src/
      ├── main/
      │    ├── java/
      │    │     └── com/hellospringBoot2/
      │    │           ├── HelloSpringBoot2Application.java   <-- Main class
      │    │           └── HelloController.java              <-- Your REST controller
      │    │
      │    └── resources/
      │          ├── static/         <-- for static files (HTML, CSS, JS, images)
      │          ├── templates/      <-- for Thymeleaf/JSP templates
      │          ├── application.properties  <-- main config file
      │          └── application.yml         <-- optional config format
      │
      └── test/
           └── java/   <-- unit & integration tests
           
Why important?
--------------
src/main/java → contains all your Java code (controllers, services, repositories).
src/main/resources → contains non-code resources like properties, templates, static files.
application.properties → your main configuration hub (server port, db, etc).


2. The Main Class (@SpringBootApplication)

Example auto-generated main class: -> Main Class


What happens here?
------------------
-> @SpringBootApplication → combo of 3 annotations:
	1. @SpringBootConfiguration → makes this class a config class (like @Configuration).
	2. @EnableAutoConfiguration → auto-configures beans (e.g., sets up Tomcat, DispatcherServlet).
	3. @ComponentScan → scans your package (com.hellospringBoot2) for components 
	   (@RestController, @Service, @Repository).

SpringApplication.run() → boots the app:
	1. Starts Spring Context (IoC container).
	2. Auto-configures beans.
	3. Starts embedded Tomcat server.

This is why you can just run the main() method and your app works like a standalone executable.
 
 
 
3. Run App from IDE vs Maven
----------------------------
From IDE (STS/IntelliJ)
	Right-click → Run As → Spring Boot App
	IDE runs main() directly.
	From Maven
	Run in terminal:
	mvn spring-boot:run

This uses Maven to start the application.

Both ways do the same thing: start your main class, launch embedded Tomcat, and load beans.



4. Practice: Changing Server Port
---------------------------------
	By default, Spring Boot apps run on port 8080.
	To change:

Method 1: application.properties

Open src/main/resources/application.properties and add:
server.port=9090

Restart app → now it runs on http://localhost:9090
.

Method 2: application.yml

Alternatively, use YAML format:

server:
  port: 9090
  
  
How you can disable the Whitelabel Error Page completely (so Spring Boot won’t show it even 
if no controller matches):

-------------------------------------


Option 1: Disable via application.properties

Add this line inside src/main/resources/application.properties:

server.error.whitelabel.enabled=false


If you now visit a non-existing URL, you’ll get a plain 404 status instead of the Whitelabel page.


Option 2: Create Your Own Error Page

Instead of the default, you can define a custom error controller:

package com.example.hello;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public void handleError(HttpServletResponse response) throws IOException {
        response.getWriter().write("❌ Oops! Page not found. Try a valid endpoint.");
    }
}

-----------------------------------


Now you have 3 choices:
Keep Whitelabel page (default).
Disable it with properties.
Replace it with your own custom error page.


 */
