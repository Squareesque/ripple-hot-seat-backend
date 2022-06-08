package com.comarch.ripplehotseat;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.comarch.ripplehotseat.model.Desk;
import com.comarch.ripplehotseat.model.Desk.Orientation;
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

@Component
@SpringBootApplication
public class RippleHotSeatApplication implements CommandLineRunner {

	@Autowired
	public DeskService deskService;
	@Autowired
	public LevelService levelService;
	@Autowired
	public OfficeService officeService;
	@Autowired
	public ReservationService reservationService;
	@Autowired
	public RoomService roomService;
	@Autowired
	public UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(RippleHotSeatApplication.class, args);
	}

	public void run(String... args) throws Exception {
		if(deskService.count() == 0 && levelService.count() == 0 && officeService.count() == 0 && reservationService.count() == 0 && roomService.count() == 0 && userService.count() == 0) {
			initializeDatabase();
		}
		return;
	}
	
	public void dropDatabase() {
		reservationService.deleteAll();
		deskService.deleteAll();
		roomService.deleteAll();
		levelService.deleteAll();
		officeService.deleteAll();
		userService.deleteAll();
	}
	
	@SuppressWarnings("unused")
	public void initializeDatabase() {
		User user1 = userService.save(new User("username", "$2a$10$XYelR9Jo1kGbIPWgdPnNE.EcUo7tiXZauAVu9C7Y2E2D6aMK1I7QW", true));
		User user2 = userService.save(new User("extra-user", "$2a$10$XYelR9Jo1kGbIPWgdPnNE.EcUo7tiXZauAVu9C7Y2E2D6aMK1I7QW", false));
		
		Office office1 = officeService.save(new Office("office"));
		
		Level level1 = levelService.save(new Level(office1.getId(), 0));
		Level level2 = levelService.save(new Level(office1.getId(), 1));
		
		Room room1 = roomService.save(new Room(level1.getId(), 1, 0, 0));
		Room room2 = roomService.save(new Room(level1.getId(), 2, 0, 0));
		Room room3 = roomService.save(new Room(level2.getId(), 3, 0, 0));
		Room room4 = roomService.save(new Room(level2.getId(), 4, 0, 0));
		
		Desk desk1 = deskService.save(new Desk(room1.getId(), null, 1, 0, 0, Orientation.SOUTH));
		Desk desk2 = deskService.save(new Desk(room1.getId(), null, 2, 0, 0, Orientation.SOUTH));
		Desk desk3 = deskService.save(new Desk(room1.getId(), null, 3, 0, 0, Orientation.SOUTH));
		Desk desk4 = deskService.save(new Desk(room2.getId(), null, 1, 0, 0, Orientation.SOUTH));
		Desk desk5 = deskService.save(new Desk(room2.getId(), null, 2, 0, 0, Orientation.SOUTH));
		Desk desk6 = deskService.save(new Desk(room2.getId(), null, 3, 0, 0, Orientation.SOUTH));
		Desk desk7 = deskService.save(new Desk(room3.getId(), null, 1, 0, 0, Orientation.SOUTH));
		Desk desk8 = deskService.save(new Desk(room3.getId(), null, 2, 0, 0, Orientation.SOUTH));
		Desk desk9 = deskService.save(new Desk(room3.getId(), null, 3, 0, 0, Orientation.SOUTH));
		Desk desk10 = deskService.save(new Desk(room3.getId(), null, 1, 0, 0, Orientation.SOUTH));
		Desk desk11 = deskService.save(new Desk(room3.getId(), null, 2, 0, 0, Orientation.SOUTH));
		Desk desk12 = deskService.save(new Desk(room3.getId(), null, 3, 0, 0, Orientation.SOUTH));
		
		Date date1, date2, date3, date4, date5, date6, date7, date8, date9, date10;
		date1 = date2 = date3 = date4 = date5 = date6 = date7 = date8 = date9 = date10 = new Date();
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
		try {
			date1 = dateFormat.parse("2022-06-11T08:00:00.000");
			date2 = dateFormat.parse("2022-06-11T16:00:00.000");
			date3 = dateFormat.parse("2022-06-12T08:00:00.000");
			date4 = dateFormat.parse("2022-06-12T16:00:00.000");
			date5 = dateFormat.parse("2022-06-13T08:00:00.000");
			date6 = dateFormat.parse("2022-06-13T16:00:00.000");
			date7 = dateFormat.parse("2022-06-14T08:00:00.000");
			date8 = dateFormat.parse("2022-06-14T16:00:00.000");
			date9 = dateFormat.parse("2022-06-15T08:00:00.000");
			date10 = dateFormat.parse("2022-06-15T16:00:00.000");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Reservation reservation1 = reservationService.save(new Reservation(desk1.getId(), user1.getId(), false, date1, date2));
		Reservation reservation2 = reservationService.save(new Reservation(desk2.getId(), user2.getId(), false, date3, date4));
		Reservation reservation3 = reservationService.save(new Reservation(desk1.getId(), user1.getId(), false, date5, date6));
		Reservation reservation4 = reservationService.save(new Reservation(desk3.getId(), user2.getId(), false, date7, date8));
		Reservation reservation5 = reservationService.save(new Reservation(desk2.getId(), user1.getId(), false, date9, date10));
	}

}
