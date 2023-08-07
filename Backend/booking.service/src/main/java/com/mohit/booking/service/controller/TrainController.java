package com.mohit.booking.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.booking.service.entity.Form;
import com.mohit.booking.service.entity.TrainSeats;
import com.mohit.booking.service.entity.Traindto;
import com.mohit.booking.service.service.TrainService;

@RestController
@RequestMapping("/book")
public class TrainController {
	
	@Autowired
	private TrainService service;
	
	@PostMapping("/addseats")
	public void adddetails(@RequestBody Traindto obj)
	{
		service.adddetails(obj);
	}
	
	@DeleteMapping("/removeseats")
	public void removetrain(@RequestBody String id)
	{
		service.removetrain(id);
	}
	
//	@CrossOrigin("*")
	@PostMapping("/tickit")
	public List<Object> bookTickits(@RequestBody List<Form> lst)
	{
		return service.bookTickits(lst);
	}

}
