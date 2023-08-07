package com.mohit.railway.searching.service.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Dates {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainNo")
    private TrainDetails trainDetails;

    private LocalDate departureDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TrainDetails getTrainDetails() {
		return trainDetails;
	}

	public void setTrainDetails(TrainDetails trainDetails) {
		this.trainDetails = trainDetails;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public Dates(Long id, LocalDate departureDate) {
		super();
		this.id = id;
		this.departureDate = departureDate;
	}

	public Dates() {
		super();
	}
    
    

}
