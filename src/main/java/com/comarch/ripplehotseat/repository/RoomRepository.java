package com.comarch.ripplehotseat.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.comarch.ripplehotseat.model.Room;

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
public interface RoomRepository extends MongoRepository<Room, String> {
	
	List<Room> findAllByOrderByNumber();
	
	List<Room> findManyByOfficeId(String officeId);
	
}
