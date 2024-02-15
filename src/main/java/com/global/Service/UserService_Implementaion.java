package com.global.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.global.DTO.UserDTO;
import com.global.Entity.User;
import com.global.Repositopy.UserRepo;


@Service
public class UserService_Implementaion implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepo userRepo;
	
	
	
	
	
	
	
	
	@Override
	public User save(UserDTO suerDto) {
	User user = new User(suerDto.getFirstName(),suerDto.getLastName(),suerDto.getEmail()
			,passwordEncoder.encode(suerDto.getPassword()) ,suerDto.getRole()); 
	
		return userRepo.save(user);
	}

}
