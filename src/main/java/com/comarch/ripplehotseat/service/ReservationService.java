package com.comarch.ripplehotseat.service;

import java.util.Date;
import java.util.List;

import com.comarch.ripplehotseat.model.Reservation;

public interface ReservationService {

	List<Reservation> findAll();
	
	List<Reservation> findAllByOrderByStartTime();
	
	List<Reservation> findManyByDeskId(String deskId);
	
	List<Reservation> findManyByUserId(String userId);
	
	List<Reservation> findManyByStartTime(Date startTime);
	
	List<Reservation> findManyByEndTime(Date endTime);
	
	Reservation findById(String id);
	
	Reservation save(Reservation reservation);
	
	void deleteById(String id);
	
	void deleteAll();
	
	long count();
	
}
