package com.comarch.ripplehotseat.service;

import java.util.List;

import com.comarch.ripplehotseat.model.Level;

public interface LevelService {

	List<Level> findAll();
	
	List<Level> findManyByOfficeId(String officeId);
	
	Level findById(String id);
	
	Level save(Level level);
	
	void deleteById(String id);
	
	void deleteAll();
	
	long count();
	
}
