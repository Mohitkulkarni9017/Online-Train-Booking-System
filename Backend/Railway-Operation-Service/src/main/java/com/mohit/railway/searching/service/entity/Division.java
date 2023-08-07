package com.mohit.railway.searching.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Division_Record")
public class Division {
	
	@Id
	private String stationName;
	private String stationDivision;
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getstationDivision() {
		return stationDivision;
	}
	public void setstationDivision(String stationDivision) {
		this.stationDivision = stationDivision;
	}
	public Division(String stationName, String stationDivision) {
		super();
		this.stationName = stationName;
		this.stationDivision = stationDivision;
	}
	public Division() {
		super();
	}
	
	

}
