package com.mohit.railway.searching.service.entity;

import java.util.Date;


public class Train {
	
	
	private String number;
	private String name;
	private String source;
	private String destination;
	private String startArrivalTime;
	private String endArrivalTime;
	private Date date;
	private int avsl;
	private int av2a;
	private int av3a;
	private int slFare;
	private int twoACFare;
	private int threeACFare;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
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
	
	public Train(String number, String name, String source, String destination, String startArrivalTime,
			String endArrivalTime, Date date, int avsl, int av2a, int av3a, int slFare, int twoACFare,
			int threeACFare) {
		super();
		this.number = number;
		this.name = name;
		this.source = source;
		this.destination = destination;
		this.startArrivalTime = startArrivalTime;
		this.endArrivalTime = endArrivalTime;
		this.date = date;
		this.avsl = avsl;
		this.av2a = av2a;
		this.av3a = av3a;
		this.slFare = slFare;
		this.twoACFare = twoACFare;
		this.threeACFare = threeACFare;
	}
	public Train() {
		super();
	}
	
	
}