package com.comarch.ripplehotseat.dto;

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
public class RoomDTO {

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
	
}
