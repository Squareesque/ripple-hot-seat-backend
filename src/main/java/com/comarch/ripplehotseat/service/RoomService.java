package com.comarch.ripplehotseat.service;

import java.util.List;

import com.comarch.ripplehotseat.model.Room;

public interface RoomService {
	
	List<Room> findAll();
	
	List<Room> findManyByLevelId(String levelId);
	
	Room findById(String id);
	
	Room save(Room room);
	
	void deleteById(String id);
	
	void deleteAll();
	
	long count();
	
}
