package com.mohit.booking.service.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mohit.booking.service.entity.Dates;
import com.mohit.booking.service.entity.Form;
import com.mohit.booking.service.entity.TrainSeats;
import com.mohit.booking.service.entity.Traindto;
import com.mohit.booking.service.entity.WaitingList;
import com.mohit.booking.service.proxy.SearchingProxy;
import com.mohit.booking.service.repository.TrainSeatsRepo;
import com.mohit.booking.service.repository.WaitingListRepo;

@Service
public class TrainService {
	
	@Autowired
	private TrainSeatsRepo repo;
	
	@Autowired
	private WaitingListRepo repo1;
	
	@Autowired
	private SearchingProxy proxy;
	
	List<String> sl = new ArrayList<>(
            List.of("Upper", "Middle", "Lower", "Upper", "Middle", "Lower", "SideUpper" ,"SideLower"));
	
	List<String> ac2t = new ArrayList<>(
            List.of("Upper", "Lower", "Upper", "Lower", "SideUpper" ,"SideLower"));
 
	int count = 0;
	int sr = 1;
	public void adddetails(Traindto obj)
	{
		for(Dates date: obj.getTrainSchedule())
		{
			for(int i = 1;i<=15;i++)
			{
				count = 0;
				for(int j =1; j<=120; j++)
				{
					TrainSeats obj1 = new TrainSeats();
					obj1.setDepartureDate(date.getDepartureDate());
					obj1.setTrainNo(obj.getTrainNo());
					obj1.setTrainName(obj.getTrainName());
					if(i<6)
					{
						obj1.setType("Sleeper");
						obj1.setSeatNo("SL/"+i+"/"+j);
						obj1.setCoach(sl.get(count));
						count = (count == 7) ? 0 : (count+1);
//						
					}else if(i>5 && i < 11)
					{
						obj1.setType("2 Tier AC");
						obj1.setSeatNo("2A/"+i+"/"+j);
						obj1.setCoach(ac2t.get(count));
						count = (count == 5) ? 0 : (count+1); 
//						
					}else {
						obj1.setType("3 Tier AC");
						obj1.setSeatNo("3A/"+i+"/"+j);
						obj1.setCoach(sl.get(count));
						count = (count == 7) ? 0 : (count+1);
//						
					}
					obj1.setStatus("NotBooked");
					obj1.setSrNo(String.valueOf(sr));
					repo.save(obj1);
					sr++;
				}
			}
		}
		
		
	}
	
	
	public void removetrain(@RequestBody String id)
	{
		repo.deleteByTrainNo(id);
	}
	
	public List<Object> bookTickits(List<Form> lst) {
	    List<Object> ans = new ArrayList<>();
//	    lst.sort(null);;
	    for (Form form : lst) {
	    	System.out.println(form.getPrefenence());
	    	if(form.getPrefenence().equalsIgnoreCase("No"))
	    	{
	    		System.out.println("1");
		        TrainSeats seat3 = repo.findFirstByStatusAndTypeAndDepartureDateAndTrainNo("NotBooked", form.getType(),form.getDepartureDate(),form.getTrainNo());
		        seat3.setName(form.getName());
	            seat3.setAge(form.getAge());
	            seat3.setXender(form.getXender());
	            seat3.setStatus("Booked/Confirmend");
	            seat3.setDepartureDate(form.getDepartureDate());
	            repo.save(seat3);
	            ans.add(seat3);
	            List<String> lst1 = new ArrayList<>();
	            lst1.add(form.getType());
	            lst1.add(form.getTrainNo());
	            proxy.lesscount(lst1);
	    	}else
	    	{
	    		
	    		TrainSeats seat = repo.findFirstByStatusAndTypeAndCoachAndDepartureDateAndTrainNo("NotBooked", form.getType(), form.getPrefenence(),form.getDepartureDate(), form.getTrainNo());
		        if (seat != null) {
		        	System.out.println("2");
		            seat.setName(form.getName());
		            seat.setAge(form.getAge());
		            seat.setXender(form.getXender());
		            seat.setStatus("Booked/Confirmed");
		            seat.setDepartureDate(form.getDepartureDate());
		            repo.save(seat);
		            ans.add(seat);
		            List<String> lst1 = new ArrayList<>();
		            lst1.add(form.getType());
		            lst1.add(form.getTrainNo());
		            proxy.lesscount(lst1);
		        }else {
		        	TrainSeats seat1 = repo.findFirstByStatusAndTypeAndDepartureDateAndTrainNo("NotBooked", form.getType(),form.getDepartureDate(),form.getTrainNo());
		        	if(seat1 != null)
		        	{
		        		System.out.println("3");
			        	seat1.setName(form.getName());
			            seat1.setAge(form.getAge());
			            seat1.setXender(form.getXender());
			            seat1.setStatus("Booked/Confirmend");
			            seat1.setDepartureDate(form.getDepartureDate());
			            repo.save(seat1);
			            ans.add(seat1);
			            List<String> lst1 = new ArrayList<>();
			            lst1.add(form.getType());
			            lst1.add(form.getTrainNo());
			            proxy.lesscount(lst1);
		        	}else {
		        		System.out.println("4");
		        		WaitingList seat2 = new WaitingList();
		        		seat2.setTrainNo(form.getTrainNo());
		        		seat2.setTrainName(form.getTrainName());
		        		seat2.setType(form.getType());
		        		long last = repo1.count() + 1;
		        		seat2.setSeatNo("WL/"+String.valueOf(last));
		        		seat2.setCoach(null);
		        		seat2.setStatus("Booked/Not Confirmed");
		        		seat2.setName(form.getName());
		        		seat2.setAge(form.getAge());
		        		seat2.setXender(form.getXender());
			            seat2.setDepartureDate(form.getDepartureDate());
		        		ans.add(seat2);
		        		repo1.save(seat2);
		        	}
		        }
	    	}
	        
	    }
	    
	    return ans;
	}

}




