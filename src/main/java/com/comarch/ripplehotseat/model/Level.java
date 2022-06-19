package com.comarch.ripplehotseat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "levels")
public class Level {

	@Id
	private String id;
	private String officeId;
	private int number;
	private byte[] picture;
	
	public Level() {}
	
	public Level(String officeId, int number) {
		this.officeId = officeId;
		this.number = number;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getOfficeId() {
		return officeId;
	}
	
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
	@Override
	public String toString() {
		return "Level [id=" + id + ", officeId=" + officeId + ", number=" + number + ", picture=" + picture + "]";
	}
	
}
