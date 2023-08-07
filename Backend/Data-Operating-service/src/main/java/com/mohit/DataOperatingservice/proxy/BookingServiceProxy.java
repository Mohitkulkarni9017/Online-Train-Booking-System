package com.mohit.DataOperatingservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mohit.DataOperatingservice.entity.TrainDetails;
import com.mohit.DataOperatingservice.entity.Traindto;




@FeignClient(name="booking-service")
@Service
public interface BookingServiceProxy {
	
	@PostMapping("/book/addseats")
	public void adddetails(@RequestBody Traindto obj);
	
	@DeleteMapping("/book/removeseats")
	public void removetrain(@RequestBody String id);
	

}
