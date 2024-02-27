package com.global.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.global.Entity.Products;
import com.global.Global.GlobalCart;
import com.global.Service.ProductServes;


@Controller
public class CartController {

	
	@Autowired
	ProductServes productServes;
	
	
	@GetMapping("/addToCart/{id}")
	public String  CartAdd(@PathVariable Long id) {
		GlobalCart.cart.add(productServes.findProduct(id).get());
		
		return "redirect:/shop";
	}
	
	@GetMapping("/cart")
	public String Cart(Model model) {
		model.addAttribute("cartCount",GlobalCart.cart.size());
		model.addAttribute("total", GlobalCart.cart.stream().mapToDouble(Products::getPrice).sum());
		model.addAttribute("cart", GlobalCart.cart);
		return "Cart";
	}
	
	@GetMapping("/cart/removeItem/{index}")
	public String deleteItemfromCart(@PathVariable int index) {
		GlobalCart.cart.remove(index);
		return "redirect:/cart";
	}
	@GetMapping("/checkout")
	public String Checkout(Model model) {
		model.addAttribute("total", GlobalCart.cart.stream().mapToDouble(Products::getPrice).sum());
		model.addAttribute("cartCount",GlobalCart.cart.size());

		return "Checkout";
	}
	
	
	
}
