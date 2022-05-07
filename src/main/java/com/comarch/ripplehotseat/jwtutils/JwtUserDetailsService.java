package com.comarch.ripplehotseat.jwtutils;

import java.util.ArrayList;

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
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.comarch.ripplehotseat.model.User user = userService.findByLogin(username);
		if (user != null) { 
	    	return new User(username, user.getPassword(), new ArrayList<GrantedAuthority>()); 
	    }
		else { 
	    	throw new UsernameNotFoundException("User " + username + " not found"); 
	    } 
	}

}
