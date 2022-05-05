package com.comarch.ripplehotseat.service;

import java.util.List;

import com.comarch.ripplehotseat.model.User;

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
public interface UserService {
	
	List<User> findAll();
	
	User findById(String id);
	
	User findByLogin(String login);
	
	User save(User user);
	
	void deleteById(String id);
	
	void deleteAll();
	
	long count();
	
}
