package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.entity.AuthenticationUser;
import com.spring.service.AuthenticationUserService;

@Controller 
public class AthenticationUserController {

	@Autowired
	private AuthenticationUserService authenticationUserService;
	
	@RequestMapping(value = "/token/authUser", method = RequestMethod.POST, produces = {"application/json"})
	@ResponseBody
	public AuthenticationUser authenticateUser(@RequestBody AuthenticationUser authUser) {
		System.out.println("User: " + authUser);
		AuthenticationUser user = authenticationUserService.login(authUser.getUserName(), authUser.getPassword());
		
		return user;
	}
	
	@RequestMapping(value = "/info/authUsers", method = RequestMethod.GET)
	@ResponseBody
	public List<AuthenticationUser> getAllAuthenticationUsers() {
		List<AuthenticationUser> users = authenticationUserService.findAll();
		
		return users;
	}
	
}
