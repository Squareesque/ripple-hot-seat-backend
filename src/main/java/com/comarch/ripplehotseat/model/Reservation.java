package com.comarch.ripplehotseat.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author Krzysztof Sajkowski
 *
 */
@Document(collection = "reservations")
public class Reservation {
	
	@Id
	private String id;
	private Date startTime;
	private Date endTime;
	private String deskId;
	private String userId;
	private boolean isPermanent;
	
	Reservation(){
	}
	
	Reservation(Date startTime, Date endTime, String deskId, String userId, boolean isPermanent){
		this.startTime = startTime;
		this.endTime = endTime;
		this.deskId = deskId;
		this.userId = userId;
		this.isPermanent = isPermanent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDeskId() {
		return deskId;
	}

	public void setDeskId(String deskId) {
		this.deskId = deskId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean getIsPermantent() {
		return isPermanent;
	}
	
	public void setIsPermanent(boolean isPermanent) {
		this.isPermanent = isPermanent;
	}
	
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", deskId="
				+ deskId + ", userId=" + userId + ", isPermanent=" + isPermanent + "]";
	}
	
}
