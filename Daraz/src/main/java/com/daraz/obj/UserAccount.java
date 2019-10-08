package com.daraz.obj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "useraccount")
public class UserAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private int id;
	
	private String username,password;
	
	
	public UserAccount(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	
	public UserAccount(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}


	public UserAccount() {
		
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
