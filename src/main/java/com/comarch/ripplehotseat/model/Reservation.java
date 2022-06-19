package com.comarch.ripplehotseat.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reservations")
public class Reservation {
	
	@Id
	private String id;
	private String deskId;
	private String userId;
	private boolean isPermanent;
	private Date startTime;
	private Date endTime;
	
	public Reservation() {}
	
	public Reservation(String deskId, String userId, boolean isPermanent, Date startTime, Date endTime){
		this.deskId = deskId;
		this.userId = userId;
		this.isPermanent = isPermanent;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public boolean getIsPermanent() {
		return isPermanent;
	}
	
	public void setIsPermanent(boolean isPermanent) {
		this.isPermanent = isPermanent;
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
	
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", deskId="+ deskId + ", userId=" + userId +
				", isPermanent=" + isPermanent + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
}
