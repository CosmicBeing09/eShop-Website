package com.daraz.repo;

import org.springframework.data.repository.CrudRepository;
import com.daraz.obj.Product_temp;

public interface ProductRepo extends CrudRepository<Product_temp, Integer> {

}
