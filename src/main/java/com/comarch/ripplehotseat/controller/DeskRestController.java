package com.comarch.ripplehotseat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comarch.ripplehotseat.dto.DeskDTO;
import com.comarch.ripplehotseat.model.Desk;
import com.comarch.ripplehotseat.service.DeskService;
import com.comarch.ripplehotseat.service.RoomService;
import com.comarch.ripplehotseat.util.ObjectMapperUtils;

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
@RestController
@CrossOrigin("https://ripple-hot-seat-backend-app.herokuapp.com")
@RequestMapping("/desks")
public class DeskRestController {

	@Autowired
	public DeskService deskService;
	@Autowired
	public RoomService roomService;
	
	@GetMapping(value="")
	public List<DeskDTO> findAll() {
		return ObjectMapperUtils.mapAll(deskService.findAll(), DeskDTO.class);
	}
	
	@GetMapping(value = "/byId/{id}")
	public DeskDTO findById(@PathVariable("id") String id) {
		return ObjectMapperUtils.map(deskService.findById(id), DeskDTO.class);
	}
	
	@GetMapping(value = "/byRoomId/{roomId}")
	public List<DeskDTO> findManyByRoomId(@PathVariable("roomId") String roomId) {
		return ObjectMapperUtils.mapAll(deskService.findManyByRoomId(roomId), DeskDTO.class);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save(@RequestBody DeskDTO deskDTO) {
		if(deskDTO.getRoomId() == null) {
			return new ResponseEntity<String>("'roomId' is required", HttpStatus.FORBIDDEN);
		}
		if(roomService.findById(deskDTO.getRoomId()) == null) {
			return new ResponseEntity<String>("'roomId' must be of an existing room", HttpStatus.FORBIDDEN);
		}
		deskDTO.setId(null);
		deskService.save(ObjectMapperUtils.map(deskDTO, Desk.class));
		return new ResponseEntity<String>("Desk added successfully", HttpStatus.OK);
	}
	
	@PatchMapping(value = "/update/{id}")
	public ResponseEntity<String> update(@PathVariable("id") String id, @RequestBody DeskDTO deskDTO) {
		if(deskService.findById(id) == null) {
			return new ResponseEntity<String>("Desk could not be found", HttpStatus.NOT_FOUND);
		}
		if(deskDTO.getRoomId() == null) {
			return new ResponseEntity<String>("'roomId' is required", HttpStatus.FORBIDDEN);
		}
		if(roomService.findById(deskDTO.getRoomId()) == null) {
			return new ResponseEntity<String>("'roomId' must be of an existing room", HttpStatus.FORBIDDEN);
		}
		deskDTO.setId(id);
		deskService.save(ObjectMapperUtils.map(deskDTO, Desk.class));
		return new ResponseEntity<String>("Desk updated successfully", HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") String id) {
		if(deskService.findById(id) == null) {
			return new ResponseEntity<String>("Desk could not be found", HttpStatus.NOT_FOUND);
		}
		deskService.deleteById(id);
		return new ResponseEntity<String>("Desk deleted successfully", HttpStatus.OK);
	}
	
}
