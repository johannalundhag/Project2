package com.lexicon.ui;

import java.util.ArrayList;
import java.util.Date;

public class Trip {
	private String fromDestination;
	private String toDestination;
	private Date departureDate;
	private ArrayList<Ticket> ticketsList;
	
	
	public Trip(String fromDestination, String toDestination, Date departureDate){
		this.fromDestination = fromDestination;
		this.toDestination = toDestination;
		this.departureDate = departureDate;
		ticketsList = new ArrayList<Ticket>();
	}
	
	
	
	
}
