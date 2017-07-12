package com.lexicon.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class Trip {
	private String fromDestination;
	private String toDestination;
	private Date departureDate;
	private ArrayList<Ticket> ticketsList;
	private ArrayList<FoodItem> menu;
	private Plane plane;

	public Trip(String fromDestination, String toDestination, Date departureDate, Plane plane) {
		this.fromDestination = fromDestination;
		this.toDestination = toDestination;
		this.departureDate = departureDate;
		this.plane = plane;
		ticketsList = new ArrayList<Ticket>();
		menu = new ArrayList<FoodItem>();
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
		int bookedInFirstClass = ticketsList.stream().filter(s -> (s.getFlightClass() == FlightClass.firstClass)).collect(Collectors.toList()).size();
		
		return (plane.getFirstClass() > bookedInFirstClass);
	}

	public boolean hasSeatsLeftInSecondClass() {
		int bookedInSecondClass = ticketsList.stream().filter(s -> (s.getFlightClass() == FlightClass.secondClass)).collect(Collectors.toList()).size();
		
		return (plane.getSecondClass() > bookedInSecondClass);
	}

	public String toString() {
		return fromDestination + " - " + toDestination + "\t" + departureDate;
	}

}