package com.daraz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import com.daraz.obj.UserAccount;

public interface UserAccountRepo extends JpaRepository<UserAccount, Integer>{
	UserAccount findByUsernameAndPassword(String username,String password);
}
