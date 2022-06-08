package com.comarch.ripplehotseat.service;

import java.util.List;

import com.comarch.ripplehotseat.model.Desk;

public interface DeskService {

	List<Desk> findAll();
	
	List<Desk> findManyByRoomId(String roomId);
	
	Desk findById(String id);
	
	Desk findByBeaconId(String beaconId);
	
	Desk save(Desk desk);
	
	void deleteById(String id);
	
	void deleteAll();
	
	long count();
	
}
