package com.comarch.ripplehotseat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.comarch.ripplehotseat.model.User;

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
public interface UserRepository extends MongoRepository<User, String> {
	
	User findByLogin(String login);
	
}
