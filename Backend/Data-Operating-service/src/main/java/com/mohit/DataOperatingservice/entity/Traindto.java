package com.mohit.DataOperatingservice.entity;

import java.util.List;

public class Traindto {
	
	private String trainNo;
	private String trainName;
	private int avsl;
	private int av2a;
	private int av3a;
	private List<Dates> trainSchedule;

	public List<Dates> getTrainSchedule() {
		return trainSchedule;
	}
	public void setTrainSchedule(List<Dates> trainSchedule) {
		this.trainSchedule = trainSchedule;
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
	public int getAvsl() {
		return avsl;
	}
	public void setAvsl(int avsl) {
		this.avsl = avsl;
	}
	public int getAv2a() {
		return av2a;
	}
	public void setAv2a(int av2a) {
		this.av2a = av2a;
	}
	public int getAv3a() {
		return av3a;
	}
	public void setAv3a(int av3a) {
		this.av3a = av3a;
	}
	public Traindto( String trainNo, String trainName, int avsl, int av2a, int av3a, List<Dates> trainSchedule ) {
		super();
		this.trainNo = trainNo;
		this.trainName = trainName;
		this.avsl = avsl;
		this.av2a = av2a;
		this.av3a = av3a;
		this.trainSchedule = trainSchedule;
	}
	
	

}
