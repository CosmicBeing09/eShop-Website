package com.daraz.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.daraz.obj.Product_temp;
import com.daraz.repo.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo productRepo;
	
	public List<Product_temp> getall (){
		List<Product_temp> all = new ArrayList<>();
		productRepo.findAll().forEach(all::add);;
		return all;
	}
	
	public String deleteOne(String id) {
		productRepo.delete(id);
		return "Deleted";
	}
	public String updateProduct(Product_temp product_temp) {
		productRepo.save(product_temp);
		return "Updated";
	}
}
