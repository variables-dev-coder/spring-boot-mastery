package com.springBoot2.hello;

import java.io.IOException;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CustomErrorController implements ErrorController {
	
	public void handleError(HttpServletResponse response) throws IOException{
		response.getWriter().write("Oops ! Page not found. Try a Valid endpoint.");
		
	}

}
