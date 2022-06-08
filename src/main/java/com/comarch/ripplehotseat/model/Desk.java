package com.comarch.ripplehotseat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "desks")
public class Desk {
	
	public enum Orientation {
		NORTH,
		NORTH_EAST,
		EAST,
		SOUTH_EAST,
		SOUTH,
		SOUTH_WEST,
		WEST,
		NORTH_WEST;
	}
	
	@Id
	private String id;
	private String roomId;
	private String beaconId;
	private int number;
	private int positionX;
	private int positionY;
	private Orientation orientation;
	
	public Desk(String roomId, String beaconId, int number, int positionX, int positionY, Orientation orientation){
		this.roomId = roomId;
		this.beaconId = beaconId;
		this.number = number;
		this.positionX = positionX;
		this.positionY = positionY;
		this.orientation = orientation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getBeaconId() {
		return beaconId;
	}

	public void setBeaconId(String beaconId) {
		this.beaconId = beaconId;
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

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	
	@Override
	public String toString() {
		return "Desk [id=" + id + ", roomId=" + roomId + ", beaconId=" + beaconId + ", number=" + number +
				", positionX=" + positionX + ", positionY=" + positionY + ", orientation=" + orientation + "]";
	}
	
}
