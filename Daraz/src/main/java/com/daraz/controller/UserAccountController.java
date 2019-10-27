package com.daraz.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.daraz.obj.Product_temp;
import com.daraz.obj.UploadFileResponse;
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
	
	@CrossOrigin
	@GetMapping("/alluser")
	public List<UserAccount> getAllUser() {
		return userService.getAllUser();
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.DELETE , value = "/deleteUser/{phone}")
	public String deleteOne(@PathVariable String phone){
		String result = userService.deleteOneUser(phone);
		return result;
	}
	
	@CrossOrigin
	@PutMapping("/updateAccountStatus")
    public String updateUserStatus(@RequestPart("user")UserAccount userAccount) {
        return userService.updateUser(userAccount);
    }
	
	
}
