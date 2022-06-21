package com.comarch.ripplehotseat.jwtutils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class JwtController {

	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenManager tokenManager;
	
	@GetMapping(value = "/login")
	public ResponseEntity<String> login(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
		try {
	    	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	    } catch (DisabledException e) {
	        throw new Exception("USER_DISABLED", e);
	    } catch (BadCredentialsException e) {
	        throw new Exception("INVALID_CREDENTIALS", e);
	    }
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	    String jwtToken = tokenManager.generateJwtToken(userDetails);
		return new ResponseEntity<String>(jwtToken, HttpStatus.OK);
	}
	
}
