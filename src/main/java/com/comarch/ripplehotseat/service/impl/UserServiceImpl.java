package com.comarch.ripplehotseat.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comarch.ripplehotseat.model.Reservation;
import com.comarch.ripplehotseat.model.User;
import com.comarch.ripplehotseat.repository.UserRepository;
import com.comarch.ripplehotseat.service.ReservationService;
import com.comarch.ripplehotseat.service.UserService;

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public ReservationService reservationService;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		try {
			return userRepository.findById(id).get();
		} catch(NoSuchElementException e) {
			return null;
		}
	}

	public User findByLogin(String login) {
		return userRepository.findByLogin(login);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void deleteById(String id) {
		for(Reservation reservation : reservationService.findManyByDeskId(id)) {
			reservationService.deleteById(reservation.getId());
		}
		userRepository.deleteById(id);
	}
	
	public void deleteAll() {
		userRepository.deleteAll();
	}

	public long count() {
		return userRepository.count();
	}
	
}
