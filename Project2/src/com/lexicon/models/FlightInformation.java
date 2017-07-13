package com.lexicon.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FlightInformation {

	private String fromDestination;
	private String toDestination;
	private Date departureDate;
	private DateFormat df;

	public FlightInformation(String fromDestination, String toDestination, Date departureDate) {
		this.fromDestination = fromDestination;
		this.toDestination = toDestination;
		this.departureDate = departureDate;
		df = new SimpleDateFormat("dd/MM/yyyy");
	}

	public String getDepartureDate() {
		return df.format(departureDate);
	}
	
	public String toString() {
		return (String.format("%15s%15s", fromDestination + " - " + toDestination, getDepartureDate()));
	}

}
