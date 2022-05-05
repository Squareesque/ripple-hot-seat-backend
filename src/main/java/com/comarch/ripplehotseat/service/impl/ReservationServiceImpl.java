package com.comarch.ripplehotseat.service.impl;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comarch.ripplehotseat.model.Reservation;
import com.comarch.ripplehotseat.repository.ReservationRepository;
import com.comarch.ripplehotseat.service.ReservationService;

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	public ReservationRepository reservationRepository;
	
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	public List<Reservation> findAllByOrderByStartTime() {
		return reservationRepository.findAllByOrderByStartTime();
	}

	public Reservation findById(String id) {
		try {
			return reservationRepository.findById(id).get();
		} catch(NoSuchElementException e) {
			return null;
		}
	}
	
	public List<Reservation> findManyByStartTime(Date startTime) {
		return reservationRepository.findManyByStartTime(startTime);
	}

	public List<Reservation> findManyByEndTime(Date endTime) {
		return reservationRepository.findManyByEndTime(endTime);
	}

	public List<Reservation> findManyByDeskId(String deskId) {
		return reservationRepository.findManyByDeskId(deskId);
	}

	public List<Reservation> findManyByUserId(String userId) {
		return reservationRepository.findManyByUserId(userId);
	}

	public Reservation save(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	public void deleteById(String id) {
		reservationRepository.deleteById(id);
	}

	public void deleteAll() {
		reservationRepository.deleteAll();
	}
	
	public long count() {
		return reservationRepository.count();
	}
	
}
