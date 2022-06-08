package com.comarch.ripplehotseat.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.comarch.ripplehotseat.model.Level;

public interface LevelRepository extends MongoRepository<Level, String> {

	List<Level> findManyByOfficeId(String officeId);
	
}
