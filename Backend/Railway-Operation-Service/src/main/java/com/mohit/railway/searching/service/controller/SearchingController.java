package com.mohit.railway.searching.service.controller;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.railway.searching.service.entity.Train;
import com.mohit.railway.searching.service.entity.TrainDetails;
import com.mohit.railway.searching.service.exception.TrainNotFoundException;
import com.mohit.railway.searching.service.service.SearchingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.mohit.railway.searching.service.entity.TrainDetails;
import com.mohit.railway.searching.service.exception.TrainNotFoundException;
import com.mohit.railway.searching.service.service.SearchingService;

@RestController
public class SearchingController {

	@Autowired
	private SearchingService service;
	
	

	@PostMapping("/gettrains")
	public Object searchTrain(@RequestBody Map<String, Object> details) {
	    String source = details.get("source").toString();
	    String dest = details.get("destination").toString();
	    String date = details.get("date").toString();
	    LocalDate date1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

	    try {
	        List<Train> result = service.searchTrain(source, dest, date1);
	        return result;
//	        return ResponseEntity.status(HttpStatus.OK).body(result);
	    } catch (TrainNotFoundException e) {
	        String errorMessage = e.getMessage();
	        return errorMessage;
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	    }
	}



	@PostMapping("/add")
    public String addTrainDetails(@RequestBody TrainDetails trainDetails) {
    	
    	return service.addTrainDetails(trainDetails);
        
    }
	
	@DeleteMapping("/removetrain")
	public String deleteTrain(@RequestBody int id)
	{
		try {			
			return service.deleteTrain(id);
		}catch(Exception e) {
			return e.getMessage();
		}
	}


	@PutMapping("/updatetrain")
	public String updateTrain(@RequestBody TrainDetails trainDetails) throws TrainNotFoundException
	{
		return service.updateTrain(trainDetails);
	}
	
	@PutMapping("/lesscount")
	public void lesscount(@RequestBody List<String> lst)
	{
		service.lesscount(lst);
	}

}


