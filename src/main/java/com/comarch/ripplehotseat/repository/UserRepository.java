package com.comarch.ripplehotseat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.comarch.ripplehotseat.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	User findByUsername(String username);
	
}
