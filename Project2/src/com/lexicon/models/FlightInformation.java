package com.lexicon.models;

import java.text.DateFormat;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
// import java.util.Date;

public class FlightInformation {

	private String fromDestination;
	private String toDestination;
	private LocalDate departureDate;
	private DateFormat df;

	public FlightInformation(String fromDestination, String toDestination, LocalDate departureDate) {
		this.fromDestination = fromDestination;
		this.toDestination = toDestination;
		this.departureDate = departureDate;
		df = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	public LocalDate getDepartureLocalDate() {
		return departureDate;
	}

	public String getDepartureDate() {
		return df.format(departureDate);
	}

	public String toString() {
		return (String.format("%15s", fromDestination + " - " + toDestination));
	}

}
