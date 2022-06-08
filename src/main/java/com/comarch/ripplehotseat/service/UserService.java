package com.comarch.ripplehotseat.service;

import java.util.List;

import com.comarch.ripplehotseat.model.User;

public interface UserService {
	
	List<User> findAll();
	
	User findById(String id);
	
	User findByUsername(String Username);
	
	User save(User user);
	
	void deleteById(String id);
	
	void deleteAll();
	
	long count();
	
}
