package com.mohit.railway.searching.service.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohit.railway.searching.service.entity.TrainDetails;


@Repository
public interface TrainDetailsRepository extends JpaRepository<TrainDetails,String> {


//	List<TrainDetails> findByDivSourceAndDivDestAndDate(String source, String destination, java.util.Date date);
}