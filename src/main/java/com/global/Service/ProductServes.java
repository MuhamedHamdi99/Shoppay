package com.global.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.global.Entity.Products;
import com.global.Repositopy.ProductRepo;

@Service
public class ProductServes {

	@Autowired
	private ProductRepo productRepo;
	
	public List<Products> FindProduct(){
		return productRepo.findAll();
	}
	
	public void AddProduct(Products product) {
		productRepo.save(product);
	}
	
	public void DeletProduct(long id) {
		productRepo.deleteById(id);
	}
	
	public Optional<Products> findProduct(Long id){
		return productRepo.findById(id);
	}
	
	public List<Products> FindProduct_By_CategoryId(long id){
		return productRepo.findAllByCategory_id(id);
	}
	
	
	
}
