package com.global.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.Entity.Category;
import com.global.Repositopy.CategoryRepo;

@Service
public class CategoryServes {

	@Autowired
	private CategoryRepo categoryRepo;
	
	
	public void AddCategory(Category category) {
		categoryRepo.save(category);
	}
	
	
	public List<Category> findAll(){
		return categoryRepo.findAll();
	}
	
	public void DeletCategory(Long id) {
		categoryRepo.deleteById(id);
	}
	
	public Optional<Category> FindCategory(Long id){
		return categoryRepo.findById(id);
	}
	
	
	
	
	
}
