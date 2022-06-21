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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comarch.ripplehotseat.dto.DeskDTO;
import com.comarch.ripplehotseat.model.Desk;
import com.comarch.ripplehotseat.model.Level;
import com.comarch.ripplehotseat.model.Office;
import com.comarch.ripplehotseat.model.Room;
import com.comarch.ripplehotseat.service.DeskService;
import com.comarch.ripplehotseat.service.LevelService;
import com.comarch.ripplehotseat.service.OfficeService;
import com.comarch.ripplehotseat.service.RoomService;
import com.comarch.ripplehotseat.util.ObjectMapperUtils;

@CrossOrigin
@RestController
@RequestMapping("/desks")
public class DeskRestController {

	@Autowired
	public DeskService deskService;
	@Autowired
	public LevelService levelService;
	@Autowired
	public OfficeService officeService;
	@Autowired
	public RoomService roomService;
	
	@GetMapping(value="")
	public List<DeskDTO> findAll() {
		List<DeskDTO> list = ObjectMapperUtils.mapAll(deskService.findAll(), DeskDTO.class);
		for(DeskDTO deskDTO : list) {
			Room room = roomService.findById(deskDTO.getRoomId());
			deskDTO.setRoomNumber(room.getNumber());
			Level level = levelService.findById(room.getLevelId());
			deskDTO.setLevelNumber(level.getNumber());
			deskDTO.setOfficeName(officeService.findById(level.getOfficeId()).getName());
		}
		return list;
	}
	
	@GetMapping(value = "/byRoomId/{roomId}")
	public List<DeskDTO> findManyByRoomId(@PathVariable("roomId") String roomId) {
		List<DeskDTO> list = ObjectMapperUtils.mapAll(deskService.findManyByRoomId(roomId), DeskDTO.class);
		Room room = roomService.findById(roomId);
		int roomNumber = room.getNumber();
		Level level = levelService.findById(room.getLevelId());
		int levelNumber = level.getNumber();
		Office office = officeService.findById(level.getOfficeId());
		String officeName = office.getName();
		for(DeskDTO deskDTO : list) {
			deskDTO.setRoomNumber(roomNumber);
			deskDTO.setLevelNumber(levelNumber);
			deskDTO.setOfficeName(officeName);
		}
		return list;
	}
	
	@GetMapping(value = "/byId/{id}")
	public DeskDTO findById(@PathVariable("id") String id) {
		DeskDTO deskDTO = ObjectMapperUtils.map(deskService.findById(id), DeskDTO.class);
		Room room = roomService.findById(deskDTO.getRoomId());
		deskDTO.setRoomNumber(room.getNumber());
		Level level = levelService.findById(room.getLevelId());
		deskDTO.setLevelNumber(level.getNumber());
		deskDTO.setOfficeName(officeService.findById(level.getOfficeId()).getName());
		return deskDTO;
	}
	
	@GetMapping(value = "/byBeaconId/{beaconId}")
	public DeskDTO findManyByBeaconId(@PathVariable("beaconId") String beaconId) {
		DeskDTO deskDTO = ObjectMapperUtils.map(deskService.findByBeaconId(beaconId), DeskDTO.class);
		Room room = roomService.findById(deskDTO.getRoomId());
		deskDTO.setRoomNumber(room.getNumber());
		Level level = levelService.findById(room.getLevelId());
		deskDTO.setLevelNumber(level.getNumber());
		deskDTO.setOfficeName(officeService.findById(level.getOfficeId()).getName());
		return deskDTO;
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save(@RequestBody DeskDTO deskDTO) {
		if(deskDTO.getRoomId() == null) {
			return new ResponseEntity<String>("'roomId' is required", HttpStatus.FORBIDDEN);
		}
		if(roomService.findById(deskDTO.getRoomId()) == null) {
			return new ResponseEntity<String>("'roomId' must be of an existing room", HttpStatus.FORBIDDEN);
		}
		if(deskDTO.getNumber() <= 0) {
			return new ResponseEntity<String>("'number' must be positive", HttpStatus.FORBIDDEN);
		}
		for(Desk desk : deskService.findManyByRoomId(deskDTO.getRoomId())) {
			if(desk.getNumber() == deskDTO.getNumber()) {
				return new ResponseEntity<String>("'number' must be unique between desks of the same room", HttpStatus.FORBIDDEN);
			}
		}
		if(deskDTO.getPositionX() < 0 || deskDTO.getPositionY() < 0) {
			return new ResponseEntity<String>("'possitionX' and 'possitionY' must be positive", HttpStatus.FORBIDDEN);
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
		if(deskDTO.getNumber() <= 0) {
			return new ResponseEntity<String>("'number' must be positive", HttpStatus.FORBIDDEN);
		}
		for(Desk desk : deskService.findManyByRoomId(deskDTO.getRoomId())) {
			if(desk.getNumber() == deskDTO.getNumber()) {
				return new ResponseEntity<String>("'number' must be unique between desks of the same room", HttpStatus.FORBIDDEN);
			}
		}
		if(deskDTO.getPositionX() < 0 || deskDTO.getPositionY() < 0) {
			return new ResponseEntity<String>("'possitionX' and 'possitionY' must be positive", HttpStatus.FORBIDDEN);
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
