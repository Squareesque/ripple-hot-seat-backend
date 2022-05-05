package com.comarch.ripplehotseat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
@Document(collection = "desks")
public class Desk {
	
	public enum Orientation {
		NORTH("north"),
		SOUTH("south"),
		EAST("east"),
		WEST("west");
		
		private String text;
		
		Orientation(String text) {
			this.text = text;
		}
		
		@Override
		public String toString() {
			return text;
		}
	}
	
	@Id
	private String id;
	private String roomId;
	private int positionX;
	private int positionY;
	private Orientation orientation;
	private int number;
	private String beaconId;
	private boolean isFree;
	
	Desk(){
	}
	
	Desk(String roomId, int positionX, int positionY, Orientation orientation, int number, String beaconId, boolean isFree){
		this.roomId = roomId;
		this.positionX = positionX;
		this.positionY = positionY;
		this.setOrientation(orientation);
		this.setNumber(number);
		this.setBeaconId(beaconId);
		this.setFree(isFree);
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getBeaconId() {
		return beaconId;
	}

	public void setBeaconId(String beaconId) {
		this.beaconId = beaconId;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}
	
	@Override
	public String toString() {
		return "Desk [id=" + id + ", roomId=" + roomId + ", positionX=" + positionX + ", positionY=" + positionY
				+ ", orientation=" + orientation + ", number=" + number + ", beaconId=" + beaconId + ", isFree=" + isFree + "]";
	}
	
}
