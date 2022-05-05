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

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
@Service
public class DeskServiceImpl implements DeskService {

	@Autowired
	public DeskRepository deskRepository;
	@Autowired
	public ReservationService reservationService;
	
	public List<Desk> findAll() {
		return deskRepository.findAll();
	}

	public Desk findById(String id) {
		try {
			return deskRepository.findById(id).get();
		} catch(NoSuchElementException e) {
			return null;
		}
	}

	public List<Desk> findManyByRoomId(String roomId) {
		return deskRepository.findManyByRoomId(roomId);
	}

	public Desk save(Desk desk) {
		return deskRepository.save(desk);
	}

	public void deleteById(String id) {
		for(Reservation reservation : reservationService.findManyByDeskId(id)) {
			reservationService.deleteById(reservation.getId());
		}
		deskRepository.deleteById(id);
	}
	
	public void deleteAll() {
		deskRepository.deleteAll();
	}
	
	public long count() {
		return deskRepository.count();
	}

}
