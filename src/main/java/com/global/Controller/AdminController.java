package com.global.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.global.DTO.ProductDTO;
import com.global.Entity.Category;
import com.global.Entity.Products;
import com.global.Service.CategoryServes;
import com.global.Service.ProductServes;

@Controller
public class AdminController {
	
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

	@Autowired
	private CategoryServes categoryServes;
	@Autowired
	private ProductServes productServes;

	
	@GetMapping("/adminPagee")
	public String BackToAdminHome() {
		return "AdminHome";
	}
	
	
	@GetMapping("/admin/categories")
	public String OpenAdminCategories(Model model) {
		model.addAttribute("categories",categoryServes.findAll() );
		return "AdminCategories";
	}
	
	
	  @GetMapping("/admin/categories/add")
	    public String showForm(Model model) {
	        model.addAttribute("category", new Category());
	        return "AddCategory";
	    }
	
	@PostMapping("/admin/categories/add")
	public String AddCategory(@ModelAttribute("category") Category c) {
		categoryServes.AddCategory(c);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/delete/{id}")
	public String DeletCategorry(@PathVariable Long id) {
		categoryServes.DeletCategory(id);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/update/{id}")
	public String UpdateCategory(@PathVariable Long id , Model model) {
		Optional<Category> catt = categoryServes.FindCategory(id);
		if(catt.isPresent()) {
			 model.addAttribute("category",catt.get() );
			 return "AddCategory";
		}else {
			return "404";
		}
		
	}
	
//******************** (Product Operations) ********************	
	
	@GetMapping("/admin/products")
	public String OpenProductPage(Model model) {
		model.addAttribute("products", productServes.FindProduct());
		return "AdminProducts";
	}
	
	@GetMapping("/admin/products/add")
	public String AddProductPage(Model model) {
		model.addAttribute("ProductDTO", new ProductDTO() );
		model.addAttribute("categories", categoryServes.findAll());
		return "AddProduct";
	}
	@PostMapping("/admin/products/add")
	public String AddNewProduct(@ModelAttribute("ProductDTO") ProductDTO prod ,
			@RequestParam("productImage") MultipartFile file ,
			@RequestParam("imgName") String imgName) throws IOException {
		
		Products products = new Products();
		products.setId(prod.getId());
		products.setName(prod.getName());
		products.setCategory(categoryServes.FindCategory(prod.getCategory_id()).get());
		products.setPrice(prod.getPrice());
		products.setWeight(prod.getWeight());
		products.setDescription(prod.getDescription());
		
		
		String imageUUID ;
		  if(!file.isEmpty()) {
		  imageUUID= file.getOriginalFilename();
		  Path fileName = Paths.get(uploadDir,imageUUID); 
		   Files.write(fileName,file.getBytes());
		  }
		  else { imageUUID = imgName; }
		  products.setImageName(imageUUID);

		productServes.AddProduct(products);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String RemoveProduct(@PathVariable Long id) {
		productServes.DeletProduct(id);
		return "redirect:/admin/products";
		
	}
	
	
	@GetMapping("/admin/product/update/{id}")
	public String UpdateProduct(@PathVariable Long id , Model model) {
		Products product = productServes.findProduct(id).get(); 
		ProductDTO productDTO = new ProductDTO();
		
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategory_id(product.getCategory().getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setWeight(product.getWeight());
		productDTO.setDescription(product.getDescription());
		productDTO.setImageName(product.getImageName());
		
		model.addAttribute("categories", categoryServes.findAll());
		model.addAttribute("ProductDTO",productDTO);
		return "AddProduct";
	}
	
	
	
}
