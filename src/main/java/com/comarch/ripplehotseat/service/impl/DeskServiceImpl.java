package com.comarch.ripplehotseat.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comarch.ripplehotseat.model.Desk;
import com.comarch.ripplehotseat.model.Reservation;
import com.comarch.ripplehotseat.repository.DeskRepository;
import com.comarch.ripplehotseat.service.DeskService;
import com.comarch.ripplehotseat.service.ReservationService;

@Service
public class DeskServiceImpl implements DeskService {

	@Autowired
	public DeskRepository deskRepository;
	@Autowired
	public ReservationService reservationService;
	
	@Override
	public List<Desk> findAll() {
		return deskRepository.findAll();
	}

	@Override
	public List<Desk> findManyByRoomId(String roomId) {
		return deskRepository.findManyByRoomId(roomId);
	}

	@Override
	public Desk findById(String id) {
		try {
			return deskRepository.findById(id).get();
		} catch(NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public Desk findByBeaconId(String beaconId) {
		return deskRepository.findByBeaconId(beaconId);
	}
	
	@Override
	public Desk save(Desk desk) {
		return deskRepository.save(desk);
	}

	@Override
	public void deleteById(String id) {
		for(Reservation reservation : reservationService.findManyByDeskId(id)) {
			reservationService.deleteById(reservation.getId());
		}
		deskRepository.deleteById(id);
	}
	
	@Override
	public void deleteAll() {
		deskRepository.deleteAll();
	}
	
	@Override
	public long count() {
		return deskRepository.count();
	}

}
