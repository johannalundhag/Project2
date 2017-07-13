package com.lexicon.models;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Trip {
	private FlightInformation flightInformation;
	private ArrayList<Ticket> ticketsList;
	//private ArrayList<FoodItem> menu;
	private Plane plane;

	public Trip(FlightInformation flightInfo, Plane plane) {
		this.flightInformation = flightInfo;
		this.plane = plane;
		ticketsList = new ArrayList<Ticket>();
		//menu = new ArrayList<FoodItem>();
	}
	
	public FlightInformation getFlightInformation(){
		return flightInformation;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public ArrayList<Ticket> getTicketList() {
		return ticketsList;
	}

	public boolean hasSeatsLeftInFirstClass() {
		int bookedInFirstClass = ticketsList.stream().filter(s -> (s.getFlightClass() == FlightClass.firstClass))
				.collect(Collectors.toList()).size();
		return (plane.getFirstClass() > bookedInFirstClass);
	}

	public boolean hasSeatsLeftInSecondClass() {
		int bookedInSecondClass = ticketsList.stream().filter(s -> (s.getFlightClass() == FlightClass.secondClass))
				.collect(Collectors.toList()).size();
		return (plane.getSecondClass() > bookedInSecondClass);
	}


}