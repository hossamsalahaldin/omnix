package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.entity.AuthenticationUser;
import com.spring.entity.User;
import com.spring.service.AuthenticationUserService;
import com.spring.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/api/addUser")
	public ResponseEntity addUser(@RequestBody User user) {
		System.out.println("User: " + user);
		User savedUser = userService.addUser(user);
		System.out.println("savedUser: " + savedUser);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/info/users")
	@ResponseBody
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	

}
