package com.lexicon.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lexicon.models.Customer;
import com.lexicon.models.FlightClass;
import com.lexicon.models.FlightInformation;
import com.lexicon.models.Plane;
import com.lexicon.models.Ticket;
import com.lexicon.models.Trip;

public class BookingManager {

	private List<Trip> tripList;
	private BufferedReader br;

	public BookingManager() {
		tripList = new ArrayList<>();
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	public void start() {
		// Setup before being able to run it.
		createListOfTrips();

		// Get the journey that the customer wants to take.
		printTripList();
		int index = getIDFromUser("Enter ID of trip you want to book:");

		// If the customer wants to fly first or second class.

		FlightClass flightClass = (getClassFromCustomerForTrip(tripList.get(index)) == 1 ? FlightClass.firstClass
				: FlightClass.secondClass);

		int price = 0;
		Customer customer = new Customer("Test");

		Ticket ticket = new Ticket(price, customer, flightClass, tripList.get(index).getFlightInformation());
	}

	private void createListOfTrips() {
		Date date = new Date();
		FlightInformation fi1 = new FlightInformation("ARN", "LHR", date);
		FlightInformation fi2 = new FlightInformation("ARN", "LAX", date);
		FlightInformation fi3 = new FlightInformation("LHR", "LAX", date);

		Plane plane1 = new Plane(1, "747", 5, 5);
		Plane plane2 = new Plane(1, "747", 10, 5);

		tripList.add(new Trip(fi1, plane1));
		tripList.add(new Trip(fi2, plane2));
		tripList.add(new Trip(fi3, plane1));
	}

	private void printTripList() {
		System.out.println(String.format("%3s%15s%15s", "ID", "Route", "Date"));

		for (Trip t : tripList)
			if (t.hasSeatsLeftInFirstClass() && t.hasSeatsLeftInSecondClass())
				System.out.println(String.format("%3d%30s", (tripList.indexOf(t) + 1), t.getFlightInformation()));
	}

	/*
	 * Classes for getting input from user Checks for invalid input
	 */
	private int getNumber1or2FromCustomer(String message) {
		int number = 0;

		while (!(number == 1) && !(number == 2))
			number = getNumberFromUser(message);

		return number;
	}

	private int getNumberFromUser(String message) {
		int number = 100;
		System.out.println(message);

		while (number == 100) {
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

		while (number > tripList.size() | number == 0)
			number = getNumberFromUser("Not a valid ID. Please try again:");
		return number;
	}

	private int getClassFromCustomerForTrip(Trip trip) {
		int firstOrSecondClass = getNumber1or2FromCustomer(	"In what class do you want to travel, first(1) or second(2)?:");

		if (firstOrSecondClass == 1) {

			if (!trip.hasSeatsLeftInFirstClass())
				if (askCustomerToChangeClass())
					firstOrSecondClass = 2;
				else
					System.out.println(); 					// exit program
		} else {
			if (!trip.hasSeatsLeftInSecondClass())
				if (askCustomerToChangeClass())
					firstOrSecondClass = 1;
				else
					System.out.println();					// exit
		}

		return firstOrSecondClass;
	}

	private boolean askCustomerToChangeClass() {

		int answer = getNumberFromUser(
				"There are no seats left in prefered class, want to change class Yes(1), No(2)?:");
		return (answer == 1);
	}
}
