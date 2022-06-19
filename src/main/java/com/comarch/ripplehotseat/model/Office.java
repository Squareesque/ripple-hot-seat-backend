package com.comarch.ripplehotseat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "offices")
public class Office {

	@Id
	private String id;
	private String name;
	
	public Office() {}
	
	public Office(String name){
		this.name = name;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Office [id=" + id + ", name=" + name + "]";
	}
}
