package com.mohit.booking.service.entity;

import java.time.LocalDate;

public class Dates {
	
    private Long id;

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
