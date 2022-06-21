package com.comarch.ripplehotseat.controller;

import java.util.Date;
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

import com.comarch.ripplehotseat.dto.ReservationDTO;
import com.comarch.ripplehotseat.model.Desk;
import com.comarch.ripplehotseat.model.Level;
import com.comarch.ripplehotseat.model.Office;
import com.comarch.ripplehotseat.model.Reservation;
import com.comarch.ripplehotseat.model.Room;
import com.comarch.ripplehotseat.model.User;
import com.comarch.ripplehotseat.service.DeskService;
import com.comarch.ripplehotseat.service.LevelService;
import com.comarch.ripplehotseat.service.OfficeService;
import com.comarch.ripplehotseat.service.ReservationService;
import com.comarch.ripplehotseat.service.RoomService;
import com.comarch.ripplehotseat.service.UserService;
import com.comarch.ripplehotseat.util.ObjectMapperUtils;

@CrossOrigin(origins = "https://ripple-hot-seat-backend-app.herokuapp.com", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE})
@RestController
@RequestMapping("/reservations")
public class ReservationRestController {

	@Autowired
	public ReservationService reservationService;
	@Autowired
	public DeskService deskService;
	@Autowired
	public LevelService levelService;
	@Autowired
	public OfficeService officeService;
	@Autowired
	public RoomService roomService;
	@Autowired
	public UserService userService;
	
	@GetMapping(value="")
	public List<ReservationDTO> findAll() {
		List<ReservationDTO> list = ObjectMapperUtils.mapAll(reservationService.findAll(), ReservationDTO.class);
		for(ReservationDTO reservationDTO : list) {
			Desk desk = deskService.findById(reservationDTO.getDeskId());
			reservationDTO.setDeskNumber(desk.getNumber());
			Room room = roomService.findById(desk.getRoomId());
			reservationDTO.setRoomNumber(room.getNumber());
			Level level = levelService.findById(room.getLevelId());
			reservationDTO.setLevelNumber(level.getNumber());
			reservationDTO.setOfficeName(officeService.findById(level.getOfficeId()).getName());
			reservationDTO.setUsername(userService.findById(reservationDTO.getUserId()).getUsername());
		}
		return list;
	}
	
	@GetMapping(value="/orderByStartTime")
	public List<ReservationDTO> findAllByOrderByStartTime() {
		List<ReservationDTO> list = ObjectMapperUtils.mapAll(reservationService.findAllByOrderByStartTime(), ReservationDTO.class);
		for(ReservationDTO reservationDTO : list) {
			Desk desk = deskService.findById(reservationDTO.getDeskId());
			reservationDTO.setDeskNumber(desk.getNumber());
			Room room = roomService.findById(desk.getRoomId());
			reservationDTO.setRoomNumber(room.getNumber());
			Level level = levelService.findById(room.getLevelId());
			reservationDTO.setLevelNumber(level.getNumber());
			reservationDTO.setOfficeName(officeService.findById(level.getOfficeId()).getName());
			reservationDTO.setUsername(userService.findById(reservationDTO.getUserId()).getUsername());
		}
		return list;
	}
	
	@GetMapping(value = "/byDeskId/{deskId}")
	public List<ReservationDTO> findManyByDeskId(@PathVariable("deskId") String deskId) {
		List<ReservationDTO> list = ObjectMapperUtils.mapAll(reservationService.findManyByDeskId(deskId), ReservationDTO.class);
		Desk desk = deskService.findById(deskId);
		int deskNumber = desk.getNumber();
		Room room = roomService.findById(desk.getRoomId());
		int roomNumber = room.getNumber();
		Level level = levelService.findById(room.getLevelId());
		int levelNumber = level.getNumber();
		Office office = officeService.findById(level.getOfficeId());
		String officeName = office.getName();
		for(ReservationDTO reservationDTO : list) {
			reservationDTO.setDeskNumber(deskNumber);
			reservationDTO.setRoomNumber(roomNumber);
			reservationDTO.setLevelNumber(levelNumber);
			reservationDTO.setOfficeName(officeName);
			reservationDTO.setUsername(userService.findById(reservationDTO.getUserId()).getUsername());
		}
		return list;
	}
	
	@GetMapping(value = "/byUserId/{userId}")
	public List<ReservationDTO> findManyByUserId(@PathVariable("userId") String userId) {
		List<ReservationDTO> list = ObjectMapperUtils.mapAll(reservationService.findManyByUserId(userId), ReservationDTO.class);
		User user = userService.findById(userId);
		String username = user.getUsername();
		for(ReservationDTO reservationDTO : list) {
			Desk desk = deskService.findById(reservationDTO.getDeskId());
			reservationDTO.setDeskNumber(desk.getNumber());
			Room room = roomService.findById(desk.getRoomId());
			reservationDTO.setRoomNumber(room.getNumber());
			Level level = levelService.findById(room.getLevelId());
			reservationDTO.setLevelNumber(level.getNumber());
			reservationDTO.setOfficeName(officeService.findById(level.getOfficeId()).getName());
			reservationDTO.setUsername(username);
		}
		return list;
	}
	
	@GetMapping(value = "/byStartTime/{startTime}")
	public List<ReservationDTO> findManyByStartTime(@PathVariable("startTime") Date startTime) {
		List<ReservationDTO> list = ObjectMapperUtils.mapAll(reservationService.findManyByStartTime(startTime), ReservationDTO.class);
		for(ReservationDTO reservationDTO : list) {
			Desk desk = deskService.findById(reservationDTO.getDeskId());
			reservationDTO.setDeskNumber(desk.getNumber());
			Room room = roomService.findById(desk.getRoomId());
			reservationDTO.setRoomNumber(room.getNumber());
			Level level = levelService.findById(room.getLevelId());
			reservationDTO.setLevelNumber(level.getNumber());
			reservationDTO.setOfficeName(officeService.findById(level.getOfficeId()).getName());
			reservationDTO.setUsername(userService.findById(reservationDTO.getUserId()).getUsername());
		}
		return list;
	}
	
	@GetMapping(value = "/byEndTime/{endTime}")
	public List<ReservationDTO> findManyByEndTime(@PathVariable("endTime") Date endTime) {
		List<ReservationDTO> list = ObjectMapperUtils.mapAll(reservationService.findManyByEndTime(endTime), ReservationDTO.class);
		for(ReservationDTO reservationDTO : list) {
			Desk desk = deskService.findById(reservationDTO.getDeskId());
			reservationDTO.setDeskNumber(desk.getNumber());
			Room room = roomService.findById(desk.getRoomId());
			reservationDTO.setRoomNumber(room.getNumber());
			Level level = levelService.findById(room.getLevelId());
			reservationDTO.setLevelNumber(level.getNumber());
			reservationDTO.setOfficeName(officeService.findById(level.getOfficeId()).getName());
			reservationDTO.setUsername(userService.findById(reservationDTO.getUserId()).getUsername());
		}
		return list;
	}
	
	@GetMapping(value = "/byId/{id}")
	public ReservationDTO findById(@PathVariable("id") String id) {
		ReservationDTO reservationDTO = ObjectMapperUtils.map(reservationService.findById(id), ReservationDTO.class);
		Desk desk = deskService.findById(reservationDTO.getDeskId());
		reservationDTO.setDeskNumber(desk.getNumber());
		Room room = roomService.findById(desk.getRoomId());
		reservationDTO.setRoomNumber(room.getNumber());
		Level level = levelService.findById(room.getLevelId());
		reservationDTO.setLevelNumber(level.getNumber());
		reservationDTO.setOfficeName(officeService.findById(level.getOfficeId()).getName());
		reservationDTO.setUsername(userService.findById(reservationDTO.getUserId()).getUsername());
		return reservationDTO;
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> save(@RequestBody ReservationDTO reservationDTO) {
		if(reservationDTO.getDeskId() == null || reservationDTO.getDeskId().isBlank() || reservationDTO.getUserId() == null ||
				reservationDTO.getUserId().isBlank() || reservationDTO.getStartTime() == null || reservationDTO.getEndTime() == null) {
			return new ResponseEntity<String>("'deskId', 'userId', 'startTime', 'endTime' are required", HttpStatus.FORBIDDEN);
		}
		if(deskService.findById(reservationDTO.getDeskId()) == null) {
			return new ResponseEntity<String>("'deskId' must be of an existing desk", HttpStatus.FORBIDDEN);
		}
		if(userService.findById(reservationDTO.getUserId()) == null) {
			return new ResponseEntity<String>("'userId' must be of an existing user", HttpStatus.FORBIDDEN);
		}
		if(!reservationDTO.getIsPermanent() && !reservationDTO.getStartTime().before(reservationDTO.getEndTime())) {
			return new ResponseEntity<String>("'startTime' must be before 'endTime'", HttpStatus.FORBIDDEN);
		}
		reservationDTO.setId(null);
		reservationService.save(ObjectMapperUtils.map(reservationDTO, Reservation.class));
		return new ResponseEntity<String>("Reservation added successfully", HttpStatus.OK);
	}
	
	@PatchMapping(value = "/update/{id}")
	public ResponseEntity<String> update(@PathVariable("id") String id, @RequestBody ReservationDTO reservationDTO) {
		if(reservationDTO.getDeskId() == null || reservationDTO.getDeskId().isBlank() || reservationDTO.getUserId() == null ||
				reservationDTO.getUserId().isBlank() || reservationDTO.getStartTime() == null || reservationDTO.getEndTime() == null) {
			return new ResponseEntity<String>("'deskId', 'userId', 'startTime', 'endTime' are required", HttpStatus.FORBIDDEN);
		}
		if(deskService.findById(reservationDTO.getDeskId()) == null) {
			return new ResponseEntity<String>("'deskId' must be of an existing desk", HttpStatus.FORBIDDEN);
		}
		if(userService.findById(reservationDTO.getUserId()) == null) {
			return new ResponseEntity<String>("'userId' must be of an existing user", HttpStatus.FORBIDDEN);
		}
		if(!reservationDTO.getIsPermanent() && !reservationDTO.getStartTime().before(reservationDTO.getEndTime())) {
			return new ResponseEntity<String>("'startTime' must be before 'endTime'", HttpStatus.FORBIDDEN);
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
