package com.mohit.DataOperatingservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.DataOperatingservice.entity.TrainDetails;

import com.mohit.DataOperatingservice.entity.Traindto;
import com.mohit.DataOperatingservice.proxy.BookingServiceProxy;
import com.mohit.DataOperatingservice.proxy.SearchingControllerProxy;
import com.mohit.railway.searching.service.exception.TrainNotFoundException;

import com.mohit.DataOperatingservice.proxy.SearchingControllerProxy;


@RestController
@RequestMapping("/admin")
public class OperationController {
	
	@Autowired
	private SearchingControllerProxy proxy;
	

	@Autowired
	private BookingServiceProxy proxy1;

	
	@PostMapping("/add")
	public String addTrain1(@RequestBody TrainDetails tdetails)
	{

		Traindto obj = new Traindto(tdetails.getTrainNo(), tdetails.getTrainName(), tdetails.getAvsl(), tdetails.getAv2a(), tdetails.getAv3a(),tdetails.getTrainSchedule());
		proxy1.adddetails(obj);

		String msg = proxy.addTrainDetails(tdetails);
		return msg;
	}
	
	@DeleteMapping("/removetrain/{id}")
	public String deleteTrain(@PathVariable int id)
	{

		proxy1.removetrain(String.valueOf(id));
		return proxy.deleteTrain(id);
	}
	
	@PutMapping("/updatetrain")
	public String updateTrain(@RequestBody TrainDetails trainDetails) throws TrainNotFoundException
	{
		return proxy.updateTrain(trainDetails);
	}

		


}
