package com.munna.springboot.day20.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munna.springboot.day20.Entity.User;
import com.munna.springboot.day20.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;


	@PostMapping
	public User create(@RequestBody User user) {
	return userService.saveUser(user);
	}

}
