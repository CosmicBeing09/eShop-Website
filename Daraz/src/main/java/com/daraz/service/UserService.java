package com.daraz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daraz.obj.UserAccount;
import com.daraz.repo.ProductRepo;
import com.daraz.repo.UserAccountRepo;

@Service
public class UserService {
	@Autowired
	private UserAccountRepo userAccountRepo;
	
	public UserAccount login(UserAccount user) {
		UserAccount resUser = new UserAccount(0,"NULL","NULL");
		UserAccount resUser1 = userAccountRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if(resUser1 != null) {
			resUser = resUser1;
		}
		return resUser;
		
	}
	
}
