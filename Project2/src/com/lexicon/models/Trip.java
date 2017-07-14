package com.lexicon.models;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Trip {
	private FlightInformation flightInformation;
	private ArrayList<Ticket> ticketsList;

	private int priceInBusiness;
	private int priceInEconomy;
	private ArrayList<FoodItem> menu;
	private Plane plane;

	public Trip(FlightInformation flightInfo, Plane plane, int priceInFirstClass, int priceInSecondClass) {
		this.flightInformation = flightInfo;
		this.plane = plane;
		this.priceInBusiness = priceInFirstClass;
		this.priceInEconomy = priceInSecondClass;

		ticketsList = new ArrayList<Ticket>();
		menu = new ArrayList<FoodItem>();
	}

	public FlightInformation getFlightInformation() {
		return flightInformation;
	}

	public ArrayList<FoodItem> getMenu() {
		return menu;
	}

	public Plane getPlane() {
		return plane;
	}

	public int getPriceInBusiness() {
		return priceInBusiness;
	}

	public int getPriceInEconomy() {
		return priceInEconomy;
	}

	public void setMenu(ArrayList<FoodItem> menu) {
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

	public boolean hasSeatsLeftInBusiness() {
		int bookedInBusiness = ticketsList.stream().filter(s -> (s.getFlightClass() == FlightClass.Business))
				.collect(Collectors.toList()).size();
		return (plane.getBusiness() > bookedInBusiness);
	}

	public boolean hasSeatsLeftInEconomy() {
		int bookedInEconomy = ticketsList.stream().filter(s -> (s.getFlightClass() == FlightClass.Economy))
				.collect(Collectors.toList()).size();
		return (plane.getEconomy() > bookedInEconomy);
	}

}