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
	private String officeId;
	private int positionX;
	private int positionY;
	private byte[] picture;
	
	public Room() {
	}
	
	public Room(int number, int level, String officeId, int positionX, int positionY, byte[] picture) {
		this.number = number;
		this.level = level;
		this.officeId = officeId;
		this.positionX = positionX;
		this.positionY = positionY;
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

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", number=" + number + ", level=" + level + ", officeId=" + officeId + ", positionX=" + positionX + ", positionY=" + positionY + ", picture=" + picture + "]";
	}
	
}
