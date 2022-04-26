package com.comarch.ripplehotseat;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.comarch.ripplehotseat.model.Room;
import com.comarch.ripplehotseat.model.User;
import com.comarch.ripplehotseat.service.DeskService;
import com.comarch.ripplehotseat.service.ReservationService;
import com.comarch.ripplehotseat.service.RoomService;
import com.comarch.ripplehotseat.service.UserService;

@SpringBootApplication
public class RippleHotSeatApplication implements CommandLineRunner {

	@Autowired
	public DeskService deskService;
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
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		if(deskService.count() == 0 && reservationService.count() == 0 && roomService.count() == 0 && userService.count() == 0) {
			System.out.println("Database is empty");
			System.out.println("Initialize the database? [yes]");
			if(reader.readLine().equals("yes")) {
				initializeDatabase();
			}
		}
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
	}
	
	public void dropDatabase() {
		deskService.deleteAll();
		reservationService.deleteAll();
		roomService.deleteAll();
		userService.deleteAll();
	}
	
	public void initializeDatabase() {
		/*
		 * login: login
		 * password: password
		 * role: role
		 */
		userService.save(new User("login", "$2a$10$XYelR9Jo1kGbIPWgdPnNE.EcUo7tiXZauAVu9C7Y2E2D6aMK1I7QW", "role"));
		roomService.save(new Room(1));
	}

}
