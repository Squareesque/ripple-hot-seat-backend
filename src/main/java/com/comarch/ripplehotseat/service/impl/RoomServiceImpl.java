package com.comarch.ripplehotseat.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comarch.ripplehotseat.model.Desk;
import com.comarch.ripplehotseat.model.Room;
import com.comarch.ripplehotseat.repository.RoomRepository;
import com.comarch.ripplehotseat.service.DeskService;
import com.comarch.ripplehotseat.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	public RoomRepository roomRepository;
	@Autowired
	public DeskService deskService;
	
	@Override
	public List<Room> findAll() {
		return roomRepository.findAll();
	}
	
	@Override
	public List<Room> findManyByLevelId(String levelId) {
		return roomRepository.findManyByLevelId(levelId);
	}

	@Override
	public Room findById(String id) {
		try {
			return roomRepository.findById(id).get();
		} catch(NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public Room save(Room room) {
		return roomRepository.save(room);
	}

	@Override
	public void deleteById(String id) {
		for(Desk desk : deskService.findManyByRoomId(id)) {
			deskService.deleteById(desk.getId());
		}
		roomRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		roomRepository.deleteAll();
	}
	
	@Override
	public long count() {
		return roomRepository.count();
	}
	
}
