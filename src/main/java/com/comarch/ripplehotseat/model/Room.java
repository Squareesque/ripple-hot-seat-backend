package com.comarch.ripplehotseat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rooms")
public class Room {
	
	@Id
	private String id;
	private String levelId;
	private int number;
	private int positionX;
	private int positionY;
	private byte[] picture;
	
	public Room(String levelId, int number, int positionX, int positionY) {
		this.levelId = levelId;
		this.number = number;
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getLevelId() {
		return levelId;
	}
	
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
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
		return "Room [id=" + id + ", levelId=" + levelId + ", number=" + number + ", positionX=" + positionX + ", positionY=" + positionY + ", picture=" + picture + "]";
	}
	
}
