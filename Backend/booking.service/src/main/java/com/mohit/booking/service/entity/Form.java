package com.mohit.booking.service.entity;

import java.time.LocalDate;
import java.util.Date;

public class Form {
	
	private String trainNo;
	private String trainName;
	private String type;
	private String prefenence;
	private String name;
	private String age;
	private String xender;
	private LocalDate departureDate;
	
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}
	public String getTrainNo() {
		return trainNo;
	}
	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrefenence() {
		return prefenence;
	}
	public void setPrefenence(String prefenence) {
		this.prefenence = prefenence;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getXender() {
		return xender;
	}
	public void setXender(String xender) {
		this.xender = xender;
	}
	public Form(String trainNo, String trainName, String type, String prefenence, String name, String age,
			String xender) {
		super();
		this.trainNo = trainNo;
		this.trainName = trainName;
		this.type = type;
		this.prefenence = prefenence;
		this.name = name;
		this.age = age;
		this.xender = xender;
	}
	public Form() {
		super();
	}
	
	
}
