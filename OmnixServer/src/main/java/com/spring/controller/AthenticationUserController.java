package com.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.spring.entity.AuthenticationUser;
import com.spring.service.AuthenticationUserService;

@Controller
public class AthenticationUserController {

	@Autowired
	private AuthenticationUserService authenticationUserService;

	@RequestMapping(value = "/token/authUser", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticationUser authUser) {
		System.out.println("User: " + authUser);
		AuthenticationUser user = authenticationUserService.login(authUser.getUserName(), authUser.getPassword());
		if (user != null)
			return ResponseEntity.ok(new ObjectMapper().createObjectNode().put("token", user.getToken()));
		else
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/info/authUsers", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<AuthenticationUser>> getAllAuthenticationUsers() {
		List<AuthenticationUser> users = authenticationUserService.findAll();

		return ResponseEntity.ok(users);
	}

}
