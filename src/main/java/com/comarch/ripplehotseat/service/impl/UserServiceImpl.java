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

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;
	@Autowired
	public ReservationService reservationService;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(String id) {
		try {
			return userRepository.findById(id).get();
		} catch(NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteById(String id) {
		for(Reservation reservation : reservationService.findManyByDeskId(id)) {
			reservationService.deleteById(reservation.getId());
		}
		userRepository.deleteById(id);
	}
	
	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}

	@Override
	public long count() {
		return userRepository.count();
	}
	
}
