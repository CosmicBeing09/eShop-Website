package com.daraz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.daraz.obj.UserAccount;
import com.daraz.repo.UserAccountRepo;
import com.daraz.service.UserService;

@RestController
public class UserAccountController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserAccountRepo userAccountRepo;
	
	@CrossOrigin
	@PostMapping("/login")
	public UserAccount login(@RequestBody UserAccount user) {
		UserAccount user1 = userService.login(user);
		return user1;
	}
	
	@CrossOrigin
	@PostMapping("/reg")
	public UserAccount reg(@RequestBody UserAccount user) {
		UserAccount user1 = userService.registration(user);
		return user1;
	}
	
	
}
