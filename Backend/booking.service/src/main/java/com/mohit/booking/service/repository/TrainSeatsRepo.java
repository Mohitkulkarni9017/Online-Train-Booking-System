package com.mohit.booking.service.repository;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mohit.booking.service.entity.TrainSeats;

@Repository
public interface TrainSeatsRepo extends MongoRepository<TrainSeats, Integer> {



	void deleteByTrainNo(String id);

//	TrainSeats findFirstByStatusAndTypeAndCoachAndDepartureDate(String string, String type, String prefenence,
//			LocalDate departureDate);
//
//	TrainSeats findFirstByStatusAndTypeAndDepartureDate(String string, String type, LocalDate departureDate);

	TrainSeats findFirstByStatusAndTypeAndDepartureDateAndTrainNo(String string, String type, LocalDate departureDate,
			String trainNo);

	TrainSeats findFirstByStatusAndTypeAndCoachAndDepartureDateAndTrainNo(String string, String type, String prefenence,
			LocalDate departureDate, String trainNo);

}
