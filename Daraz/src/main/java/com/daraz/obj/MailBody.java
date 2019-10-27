package com.daraz.obj;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mail_body")
public class MailBody {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(columnDefinition = "serial")
private String id;
private String address;
private String productName;
private String phoneNo;
private String price;
private Date date;
private String shopName;


public MailBody(String id, String address, String productName, String phoneNo, String price, Date date,String shopName) {
	super();
	this.id = id;
	this.address = address;
	this.productName = productName;
	this.phoneNo = phoneNo;
	this.price = price;
	this.date = date;
	this.shopName = shopName;
}

public MailBody() {
	super();
}

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getPhoneNo() {
	return phoneNo;
}
public void setPhoneNo(String phoneNo) {
	this.phoneNo = phoneNo;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}

public String getPrice() {
	return price;
}

public void setPrice(String price) {
	this.price = price;
}

public String getShopName() {
	return shopName;
}

public void setShopName(String shopName) {
	this.shopName = shopName;
}

}
