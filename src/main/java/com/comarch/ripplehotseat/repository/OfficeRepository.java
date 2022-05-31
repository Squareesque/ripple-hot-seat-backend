package com.comarch.ripplehotseat.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.comarch.ripplehotseat.model.Office;

public interface OfficeRepository extends MongoRepository<Office, String> {

	Office findByName(String name);
	
}
