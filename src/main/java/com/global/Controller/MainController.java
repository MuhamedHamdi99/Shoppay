package com.global.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.global.DTO.UserDTO;
import com.global.Entity.User;
import com.global.Repositopy.UserRepo;
import com.global.Service.UserService;

@Controller
@RequestMapping("/")
public class MainController {

	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("register")
	public String OpenRegistert(@ModelAttribute("user") UserDTO userDto){
		return "registerr";
	}
	
	@PostMapping("register")
	public String Save_User_Regist(@ModelAttribute("user") UserDTO userdto) {
		User uuser = userRepo.findByEmail(userdto.getEmail());
		if (uuser == null) {
			userService.save(userdto);
			return "login";  //"redirect:/register?success"; 
			}
		else {return "redirect:/register?error";}
		
	}
	
	
	@GetMapping("login")
	public String login(){
		return "login";
	}
	
	@GetMapping("userPage")
	public String loginn(
		Model model , Principal principal){
	
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		return "UserHome";
	}
	@GetMapping("adminPage")
	public String loginnn(Model model , Principal principal){
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		return "AdminHome";
	}
	
	
	
	
	
}
