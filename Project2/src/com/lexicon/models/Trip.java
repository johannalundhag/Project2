package com.lexicon.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	private DateFormat df;

	public Trip(String fromDestination, String toDestination, Date departureDate, Plane plane) {
		this.fromDestination = fromDestination;
		this.toDestination = toDestination;
		this.departureDate = departureDate;
		this.plane = plane;
		ticketsList = new ArrayList<Ticket>();
		menu = new ArrayList<FoodItem>();
		df = new SimpleDateFormat("dd/MM/yyyy");
	}

	public Plane getPlane() {
		return plane;
	}
	
	public String getDepartureDate() {
		return df.format(departureDate);
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
		return (String.format("%15s%15s", fromDestination + " - " + toDestination, getDepartureDate()));
	}
}