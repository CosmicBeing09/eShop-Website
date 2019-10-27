package com.daraz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import com.daraz.obj.UserAccount;

public interface UserAccountRepo extends JpaRepository<UserAccount, String>{
	UserAccount findByPhoneAndPassword(String phone,String password);
	UserAccount findByPhone(String phone);
}
