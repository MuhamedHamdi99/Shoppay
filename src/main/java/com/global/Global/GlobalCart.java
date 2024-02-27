package com.global.Global;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.global.Entity.Products;



public class GlobalCart {

	public static List<Products> cart;
	
	
	static{
		cart = new ArrayList<Products>();
	}
	
}
