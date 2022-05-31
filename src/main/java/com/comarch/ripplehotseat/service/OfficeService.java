package com.comarch.ripplehotseat.service;

import java.util.List;

import com.comarch.ripplehotseat.model.Office;

public interface OfficeService {

	List<Office> findAll();
	
	Office findById(String id);
	
	Office findByName(String name);
	
	Office save(Office office);
	
	void deleteById(String id);
	
	void deleteAll();
	
	long count();
	
}
