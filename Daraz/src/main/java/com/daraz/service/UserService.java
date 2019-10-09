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
		UserAccount resUser1 = userAccountRepo.findByPhoneAndPassword(user.getPhone(), user.getPassword());
		if(resUser1 != null) {
			resUser = resUser1;
		}
		return resUser;
	}
	
	public UserAccount registration (UserAccount user1) {
		UserAccount user2= userAccountRepo.findByPhone(user1.getPhone());
		if( user1.getName() == "" || user1.getPhone() == "" || user1.getShopname() == "" || user1.getPassword() == "") {
			return new UserAccount("NULL","NULL");
		}
		else if(user2 == null ) {
			userAccountRepo.save(user1);
			return user1;
		}
		else {
			return new UserAccount("NULL","NULL");
		}
		
	}
	
	
}
