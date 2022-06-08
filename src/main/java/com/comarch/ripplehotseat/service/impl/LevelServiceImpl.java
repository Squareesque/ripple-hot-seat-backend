package com.comarch.ripplehotseat.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comarch.ripplehotseat.model.Level;
import com.comarch.ripplehotseat.model.Room;
import com.comarch.ripplehotseat.repository.LevelRepository;
import com.comarch.ripplehotseat.service.LevelService;
import com.comarch.ripplehotseat.service.RoomService;

@Service
public class LevelServiceImpl implements LevelService {

	@Autowired
	public LevelRepository levelRepository;
	@Autowired
	public RoomService roomService;
	
	@Override
	public List<Level> findAll() {
		return levelRepository.findAll();
	}

	@Override
	public List<Level> findManyByOfficeId(String officeId) {
		return levelRepository.findManyByOfficeId(officeId);
	}

	@Override
	public Level findById(String id) {
		try {
			return levelRepository.findById(id).get();
		} catch(NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public Level save(Level level) {
		return levelRepository.save(level);
	}

	@Override
	public void deleteById(String id) {
		for(Room room : roomService.findManyByLevelId(id)) {
			roomService.deleteById(room.getId());
		}
		levelRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		levelRepository.deleteAll();
	}

	@Override
	public long count() {
		return levelRepository.count();
	}

}
