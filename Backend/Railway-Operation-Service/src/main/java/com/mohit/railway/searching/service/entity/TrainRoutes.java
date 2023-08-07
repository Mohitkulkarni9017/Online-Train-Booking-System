package com.mohit.railway.searching.service.entity;

import java.util.Date;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Route_details")
public class TrainRoutes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int srNo;
	
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trainNo")
	private TrainDetails traindetails;
	private String divSource;
	private String divDest;
	private String source;
	private String destination;
	private String startArrivalTime;
	private String endArrivalTime;
	private Date date;
	

	private int slFare;

	private int twoACFare;

	private int threeACFare;
	
	public int getSrNo() {
		return srNo;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	public TrainDetails getTraindetails() {
		return traindetails;
	}
	public void setTraindetails(TrainDetails traindetails) {
		this.traindetails = traindetails;
	}
	public String getDivSource() {
		return divSource;
	}
	public void setDivSource(String divSource) {
		this.divSource = divSource;
	}
	public String getDivDest() {
		return divDest;
	}
	public void setDivDest(String divDest) {
		this.divDest = divDest;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getStartArrivalTime() {
		return startArrivalTime;
	}
	public void setStartArrivalTime(String startArrivalTime) {
		this.startArrivalTime = startArrivalTime;
	}
	public String getEndArrivalTime() {
		return endArrivalTime;
	}
	public void setEndArrivalTime(String endArrivalTime) {
		this.endArrivalTime = endArrivalTime;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getSlFare() {
		return slFare;
	}
	public void setSlFare(int slFare) {
		this.slFare = slFare;
	}
	public int getTwoACFare() {
		return twoACFare;
	}
	public void setTwoACFare(int twoACFare) {
		this.twoACFare = twoACFare;
	}
	public int getThreeACFare() {
		return threeACFare;
	}
	public void setThreeACFare(int threeACFare) {
		this.threeACFare = threeACFare;
	}
	
	
	

}
