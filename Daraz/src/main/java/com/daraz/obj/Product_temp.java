package com.daraz.obj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "product")
public class Product_temp {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(columnDefinition = "serial")
private String id;
private String name;
private String category;
private String details;
private String price;
private String discountPrice;
private String brand;
private String model;
private String height;
private String width;
private String weight;
private String warrenty;
private String image1;
private String image2;
private String image3;
public Product_temp(String id, String name, String category, String details, String price, String discountPrice,
		String brand, String model, String height, String width, String weight, String warrenty, String image1,
		String image2, String image3) {
	super();
	this.id = id;
	this.name = name;
	this.category = category;
	this.details = details;
	this.price = price;
	this.discountPrice = discountPrice;
	this.brand = brand;
	this.model = model;
	this.height = height;
	this.width = width;
	this.weight = weight;
	this.warrenty = warrenty;
	this.image1 = image1;
	this.image2 = image2;
	this.image3 = image3;
}
public Product_temp() {
	super();
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getDetails() {
	return details;
}
public void setDetails(String details) {
	this.details = details;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public String getDiscountPrice() {
	return discountPrice;
}
public void setDiscountPrice(String discountPrice) {
	this.discountPrice = discountPrice;
}
public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	this.brand = brand;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
public String getHeight() {
	return height;
}
public void setHeight(String height) {
	this.height = height;
}
public String getWidth() {
	return width;
}
public void setWidth(String width) {
	this.width = width;
}
public String getWeight() {
	return weight;
}
public void setWeight(String weight) {
	this.weight = weight;
}
public String getWarrenty() {
	return warrenty;
}
public void setWarrenty(String warrenty) {
	this.warrenty = warrenty;
}
public String getImage1() {
	return image1;
}
public void setImage1(String image1) {
	this.image1 = image1;
}
public String getImage2() {
	return image2;
}
public void setImage2(String image2) {
	this.image2 = image2;
}
public String getImage3() {
	return image3;
}
public void setImage3(String image3) {
	this.image3 = image3;
}

}
