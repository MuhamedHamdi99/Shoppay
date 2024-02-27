package com.global.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.global.Global.GlobalCart;
import com.global.Service.CategoryServes;
import com.global.Service.ProductServes;

@Controller
public class CustomerController {

	@Autowired
	CategoryServes categoryServes;
	@Autowired
	ProductServes productServes;
	
	
	@GetMapping({"/homee","/homeee"})
	public String WelcomPage(Model model) {
		model.addAttribute("cartCount",GlobalCart.cart.size());
		return "WelocmePage";
	}
	
	@GetMapping("/shop")
	public String ShopPage(Model model) {
		model.addAttribute("categories", categoryServes.findAll());
		model.addAttribute("products",productServes.FindProduct());
		model.addAttribute("cartCount",GlobalCart.cart.size());

		return "Shop";
	}
	
	
	@GetMapping("/shop/category/{id}")
	public String ViewProductsWithCategoryId(Model model,@PathVariable Long id) {
		model.addAttribute("categories", categoryServes.findAll());
		model.addAttribute("cartCount",GlobalCart.cart.size());
		model.addAttribute("products",productServes.FindProduct_By_CategoryId(id));
		

		return "Shop";
	}
	
	
	@GetMapping("/shop/viewproduct/{id}")
	public String ViewProduct(Model model,@PathVariable Long id) {
		model.addAttribute("product",productServes.findProduct(id).get());
		model.addAttribute("cartCount",GlobalCart.cart.size());

		return "ViewProduct";
	}
	
	
	
}
