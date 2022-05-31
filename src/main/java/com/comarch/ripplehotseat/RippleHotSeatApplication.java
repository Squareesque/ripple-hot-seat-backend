package com.comarch.ripplehotseat;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.comarch.ripplehotseat.model.Desk;
import com.comarch.ripplehotseat.model.Desk.Orientation;
import com.comarch.ripplehotseat.model.Office;
import com.comarch.ripplehotseat.model.Room;
import com.comarch.ripplehotseat.model.User;
import com.comarch.ripplehotseat.service.DeskService;
import com.comarch.ripplehotseat.service.OfficeService;
import com.comarch.ripplehotseat.service.ReservationService;
import com.comarch.ripplehotseat.service.RoomService;
import com.comarch.ripplehotseat.service.UserService;

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
@Component
@SpringBootApplication
public class RippleHotSeatApplication implements CommandLineRunner {

	@Autowired
	public DeskService deskService;
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
		//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		if(deskService.count() == 0 && officeService.count() == 0 && reservationService.count() == 0 && roomService.count() == 0 && userService.count() == 0) {
			initializeDatabase();
			/*
			System.out.println("Database is empty");
			System.out.println("Initialize the database? [yes]");
			if(reader.readLine().equals("yes")) {
				initializeDatabase();
			}
			 */
		}
		return;
		/*
		String command;
		do {
			command = reader.readLine();
			if(command.equals("help")) {
				System.out.println("Avaliable commands:");
				System.out.println(" exit  - exit the application");
				System.out.println(" help  - show commands");
				System.out.println(" reset - drop and re-initialize the database");
			}
			if(command.equals("reset")) {
				System.out.println("Reset the database? [yes]");
				if(reader.readLine().equals("yes")) {
					dropDatabase();
					initializeDatabase();
				}
			}
		}
		while(!command.equals("exit"));
		
		System.exit(0);
		*/
	}
	
	public void dropDatabase() {
		deskService.deleteAll();
		officeService.deleteAll();
		reservationService.deleteAll();
		roomService.deleteAll();
		userService.deleteAll();
	}
	
	public void initializeDatabase() {
		userService.save(new User("login", "$2a$10$XYelR9Jo1kGbIPWgdPnNE.EcUo7tiXZauAVu9C7Y2E2D6aMK1I7QW", true));
		officeService.save(new Office("office", null));
		roomService.save(new Room(0, 0, officeService.findAll().get(0).getId(), 0, 0, null));
		deskService.save(new Desk(roomService.findAll().get(0).getId(), 0, 0, Orientation.SOUTH, 0, null, true));
	}

}
