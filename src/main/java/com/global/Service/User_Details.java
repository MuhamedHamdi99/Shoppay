package com.global.Service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.global.Entity.User;



public class User_Details implements UserDetails {

	
	private User useer;
	public User_Details(User useer) {this.useer = useer;}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(() -> useer.getRole());
	}

	public String getFirstName() {
		return useer.getFirstName();
	}
	
	public String getLastName() {
		return useer.getLastName();
	}
	
	@Override
	public String getPassword() {
		return useer.getPassword();
	}

	@Override
	public String getUsername() {
		return useer.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
