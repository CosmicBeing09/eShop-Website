package com.daraz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daraz.obj.HomeSlider;
import com.daraz.repo.HomeSliderRepo;

@Service
public class HomeSliderService {

	@Autowired
	private HomeSliderRepo homeSliderRepo;
	
	public List<HomeSlider> getAll(){
		List<HomeSlider> all = new ArrayList<HomeSlider>();
		homeSliderRepo.findAll().forEach(all::add);;
		return all;
	}
	
	public String deleteOne(String id) {
		homeSliderRepo.delete(id);
		return "Deleted";
	}
		
}

