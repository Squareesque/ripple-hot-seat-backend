package com.comarch.ripplehotseat.service.impl;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comarch.ripplehotseat.model.Reservation;
import com.comarch.ripplehotseat.repository.ReservationRepository;
import com.comarch.ripplehotseat.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	public ReservationRepository reservationRepository;
	
	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	@Override
	public List<Reservation> findAllByOrderByStartTime() {
		return reservationRepository.findAllByOrderByStartTime();
	}

	@Override
	public List<Reservation> findManyByDeskId(String deskId) {
		return reservationRepository.findManyByDeskId(deskId);
	}

	@Override
	public List<Reservation> findManyByUserId(String userId) {
		return reservationRepository.findManyByUserId(userId);
	}
	
	@Override
	public List<Reservation> findManyByStartTime(Date startTime) {
		return reservationRepository.findManyByStartTime(startTime);
	}

	@Override
	public List<Reservation> findManyByEndTime(Date endTime) {
		return reservationRepository.findManyByEndTime(endTime);
	}

	@Override
	public Reservation findById(String id) {
		try {
			return reservationRepository.findById(id).get();
		} catch(NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public Reservation save(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Override
	public void deleteById(String id) {
		reservationRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		reservationRepository.deleteAll();
	}
	
	@Override
	public long count() {
		return reservationRepository.count();
	}
	
}
