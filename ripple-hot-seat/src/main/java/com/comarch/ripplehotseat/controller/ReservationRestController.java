package com.comarch.ripplehotseat.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comarch.ripplehotseat.dto.ReservationDTO;
import com.comarch.ripplehotseat.model.Reservation;
import com.comarch.ripplehotseat.service.DeskService;
import com.comarch.ripplehotseat.service.ReservationService;
import com.comarch.ripplehotseat.service.UserService;
import com.comarch.ripplehotseat.util.ObjectMapperUtils;

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
@RestController
@RequestMapping("/reservations")
public class ReservationRestController {

	@Autowired
	public DeskService deskService;
	@Autowired
	public ReservationService reservationService;
	@Autowired
	public UserService userService;
	
	@GetMapping(value="")
	public List<ReservationDTO> findAll() {
		return ObjectMapperUtils.mapAll(reservationService.findAll(), ReservationDTO.class);
	}
	
	@GetMapping(value="/orderByStartTime")
	public List<ReservationDTO> findAllByOrderByStartTime() {
		return ObjectMapperUtils.mapAll(reservationService.findAllByOrderByStartTime(), ReservationDTO.class);
	}
	
	@GetMapping(value = "/byId/{id}")
	public ReservationDTO findById(@PathVariable("id") String id) {
		return ObjectMapperUtils.map(reservationService.findById(id), ReservationDTO.class);
	}
	
	@GetMapping(value = "/byStartTime/{startTime}")
	public List<ReservationDTO> findManyByStartTime(@PathVariable("startTime") Date startTime) {
		return ObjectMapperUtils.mapAll(reservationService.findManyByStartTime(startTime), ReservationDTO.class);
	}
	
	@GetMapping(value = "/byEndTime/{endTime}")
	public List<ReservationDTO> findManyByEndTime(@PathVariable("endTime") Date endTime) {
		return ObjectMapperUtils.mapAll(reservationService.findManyByEndTime(endTime), ReservationDTO.class);
	}
	
	@GetMapping(value = "/byDeskId/{deskId}")
	public List<ReservationDTO> findManyByDeskId(@PathVariable("deskId") String deskId) {
		return ObjectMapperUtils.mapAll(reservationService.findManyByDeskId(deskId), ReservationDTO.class);
	}
	
	@GetMapping(value = "/byUserId/{userId}")
	public List<ReservationDTO> findManyByUserId(@PathVariable("userId") String userId) {
		return ObjectMapperUtils.mapAll(reservationService.findManyByUserId(userId), ReservationDTO.class);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save(@RequestBody ReservationDTO reservationDTO) {
		if(reservationDTO.getStartTime() == null || reservationDTO.getEndTime() == null || reservationDTO.getStartTime() == null || reservationDTO.getEndTime() == null) {
			return new ResponseEntity<String>("'startTime', 'endTime', 'deskId' and 'userId' are required", HttpStatus.FORBIDDEN);
		}
		if(!reservationDTO.getStartTime().before(reservationDTO.getEndTime())) {
			return new ResponseEntity<String>("'startTime' must be before 'endTime'", HttpStatus.FORBIDDEN);
		}
		if(deskService.findById(reservationDTO.getDeskId()) == null) {
			return new ResponseEntity<String>("'deskId' must be of an existing desk", HttpStatus.FORBIDDEN);
		}
		if(userService.findById(reservationDTO.getUserId()) == null) {
			return new ResponseEntity<String>("'userId' must be of an existing user", HttpStatus.FORBIDDEN);
		}
		reservationDTO.setId(null);
		reservationService.save(ObjectMapperUtils.map(reservationDTO, Reservation.class));
		return new ResponseEntity<String>("Reservation added successfully", HttpStatus.OK);
	}
	
	@PatchMapping(value = "/update/{id}")
	public ResponseEntity<String> update(@PathVariable("id") String id, @RequestBody ReservationDTO reservationDTO) {
		if(reservationService.findById(id) == null) {
			return new ResponseEntity<String>("Reservation could not be found", HttpStatus.NOT_FOUND);
		}
		if(reservationDTO.getStartTime() == null || reservationDTO.getEndTime() == null || reservationDTO.getStartTime() == null || reservationDTO.getEndTime() == null) {
			return new ResponseEntity<String>("'startTime', 'endTime', 'deskId' and 'userId' are required", HttpStatus.FORBIDDEN);
		}
		if(!reservationDTO.getStartTime().before(reservationDTO.getEndTime())) {
			return new ResponseEntity<String>("'startTime' must be before 'endTime'", HttpStatus.FORBIDDEN);
		}
		if(deskService.findById(reservationDTO.getDeskId()) == null) {
			return new ResponseEntity<String>("'deskId' must be of an existing desk", HttpStatus.FORBIDDEN);
		}
		if(userService.findById(reservationDTO.getUserId()) == null) {
			return new ResponseEntity<String>("'userId' must be of an existing user", HttpStatus.FORBIDDEN);
		}
		reservationDTO.setId(id);
		reservationService.save(ObjectMapperUtils.map(reservationDTO, Reservation.class));
		return new ResponseEntity<String>("Reservation updated successfully", HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") String id) {
		if(reservationService.findById(id) == null) {
			return new ResponseEntity<String>("Reservation could not be found", HttpStatus.NOT_FOUND);
		}
		reservationService.deleteById(id);
		return new ResponseEntity<String>("Reservation deleted successfully", HttpStatus.OK);
	}
	
}
