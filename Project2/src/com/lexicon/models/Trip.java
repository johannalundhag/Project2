package com.lexicon.models;

import java.util.ArrayList;
import java.util.Date;

public class Trip {
	private String fromDestination;
	private String toDestination;
	private Date departureDate;
	private ArrayList<Ticket> firstClassTicketsList;
	private ArrayList<Ticket> secondClassTicketsList;
	//private ArrayList<FoodItem> menu;
	private Plane plane;
	
	
	public Trip(String fromDestination, String toDestination, Date departureDate){
		this.fromDestination = fromDestination;
		this.toDestination = toDestination;
		this.departureDate = departureDate;
		firstClassTicketsList = new ArrayList<Ticket>();
		secondClassTicketsList = new ArrayList<Ticket>();
		//menu = new ArrayList<FoodItem>();
	}
	
	public ArrayList<Ticket> getTicketList(){
		ArrayList<Ticket> tmpList = new ArrayList<Ticket>(firstClassTicketsList);
		tmpList.addAll(secondClassTicketsList);
		return tmpList;
	}
	
	 public boolean hasSeatsLeftInFirstClass(){
	 return (plane.getFirstClass() > firstClassTicketsList.size());
	 }
	
	 public boolean hasSeatsLeftInSecondClass(){
	 return (plane.getSecondClass() > secondClassTicketsList.size());
	 }
	
	public String toString(){
		String s = fromDestination + " - " + toDestination + "\t" + departureDate;
				
		return s;
	}
	
}
