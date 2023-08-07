package com.mohit.DataOperatingservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mohit.DataOperatingservice.entity.TrainDetails;
import com.mohit.railway.searching.service.exception.TrainNotFoundException;




@FeignClient(name="searching-service")
@Service
public interface SearchingControllerProxy {
	
	@PostMapping("/add")
    public String addTrainDetails(@RequestBody TrainDetails trainDetails);
	
	@DeleteMapping("/removetrain")
	public String deleteTrain(@RequestBody int id);

	
	@PutMapping("/updatetrain")
	public String updateTrain(@RequestBody TrainDetails trainDetails)throws TrainNotFoundException;



}
