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
	private String office;
	private byte[] picture;
	
	public Room() {
	}
	
	public Room(int number, int level, String office, byte[] picture) {
		this.number = number;
		this.level = level;
		this.office = office;
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
	
	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", number=" + number + ", level=" + level + ", office=" + office + ", picture=" + picture + "]";
	}
	
}
