package com.lexicon.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import com.lexicon.models.Customer;
import com.lexicon.models.FlightClass;
import com.lexicon.models.FlightInformation;
import com.lexicon.models.FoodItem;
import com.lexicon.models.Plane;
import com.lexicon.models.Ticket;
import com.lexicon.models.Trip;

public class BookingManager {

	private ArrayList<Trip> tripList;
	private ArrayList<Customer> customerList;
	private ArrayList<Ticket> ticketList;
	private BufferedReader br;
	private String[] options;

	public BookingManager() {
		tripList = new ArrayList<>();
		customerList = new ArrayList<>();
		ticketList = new ArrayList<>();
		br = new BufferedReader(new InputStreamReader(System.in));

		options = new String[] { "Book trip", "List tickets", "Exit" };
	}

	public void start() {
		// Setup before being able to run it.
		createListOfTrips();

		int number;
		boolean doAgain = true;
		printOptions();

		while (doAgain) {
			number = getNumberFromUser("\nWhat do you want to do? To list options type 0.\nPlease enter number:");

			switch (number) {

			case 1:
				doBookings();
				break;
			case 2: // edit item
				printTicketList();
				break;

			case 5: // exit
				doAgain = false;
				break;
			default:
				printOptions();
				break;
			}
		}
	}

	private void doBookings() {
		while (true) {
			int price = 0;
			FlightClass flightClass;
			Trip tripChoosen;

			printTripList();
			tripChoosen = tripList.get(getIDFromUser("\nEnter ID of trip you want to book:", tripList));

			flightClass = (getClassFromUserForTrip(tripChoosen) == 1 ? FlightClass.Business : FlightClass.Economy);

			price = (flightClass == FlightClass.Business ? tripChoosen.getPriceInBusiness()
					: tripChoosen.getPriceInEconomy());

			if (getNumber1or2FromUser("Do you want to add food to your journey, Yes(1) or No(2)?:") == 1) {
				FoodItem food = decideFoodOnThePlane(tripChoosen, flightClass);
				price += food.getPrice();
			}

			System.out.println("The total price will be: " + price);

			Ticket ticket = new Ticket(price, customerList.get(0), flightClass, tripChoosen.getFlightInformation());
			tripChoosen.addTicket(ticket);
			ticketList.add(ticket);

			printTicketInformatio(ticket);
		}
	}

	private void createListOfTrips() {
		Date date = new Date();
		FlightInformation fi1 = new FlightInformation("ARN", "LHR", date);
		FlightInformation fi2 = new FlightInformation("ARN", "LAX", date);
		FlightInformation fi3 = new FlightInformation("LHR", "LAX", date);

		Plane plane1 = new Plane(1, "747", 5, 5);
		Plane plane2 = new Plane(1, "747", 10, 5);

		Customer customer1 = new Customer("Nisse");
		Customer customer2 = new Customer("Staffan");

		customerList.add(customer1);
		customerList.add(customer2);

		tripList.add(new Trip(fi1, plane1, 20000, 5000));
		tripList.add(new Trip(fi2, plane2, 20000, 5000));
		tripList.add(new Trip(fi3, plane1, 20000, 5000));

		FoodItem food1 = new FoodItem("Kött", "Kött", 200, FlightClass.Economy);
		FoodItem food2 = new FoodItem("Fisk", "Fisk", 200, FlightClass.Economy);
		FoodItem food3 = new FoodItem("Vegetariskt", "Vegetariskt", 200, FlightClass.NONE);
		FoodItem food4 = new FoodItem("Lyx", "Lyx", 200, FlightClass.Business);

		ArrayList<FoodItem> menu = new ArrayList<FoodItem>(Arrays.asList(food1, food2, food3, food4));

		Ticket ticket = new Ticket(20000, customerList.get(0), FlightClass.Business, fi1);
		ticketList.add(ticket);

		tripList.get(0).setMenu(menu);
		tripList.get(1).setMenu(menu);
		tripList.get(2).setMenu(menu);
	}

	private FoodItem decideFoodOnThePlane(Trip trip, FlightClass flightClass) {

		trip.getMenu().stream().filter(f -> f.getAssociation() == FlightClass.NONE || f.getAssociation() == flightClass)
				.collect(Collectors.toList())
				.forEach(s -> System.out.println((trip.getMenu().indexOf(s) + 1) + "\t" + s));

		FoodItem food = trip.getMenu().get(getIDFromUser("\nEnter ID of food you want to eat:", trip.getMenu()));
		return food;
	}

	/*
	 * Classes for printing information and lists to the user
	 */
	private void printOptions() {
		System.out.println("\nOptions:");
		System.out.println("------------------------");

		for (int i = 0; i < options.length; i++)
			System.out.println((i + 1) + "\t" + options[i]);
	}

	private void printTripList() {
		System.out.println(String.format("%3s%11s%13s", "ID", "Route", "Date"));

		for (Trip t : tripList)
			if (t.hasSeatsLeftInBusiness() && t.hasSeatsLeftInEconomy())
				System.out.println(String.format("%3d%30s", (tripList.indexOf(t) + 1), t.getFlightInformation()));
	}

	private void printTicketList() {
		System.out.println(String.format("%15s%20s%10s%10s", "Route", "Customer", "Class", "Price"));

		for (Ticket t : ticketList)
			System.out.println(t);
	}

	private void printTicketInformatio(Ticket ticket) {
		System.out.println("");

	}

	/*
	 * Classes for getting input from user Checks for invalid input
	 */
	private int getNumber1or2FromUser(String message) {
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

	private int getIDFromUser(String message, ArrayList<?> list) {
		int number = getNumberFromUser(message);

		while (number > list.size() || number == 0)
			number = getNumberFromUser("Not a valid ID. Please try again:");
		return number;
	}

	private int getClassFromUserForTrip(Trip trip) {
		int firstOrSecondClass = getNumber1or2FromUser("In what class do you want to travel, first(1) or second(2)?:");

		if (firstOrSecondClass == 1) {

			if (!trip.hasSeatsLeftInBusiness())
				if (askUserToChangeClass())
					firstOrSecondClass = 2;
				else
					System.out.println(); // exit program
		} else {
			if (!trip.hasSeatsLeftInEconomy())
				if (askUserToChangeClass())
					firstOrSecondClass = 1;
				else
					System.out.println(); // exit
		}

		return firstOrSecondClass;
	}

	private boolean askUserToChangeClass() {
		int answer = getNumberFromUser(
				"There are no seats left in prefered class, want to change class Yes(1), No(2)?:");
		return (answer == 1);
	}
}
