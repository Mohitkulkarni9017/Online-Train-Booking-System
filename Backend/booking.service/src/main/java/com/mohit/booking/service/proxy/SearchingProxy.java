package com.mohit.booking.service.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="searching-service")
@Service
public interface SearchingProxy {
	
	@PutMapping("/lesscount")
	public void lesscount(@RequestBody List<String> lst);

}
