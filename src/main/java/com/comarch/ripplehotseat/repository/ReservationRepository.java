package com.comarch.ripplehotseat.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.comarch.ripplehotseat.model.Reservation;

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
public interface ReservationRepository extends MongoRepository<Reservation, String> {

	List<Reservation> findAllByOrderByStartTime();
	
	List<Reservation> findManyByStartTime(Date startTime);
	
	List<Reservation> findManyByEndTime(Date endTime);
	
	List<Reservation> findManyByDeskId(String deskId);
	
	List<Reservation> findManyByUserId(String userId);
	
}
