package com.global.Repositopy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.Entity.Products;

@Repository
public interface ProductRepo extends JpaRepository<Products, Long> {

	List<Products> findAllByCategory_id(long id);

}
