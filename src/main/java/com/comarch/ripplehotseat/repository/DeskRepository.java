package com.comarch.ripplehotseat.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.comarch.ripplehotseat.model.Desk;

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
public interface DeskRepository extends MongoRepository<Desk, String> {
	
	List<Desk> findManyByRoomId(String roomId);
	
}
