package com.comarch.ripplehotseat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author Krzysztof Sajkowski
 * 
 */
@Document(collection = "rooms")
public class Room {
	
	@Id
	private String id;
	private int number;
	private int level;
<<<<<<< HEAD
	private String officeId;
	private int positionX;
	private int positionY;
=======
	private String office;
>>>>>>> 32e96365e540e802fa5c90eea0df4531bfba69be
	private byte[] picture;
	
	public Room() {
	}
	
<<<<<<< HEAD
	public Room(int number, int level, String officeId, int positionX, int positionY, byte[] picture) {
		this.number = number;
		this.level = level;
		this.officeId = officeId;
		this.positionX = positionX;
		this.positionY = positionY;
=======
	public Room(int number, int level, String office, byte[] picture) {
		this.number = number;
		this.level = level;
		this.office = office;
>>>>>>> 32e96365e540e802fa5c90eea0df4531bfba69be
		this.picture = picture;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

<<<<<<< HEAD
	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public byte[] getPicture() {
		return picture;
	}

=======
	public byte[] getPicture() {
		return picture;
	}

>>>>>>> 32e96365e540e802fa5c90eea0df4531bfba69be
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
<<<<<<< HEAD
		return "Room [id=" + id + ", number=" + number + ", level=" + level + ", officeId=" + officeId + ", positionX=" + positionX + ", positionY=" + positionY + ", picture=" + picture + "]";
=======
		return "Room [id=" + id + ", number=" + number + ", level=" + level + ", office=" + office + ", picture=" + picture + "]";
>>>>>>> 32e96365e540e802fa5c90eea0df4531bfba69be
	}
	
}
