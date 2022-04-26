package com.comarch.ripplehotseat.jwtutils;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.comarch.ripplehotseat.service.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@SuppressWarnings("unchecked")
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.comarch.ripplehotseat.model.User user = userService.findByLogin(username);
		if (user != null) { 
	    	return new User(username, user.getPassword(), (Collection<? extends GrantedAuthority>) new ArrayList<>()); 
	    }
		else { 
	    	throw new UsernameNotFoundException("User " + username + " not found"); 
	    } 
	}

}
