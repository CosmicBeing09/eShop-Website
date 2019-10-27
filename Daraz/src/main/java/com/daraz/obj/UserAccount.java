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
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private int id;
	@Id
	private String phone;
	private String name,shopname,password;
	
	private String type,status;
	
	
	public UserAccount(int id, String name, String phone, String shopname, String password, String type,
			String status) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.shopname = shopname;
		this.password = password;
		this.type = type;
		this.status = status;
	}


	public UserAccount(String phone, String password) {
		this.phone = phone;
		this.password = password;
	}
	
	
	public UserAccount(int id, String phone, String password) {
		this.id = id;
		this.phone = phone;
		this.password = password;
	}


	public UserAccount() {
		
	}


	public UserAccount(int id, String name, String phone, String shopname, String password) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.shopname = shopname;
		this.password = password;
	}


	public UserAccount(String name, String phone, String shopname, String password) {
		this.name = name;
		this.phone = phone;
		this.shopname = shopname;
		this.password = password;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getShopname() {
		return shopname;
	}


	public void setShopname(String shopname) {
		this.shopname = shopname;
	}


	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
}
