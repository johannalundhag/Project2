package com.lexicon.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lexicon.models.Plane;
import com.lexicon.models.Trip;

public class BookingManager {
	
	private List<Trip> tripList;
	private BufferedReader br;
	
	public BookingManager() {
		tripList = new ArrayList<>();
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void start() {
		//System.out.println("Application entry point");
		
		createListOfTrips();
		printTripList();
		int index = getIDFromUser("Enter ID of trip you want to book:");

	}
	
	private void createListOfTrips(){
		Plane plane1 = new Plane(1, "747", 5, 5);
		Plane plane = new Plane(1, "747", 10, 5);
		Date date = new Date();
		Trip t1 = new Trip("ARN", "LHR", date, plane);
		Trip t2 = new Trip("ARN", "LAX", date, plane1);
		Trip t3 = new Trip("LHR", "LAX", date, plane);
		tripList.add(t1);
		tripList.add(t2);
		tripList.add(t3);
	}
	
	private void printTripList(){
		for(Trip t: tripList)
			System.out.println((tripList.indexOf(t)+1) +"\t" + t);
		
	}
	
	private int getNumberFromUser(String message) {
		int number = 100;
		System.out.println(message);
		
		while(number == 100){
			try {
				number = Integer.parseInt(br.readLine());
			} catch (Exception e) {
				System.out.println("You did not enter a number. Try again");
			}
		}
		return number;
	}
	
	private int getIDFromUser(String message) {
		int number = getNumberFromUser(message);
		
		while(number > tripList.size() | number == 0){
			number = getNumberFromUser("Not a valid ID. Please try again:");
		}
		return number;
	}

}
