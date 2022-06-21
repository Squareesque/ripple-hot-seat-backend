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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.comarch.ripplehotseat.dto.RoomDTO;
import com.comarch.ripplehotseat.model.Level;
import com.comarch.ripplehotseat.model.Office;
import com.comarch.ripplehotseat.model.Room;
import com.comarch.ripplehotseat.service.LevelService;
import com.comarch.ripplehotseat.service.OfficeService;
import com.comarch.ripplehotseat.service.RoomService;
import com.comarch.ripplehotseat.util.ObjectMapperUtils;

//@CrossOrigin(origins = "${origin}", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE})
@RestController
@RequestMapping("/rooms")
public class RoomRestController {

	@Autowired
	public RoomService roomService;
	@Autowired
	public LevelService levelService;
	@Autowired
	public OfficeService officeService;
	
	@GetMapping(value="")
	public List<RoomDTO> findAll() {
		List<RoomDTO> list = ObjectMapperUtils.mapAll(roomService.findAll(), RoomDTO.class);
		for(RoomDTO roomDTO : list) {
			Level level = levelService.findById(roomDTO.getLevelId());
			roomDTO.setLevelNumber(level.getNumber());
			roomDTO.setOfficeName(officeService.findById(level.getOfficeId()).getName());
		}
		return list;
	}
	
	@GetMapping(value = "/byLevelId/{levelId}")
	public List<RoomDTO> findManyByLevelId(@PathVariable("levelId") String levelId) {
		List<RoomDTO> list = ObjectMapperUtils.mapAll(roomService.findManyByLevelId(levelId), RoomDTO.class);
		Level level = levelService.findById(levelId);
		int levelNumber = level.getNumber();
		Office office = officeService.findById(level.getOfficeId());
		String officeName = office.getName();
		for(RoomDTO roomDTO : list) {
			roomDTO.setLevelNumber(levelNumber);
			roomDTO.setOfficeName(officeName);
		}
		return list;
	}
	
	@GetMapping(value = "/byId/{id}")
	public RoomDTO findById(@PathVariable("id") String id) {
		RoomDTO roomDTO = ObjectMapperUtils.map(roomService.findById(id), RoomDTO.class);
		Level level = levelService.findById(roomDTO.getLevelId());
		roomDTO.setLevelNumber(level.getNumber());
		roomDTO.setOfficeName(officeService.findById(level.getOfficeId()).getName());
		return roomDTO;
	}
	
	@GetMapping(value = "/byOfficeName/{officeName}/byLevelNumber/{levelNumber}/byNumber/{number}")
	public RoomDTO findByOfficeNameAndLevelNumberAndNumber(@PathVariable("officeName") String officeName, @PathVariable("levelNumber") int levelNumber, @PathVariable("number") int number) {
		Office office = officeService.findByName(officeName);
		if(office == null) {
			return ObjectMapperUtils.map(null, RoomDTO.class);
		}
		for(Level level : levelService.findManyByOfficeId(office.getId())) {
			if(level.getNumber() == levelNumber) {
				for(Room room : roomService.findManyByLevelId(level.getId())) {
					if(room.getNumber() == number) {
						RoomDTO roomDTO = ObjectMapperUtils.map(room, RoomDTO.class);
						roomDTO.setLevelNumber(levelNumber);
						roomDTO.setOfficeName(officeName);
						return roomDTO;
					}
				}
			}
		}
		return ObjectMapperUtils.map(null, RoomDTO.class);
	}
	
	@GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE) 
	public @ResponseBody byte[] getImage(@PathVariable("id") String id) {
		return ObjectMapperUtils.map(roomService.findById(id), RoomDTO.class).getPicture();
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save(@RequestBody RoomDTO roomDTO) {
		if(roomDTO.getLevelId() == null) {
			return new ResponseEntity<String>("'levelId' is required", HttpStatus.FORBIDDEN);
		}
		if(levelService.findById(roomDTO.getLevelId()) == null) {
			return new ResponseEntity<String>("'levelId' must be of an existing office", HttpStatus.FORBIDDEN);
		}
		if(roomDTO.getNumber() <= 0) {
			return new ResponseEntity<String>("'number' must be positive", HttpStatus.FORBIDDEN);
		}
		for(Room room : roomService.findManyByLevelId(roomDTO.getLevelId())) {
			if(room.getNumber() == roomDTO.getNumber()) {
				return new ResponseEntity<String>("'number' must be unique between rooms of the same level", HttpStatus.FORBIDDEN);
			}
		}
		if(roomDTO.getPositionX() < 0 || roomDTO.getPositionY() < 0) {
			return new ResponseEntity<String>("'possitionX' and 'possitionY' must be positive", HttpStatus.FORBIDDEN);
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
		if(roomDTO.getLevelId() == null) {
			return new ResponseEntity<String>("'levelId' is required", HttpStatus.FORBIDDEN);
		}
		if(levelService.findById(roomDTO.getLevelId()) == null) {
			return new ResponseEntity<String>("'levelId' must be of an existing office", HttpStatus.FORBIDDEN);
		}
		if(roomDTO.getNumber() <= 0) {
			return new ResponseEntity<String>("'number' must be positive", HttpStatus.FORBIDDEN);
		}
		for(Room room : roomService.findManyByLevelId(roomDTO.getLevelId())) {
			if(room.getNumber() == roomDTO.getNumber() && room.getId() != id) {
				return new ResponseEntity<String>("'number' must be unique between rooms of the same level", HttpStatus.FORBIDDEN);
			}
		}
		if(roomDTO.getPositionX() < 0 || roomDTO.getPositionY() < 0) {
			return new ResponseEntity<String>("'possitionX' and 'possitionY' must be positive", HttpStatus.FORBIDDEN);
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
