package com.comarch.ripplehotseat.dto;

import java.util.Date;

public class ReservationDTO {

	private String id;
	private Date startTime;
	private Date endTime;
	private String deskId;
	private String userId;
	private boolean isPermanent;

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
	
	public boolean getIsPermanent() {
		return isPermanent;
	}
	
	public void setIsPermanent(boolean isPermanent) {
		this.isPermanent = isPermanent;
	}
	
}
