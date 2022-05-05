package com.comarch.ripplehotseat.service;

import java.util.List;

import com.comarch.ripplehotseat.model.Desk;

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
public interface DeskService {

	List<Desk> findAll();
	
	Desk findById(String id);
	
	List<Desk> findManyByRoomId(String roomId);
	
	Desk save(Desk desk);
	
	void deleteById(String id);
	
	void deleteAll();
	
	long count();
	
}
