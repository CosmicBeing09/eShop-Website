package com.daraz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daraz.obj.Product;
import com.daraz.repo.FeaturedProductRepo;


@Service
public class FeaturedProductService {
	@Autowired
	private FeaturedProductRepo featuredProductRepo;
	
	public List<Product> getall (){
		List<Product> all = new ArrayList<>();
		featuredProductRepo.findAll().forEach(all::add);;
		return all;
	}
}
