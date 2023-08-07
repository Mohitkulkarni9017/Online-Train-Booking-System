package com.mohit.railway.searching.service.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "train_details")
public class TrainDetails {

	@Id
	private String trainNo;
	private String trainName;
    private int av2a;
    private int av3a;
    private int avsl;
	
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

	//	@OneToMany(mappedBy = "trainDetails", cascade = CascadeType.ALL, orphanRemoval = true)
	@OneToMany(mappedBy = "traindetails")
	private List<TrainRoutes> trainRoutes;
	
	@OneToMany(mappedBy = "trainDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dates> trainSchedule;

	public String getTrainNo() {
		return trainNo;
	}

	public List<Dates> getTrainSchedule() {
		return trainSchedule;
	}
	public void setTrainSchedule(List<Dates> trainSchedule) {
		this.trainSchedule = trainSchedule;
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

	public List<TrainRoutes> getTrainRoutes() {
		return trainRoutes;
	}

	public void setTrainRoutes(List<TrainRoutes> trainRoutes) {
		this.trainRoutes = trainRoutes;
	}

	public TrainDetails(String trainNo, String trainName, int avsl, int av2a, int av3a) {
		super();
		this.trainNo = trainNo;
		this.trainName = trainName;
		this.avsl = avsl;
		this.av2a = av2a;
		this.av3a = av3a;
	}

	public TrainDetails() {
		super();
	}
	
	
}
