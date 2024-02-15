package com.global.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.global.Entity.User;
import com.global.Repositopy.UserRepo;

@Service
public class User_Details_Service implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
			
		return new User_Details(user);
	}

}
