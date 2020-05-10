package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private AuthenticationUserService authenticationUserService;
	
	@PostMapping("/api/addUser")
	public String addUser(@RequestBody User user) {
		System.out.println("User: " + user);
		User savedUser = userService.addUser(user);
		System.out.println("savedUser: " + savedUser);
		return "Successfull";
	}
	
	@GetMapping("/info/users")
	@ResponseBody
	public List<User> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return users;
	}
	
	@RequestMapping(value = "/token/authUser", method = RequestMethod.POST, produces = {"application/json"})
	@ResponseBody
	public AuthenticationUser authenticateUser(@RequestBody AuthenticationUser authUser) {
		System.out.println("User: " + authUser);
		AuthenticationUser user = authenticationUserService.login(authUser.getUserName(), authUser.getPassword());
		
		return user;
	}

}
