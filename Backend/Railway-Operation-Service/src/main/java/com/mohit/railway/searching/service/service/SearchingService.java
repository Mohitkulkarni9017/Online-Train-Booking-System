package com.mohit.railway.searching.service.service;


import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.railway.searching.service.entity.Dates;
import com.mohit.railway.searching.service.entity.Division;
import com.mohit.railway.searching.service.entity.Train;
import com.mohit.railway.searching.service.entity.TrainDetails;
import com.mohit.railway.searching.service.entity.TrainRoutes;
import com.mohit.railway.searching.service.exception.TrainNotFoundException;
import com.mohit.railway.searching.service.repository.DatesRepository;
import com.mohit.railway.searching.service.repository.DivisionRepository;
import com.mohit.railway.searching.service.repository.TrainDetailsRepository;
import com.mohit.railway.searching.service.repository.TrainRoutesRepository;

@Service
public class SearchingService {
	
	@Autowired
    private TrainDetailsRepository trainDetailsRepository;

    @Autowired
    private TrainRoutesRepository trainRoutesRepository;

    @Autowired
    private DatesRepository datesRepository;
    
    @Autowired
    private DivisionRepository divisionRepository;
    
    public String addTrainDetails(TrainDetails trainDetails)
    {
    	try {
            
            TrainDetails savedTrainDetails = trainDetailsRepository.save(trainDetails);

            List<TrainRoutes> trainRoutes = trainDetails.getTrainRoutes();
            for (TrainRoutes route : trainRoutes) {
                route.setTraindetails(savedTrainDetails);
                trainRoutesRepository.save(route);
            }

            List<Dates> trainDates = savedTrainDetails.getTrainSchedule();
            for (Dates date : trainDates) {
                date.setTrainDetails(savedTrainDetails);
                datesRepository.save(date);
            }

            return "Train details added successfully.";
        } catch (Exception e) {
            return "Error adding train details.";
        }
    }


	public List<Train> searchTrain(String source, String dest, LocalDate date) throws TrainNotFoundException
	{
		Division so = divisionRepository.findById(source).orElse(null);
		Division de = divisionRepository.findById(dest).orElse(null);
		if(so==null || de == null)
		{
			throw new TrainNotFoundException("Given Station Name is Not Available");
		}
		String source1 = so.getstationDivision();
		String dest1 = de.getstationDivision();
		
		 List<TrainDetails> trains = trainDetailsRepository.findAll();
	     List<TrainDetails> matchingTrains = new ArrayList<>();

	     for (TrainDetails train : trains) {
	            boolean hasMatchingRoute = train.getTrainRoutes().stream()
	            		.anyMatch(route -> route.getDivSource().equalsIgnoreCase(source1)
	                            && route.getDivDest().equalsIgnoreCase(dest1));

	            boolean hasMatchingDate = train.getTrainSchedule().stream()
	                    .anyMatch(schedule -> schedule.getDepartureDate().equals(date));
	         
	            if (hasMatchingRoute && hasMatchingDate) {
	                matchingTrains.add(train);
	            }
	        }

	   
		
		
		List<Train> ans = new ArrayList<>();
		List<String> trainnum = new ArrayList<>();
//		List<String> trainnum = new ArrayList<String>();
		
		for(TrainDetails i : matchingTrains)
		{
			
			List<TrainRoutes> routes = i.getTrainRoutes();
			for(TrainRoutes j: routes)
			{
				if(source.equalsIgnoreCase(j.getSource())&& dest.equalsIgnoreCase(j.getDestination()) )
				{
					trainnum.add(i.getTrainNo());
					ans.add(new Train(i.getTrainNo(),i.getTrainName(),j.getSource(),j.getDestination(),j.getStartArrivalTime(),j.getEndArrivalTime(),
							j.getDate(),i.getAvsl(),i.getAv2a(),i.getAv3a(),j.getSlFare(),j.getTwoACFare(),j.getThreeACFare()));
					break;
				}
					
			}
		}
		
		
		for(TrainDetails i : matchingTrains)
		{
			if(trainnum.contains(i.getTrainNo()))
			{
				continue;
			}else {
				List<TrainRoutes> routes = i.getTrainRoutes();
				for(TrainRoutes j: routes)
				{
					if(source1.equalsIgnoreCase(j.getDivSource()) && dest1.equalsIgnoreCase(j.getDivDest()))
					{
						ans.add(new Train(i.getTrainNo(),i.getTrainName(),j.getSource(),j.getDestination(),j.getStartArrivalTime(),j.getEndArrivalTime(),
								j.getDate(),i.getAvsl(),i.getAv2a(),i.getAv3a(),j.getSlFare(),j.getTwoACFare(),j.getThreeACFare()));
						break;
					}
						
				}
				
			}
		}
	
		if(!ans.isEmpty())
		{
			return ans;			
		}else {
			throw new TrainNotFoundException("Train Not Found For Given Details");
		}
		
	}	
	

	public String deleteTrain(int id) throws TrainNotFoundException
	{

		String s = String.valueOf(id);
		TrainDetails temp = trainDetailsRepository.findById(s).orElse(null);
		if (temp==null)
		{
			throw new TrainNotFoundException("Invalid Train Number or Date");
		}
		// Delete the train routes associated with the train
		// Delete the train routes associated with the train
		List<TrainRoutes> trainRoutes = temp.getTrainRoutes();
		trainRoutesRepository.deleteAll(trainRoutes);

		// Delete the dates associated with the train
		List<Dates> trainDates = temp.getTrainSchedule();
		trainDates.clear();

		trainDetailsRepository.delete(temp);
		return "Train Removed Successfully";
	}

	
	
	public String updateTrain(TrainDetails trainDetails) throws TrainNotFoundException
	{
		String no = trainDetails.getTrainNo();
	
		if (no == null)
		{
			throw new TrainNotFoundException("Train not found for given details");
		}
		deleteTrain(Integer.valueOf(no));
		addTrainDetails(trainDetails);
		return "Train Details Updated Successfully";
	}

	public void lesscount(List<String> lst)
	{
		String type = lst.get(0);
		String trainNo = lst.get(1);
		TrainDetails temp = trainDetailsRepository.findById(trainNo).orElse(null);
		if(("Sleeper").equalsIgnoreCase(type))
		{
			int sl = temp.getAvsl();
			if(sl!=0)
			{
				sl--;
			}
			temp.setAvsl(sl);
			trainDetailsRepository.save(temp);
		}else if(("2 Tier Ac").equalsIgnoreCase(type))
		{
			int a2 = temp.getAv2a();
			if(a2!=0)
			{
				a2--;
			}
			temp.setAv2a(a2);
			trainDetailsRepository.save(temp);
		}else {
			int a3 = temp.getAv3a();
			if(a3!=0)
			{
				a3--;
			}
			temp.setAv3a(a3);
			trainDetailsRepository.save(temp);
		}
	}

}





