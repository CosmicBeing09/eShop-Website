package com.daraz.repo;

import org.springframework.data.repository.CrudRepository;

import com.daraz.obj.Product;

public interface FeaturedProductRepo extends CrudRepository<Product, Integer> {

}
