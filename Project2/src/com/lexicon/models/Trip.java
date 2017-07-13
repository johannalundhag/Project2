package com.lexicon.models;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Trip {
	private FlightInformation flightInformation;
	private ArrayList<Ticket> ticketsList;

	private int priceInFirstClass;
	private int priceInSecondClass;
	private ArrayList<FoodItem> menu;
	private Plane plane;

	public Trip(FlightInformation flightInfo, Plane plane, int priceInFirstClass, int priceInSecondClass) {
		this.flightInformation = flightInfo;
		this.plane = plane;
		this.priceInFirstClass = priceInFirstClass;
		this.priceInSecondClass = priceInSecondClass;

		ticketsList = new ArrayList<Ticket>();
		menu = new ArrayList<FoodItem>();
	}

	public FlightInformation getFlightInformation() {
		return flightInformation;
	}
	
	public ArrayList<FoodItem> getMenu(){
		return menu;
	}

	public Plane getPlane() {
		return plane;
	}

	public int getPriceInFirstClass() {
		return priceInFirstClass;
	}

	public int getPriceInSecondClass() {
		return priceInSecondClass;
	}
	
	public void setMenu(ArrayList<FoodItem> menu){
		this.menu = menu;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public ArrayList<Ticket> getTicketList() {
		return ticketsList;
	}

	public void addTicket(Ticket newTicket) {
		ticketsList.add(newTicket);
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