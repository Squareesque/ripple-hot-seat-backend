package com.comarch.ripplehotseat.service;

import java.util.Date;
import java.util.List;

import com.comarch.ripplehotseat.model.Reservation;

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
public interface ReservationService {

	List<Reservation> findAll();
	
	List<Reservation> findAllByOrderByStartTime();
	
	Reservation findById(String id);
	
	List<Reservation> findManyByStartTime(Date startTime);
	
	List<Reservation> findManyByEndTime(Date endTime);
	
	List<Reservation> findManyByDeskId(String deskId);
	
	List<Reservation> findManyByUserId(String userId);
	
	Reservation save(Reservation reservation);
	
	void deleteById(String id);
	
	void deleteAll();
	
	long count();
	
}
