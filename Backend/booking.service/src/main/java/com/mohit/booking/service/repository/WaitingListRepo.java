package com.mohit.booking.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mohit.booking.service.entity.WaitingList;


@Repository
public interface WaitingListRepo extends MongoRepository<WaitingList, Integer>{
	
	

}
