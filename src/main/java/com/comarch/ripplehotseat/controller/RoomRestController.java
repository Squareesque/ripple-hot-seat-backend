package com.comarch.ripplehotseat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.comarch.ripplehotseat.service.RoomService;
import com.comarch.ripplehotseat.util.ObjectMapperUtils;

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
@RestController
@RequestMapping("/rooms")
public class RoomRestController {

	@Autowired
	public RoomService roomService;
	
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
	
	@GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE) 
	public @ResponseBody byte[] getImage(@PathVariable("id") String id) {
		return ObjectMapperUtils.map(roomService.findById(id), RoomDTO.class).getPicture();
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save(@RequestBody RoomDTO roomDTO) {
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
