package com.comarch.ripplehotseat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.comarch.ripplehotseat.dto.RoomDTO;
import com.comarch.ripplehotseat.model.Room;
import com.comarch.ripplehotseat.service.OfficeService;
import com.comarch.ripplehotseat.service.RoomService;
import com.comarch.ripplehotseat.util.ObjectMapperUtils;

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
@RestController
@CrossOrigin("https://ripple-hot-seat-backend-app.herokuapp.com")
@RequestMapping("/rooms")
public class RoomRestController {

	@Autowired
	public RoomService roomService;
	
	@Autowired
	public OfficeService officeService;
	
	@GetMapping(value="")
	public List<RoomDTO> findAll() {
		return ObjectMapperUtils.mapAll(roomService.findAll(), RoomDTO.class);
	}
	
	@GetMapping(value="/orderByNumber")
	public List<RoomDTO> findAllByOrderByNumber() {
		return ObjectMapperUtils.mapAll(roomService.findAllByOrderByNumber(), RoomDTO.class);
	}
	
	@GetMapping(value = "/byId/{id}")
	public RoomDTO findById(@PathVariable("id") String id) {
		return ObjectMapperUtils.map(roomService.findById(id), RoomDTO.class);
	}
	
<<<<<<< HEAD
	@GetMapping(value = "/byOfficeId/{officeId}")
	public List<RoomDTO> findManyByOfficeId(@PathVariable("officeId") String officeId) {
		return ObjectMapperUtils.mapAll(roomService.findManyByOfficeId(officeId), RoomDTO.class);
	}
	
=======
>>>>>>> 32e96365e540e802fa5c90eea0df4531bfba69be
	@GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE) 
	public @ResponseBody byte[] getImage(@PathVariable("id") String id) {
		return ObjectMapperUtils.map(roomService.findById(id), RoomDTO.class).getPicture();
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save(@RequestBody RoomDTO roomDTO) {
		if(roomDTO.getOfficeId() == null) {
			return new ResponseEntity<String>("'officeId' is required", HttpStatus.FORBIDDEN);
		}
		if(officeService.findById(roomDTO.getOfficeId()) == null) {
			return new ResponseEntity<String>("'officeId' must be of an existing office", HttpStatus.FORBIDDEN);
		}
		roomDTO.setId(null);
		roomService.save(ObjectMapperUtils.map(roomDTO, Room.class));
		return new ResponseEntity<String>("Room added successfully", HttpStatus.OK);
	}
	
	@PostMapping(value = "/image/{id}")
	public ResponseEntity<String> setImage(@PathVariable("id") String id, @RequestBody byte[] image) {
		Room room = roomService.findById(id);
		room.setPicture(image);
		roomService.save(room);
		return new ResponseEntity<String>("Image set successfully", HttpStatus.OK);
	}
	
	@PatchMapping(value = "/update/{id}")
	public ResponseEntity<String> update(@PathVariable("id") String id, @RequestBody RoomDTO roomDTO) {
		if(roomService.findById(id) == null) {
			return new ResponseEntity<String>("Room could not be found", HttpStatus.NOT_FOUND);
		}
		if(roomDTO.getOfficeId() == null) {
			return new ResponseEntity<String>("'officeId' is required", HttpStatus.FORBIDDEN);
		}
		if(officeService.findById(roomDTO.getOfficeId()) == null) {
			return new ResponseEntity<String>("'officeId' must be of an existing office", HttpStatus.FORBIDDEN);
		}
		roomDTO.setId(id);
		roomService.save(ObjectMapperUtils.map(roomDTO, Room.class));
		return new ResponseEntity<String>("Room updated successfully", HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") String id) {
		if(roomService.findById(id) == null) {
			return new ResponseEntity<String>("Room could not be found", HttpStatus.NOT_FOUND);
		}
		roomService.deleteById(id);
		return new ResponseEntity<String>("Room deleted successfully", HttpStatus.OK);
	}
	
}
