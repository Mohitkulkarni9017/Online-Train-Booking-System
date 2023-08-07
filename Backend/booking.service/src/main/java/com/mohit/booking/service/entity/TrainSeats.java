package com.mohit.booking.service.entity;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Train_Seats")
public class TrainSeats {
	
	@Id
	private String srNo;
	private String trainNo;
	private String trainName;
	private String type;
	private String seatNo;
	private String coach;
	private String status;
	private String userId;
	private LocalDate departureDate;
	
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDate date) {
		this.departureDate = date;
	}
	private String name;
	private String age;
	private String xender;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public String getCoach() {
		return coach;
	}
	public void setCoach(String coach) {
		this.coach = coach;
	}

	public TrainSeats(String trainNo, String trainName, String type, String seatNo, String coach) {
		super();
		this.trainNo = trainNo;
		this.trainName = trainName;
		this.type = type;
		this.seatNo = seatNo;
		this.coach = coach;
	}
	public TrainSeats() {
		super();
	}
	
	
}
