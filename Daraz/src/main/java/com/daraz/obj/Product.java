package com.daraz.obj;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.core.SerializableString;

@Entity
@Table(name = "featured_product")
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	int product_id;
	String product_name, description;
	int price, discount_price, inventory;
	Boolean is_discount;
	String image1, image2, image3, coverimage, smallimage1, smallimage2, smallimage3;

	public Product(String product_name, String description, int price, int discount_price, int inventory,
			Boolean is_discount, String image1, String image2, String image3, String coverimage, String smallimage1,
			String smallimage2, String smallimage3) {
//		super();
		this.product_name = product_name;
		this.description = description;
		this.price = price;
		this.discount_price = discount_price;
		this.inventory = inventory;
		this.is_discount = is_discount;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.coverimage = coverimage;
		this.smallimage1 = smallimage1;
		this.smallimage2 = smallimage2;
		this.smallimage3 = smallimage3;
	}

	public Product(int product_id, String product_name, String description, int price, int discount_price,
			int inventory, Boolean is_discount, String image1, String image2, String image3, String coverimage,
			String smallimage1, String smallimage2, String smallimage3) {
//		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.description = description;
		this.price = price;
		this.discount_price = discount_price;
		this.inventory = inventory;
		this.is_discount = is_discount;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
		this.coverimage = coverimage;
		this.smallimage1 = smallimage1;
		this.smallimage2 = smallimage2;
		this.smallimage3 = smallimage3;
	}

	public Product() {
//		super();
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscount_price() {
		return discount_price;
	}

	public void setDiscount_price(int discount_price) {
		this.discount_price = discount_price;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public Boolean getIs_discount() {
		return is_discount;
	}

	public void setIs_discount(Boolean is_discount) {
		this.is_discount = is_discount;
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

	public String getCoverimage() {
		return coverimage;
	}

	public void setCoverimage(String coverimage) {
		this.coverimage = coverimage;
	}

	public String getSmallimage1() {
		return smallimage1;
	}

	public void setSmallimage1(String smallimage1) {
		this.smallimage1 = smallimage1;
	}

	public String getSmallimage2() {
		return smallimage2;
	}

	public void setSmallimage2(String smallimage2) {
		this.smallimage2 = smallimage2;
	}

	public String getSmallimage3() {
		return smallimage3;
	}

	public void setSmallimage3(String smallimage3) {
		this.smallimage3 = smallimage3;
	}

}
