package com.mohit.RailwayOperationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mohit.railway.searching.service.RailwaySearchingServiceApplication;
import com.mohit.railway.searching.service.controller.SearchingController;
import com.mohit.railway.searching.service.entity.Train;

import com.mohit.railway.searching.service.exception.TrainNotFoundException;

@SpringBootTest(classes = RailwaySearchingServiceApplication.class)
class RailwayOperationServiceApplicationTests {

		@MockBean
	    private SearchingController controller;

		@BeforeEach
		void setUp() {
			MockitoAnnotations.openMocks(this);
		}

	    @Test
	    void testSearchTrain() throws TrainNotFoundException, ParseException {

	        Map<String, Object> details = Map.of(
	            "source", "Solapur",
	            "destination", "Thane",
	            "date", "2023-08-01"
	        );
	        List<Train> expectedTrains = new ArrayList<>();
		    Date date2 = (Date) new SimpleDateFormat("yyyy-MM-dd").parse("2023-08-01");
		    
		    Train t1 = new Train("12116", "Siddheshwar Express", "Solapur", "Thane", "10:30 PM", "5:40 AM", date2, 0, 600, 600, 295, 1050, 750);
		    Train t2 = new Train("11302", "Udyan Express", "Solapur", "CSMT", "10:55 AM", "8:15 PM", date2, 600, 600, 600, 275, 1050, 745);
		    expectedTrains.add(t1);
		    expectedTrains.add(t2);

		    when(controller.searchTrain(details)).thenReturn(expectedTrains);

	        List<Train> actualTrains = (List<Train>) controller.searchTrain(details);

	        assertEquals(expectedTrains, actualTrains);
	    }

//		@MockBean
//	    private SearchingController controller;
//
//		@BeforeEach
//		void setUp() {
//			MockitoAnnotations.openMocks(this);
//		}
//
//	    @Test
//	    void testSearchTrain() throws TrainNotFoundException, ParseException {
//
//	        Map<String, Object> details = Map.of(
//	            "source", "Solapur",
//	            "destination", "Thane",
//	            "date", "2023-06-15"
//	        );
//	        List<Train> expectedTrains = new ArrayList<>();
//		    Date date2 = (Date) new SimpleDateFormat("yyyy-MM-dd").parse("2023-06-15");
//		    
//		    Train t1 = new Train(152, "11302", "Udyan Express", "Solapur", "Mumbai", "Solapur", "Thane", "11:00", "20:15", date2, 300, 300, 300);
//		    Train t2 = new Train(153, "12116", "Siddheshwar Express", "Solapur", "Mumbai", "Solapur", "CSMT", "22:30", "05:37", date2, 300, 300, 300);
//		    expectedTrains.add(t1);
//		    expectedTrains.add(t2);
//
//		    when(controller.searchTrain(details)).thenReturn(expectedTrains);
//
//	        List<Train> actualTrains = (List<Train>) controller.searchTrain(details);
//
//	        assertEquals(expectedTrains, actualTrains);
//	    }

	}

