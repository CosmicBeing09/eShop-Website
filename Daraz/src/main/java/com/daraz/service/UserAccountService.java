package com.daraz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daraz.obj.UserAccount;
import com.daraz.repo.UserAccountRepo;

@Service
public class UserAccountService {
@Autowired
private UserAccountRepo userAccountRepo;
public List<UserAccount> getAllUser(){
	List<UserAccount> all = new ArrayList<UserAccount>();
	userAccountRepo.findAll().forEach(all::add);;
	return all;
}
public String deleteOneUser(int id) {
	userAccountRepo.delete(id);
	return "Successfull";
}
}
