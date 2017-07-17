package com.lexicon.handlers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.lexicon.models.Customer;
import com.lexicon.models.FlightClass;
import com.lexicon.models.FlightInformation;
import com.lexicon.models.FoodItem;
import com.lexicon.models.Plane;
import com.lexicon.models.Ticket;
import com.lexicon.models.Trip;

public class BookingManager {
	
	private ArrayList<Ticket> demoList;
	private ReportManager report;
	private ArrayList<Trip> tripList;
	private ArrayList<Customer> customerList;
	private ArrayList<Ticket> ticketList;
	private ArrayList<FoodItem> menu;
	private BufferedReader br;
	private String[] options;

	public BookingManager() {
		demoList = new DemoData().getDemoTickets();
		report = new ReportManager();
		tripList = new ArrayList<>();
		customerList = new ArrayList<>();
		ticketList = new ArrayList<>();
		menu = new ArrayList<>();

		br = new BufferedReader(new InputStreamReader(System.in));
		options = new String[] { "Book trip", "List tickets", "Calculate profit (total)", "Calculate profit (date-date)", "Exit" };
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
			case 2:
				printTicketList();
				break;
			case 3:
				calculateProfit();
				break;
				
			case 4:
				report.printReport(demoList);
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
		int price = 0;
		FlightClass flightClass;
		Trip tripChoosen;

		printTripList();
		tripChoosen = tripList.get(getIDFromUser("\nEnter ID of trip you want to book:", tripList));

		flightClass = (getClassFromUserForTrip(tripChoosen) == 1 ? FlightClass.Business : FlightClass.Economy);

		price = (flightClass == FlightClass.Business ? tripChoosen.getPriceInBusiness()
				: tripChoosen.getPriceInEconomy());

		List<FoodItem> food = new ArrayList<>();
		if (getNumber1or2FromUser("Do you want to add food to your journey, Yes(1) or No(2)?:") == 1)
			food = decideFoodOnThePlane(flightClass);

		for (FoodItem next : food) {
			price += next.getPrice();
		}

		System.out.println("The total price will be: " + price);

		Ticket ticket = new Ticket(price, customerList.get(0), flightClass, tripChoosen.getFlightInformation(), food);
		tripChoosen.addTicket(ticket);
		ticketList.add(ticket);
		ticket.printTicket();
	}

	private void calculateProfit() {
		double profit = 0;
		for (Ticket t : ticketList)
			profit += t.getPrice() * 0.3;

		System.out.println("The current profit is: " + profit);
	}

	private void createListOfTrips() {
		LocalDate date = LocalDate.now();
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

		menu.add(new FoodItem("Koett", "Koett", 200, FlightClass.Economy));
		menu.add(new FoodItem("Fisk", "Fisk", 200, FlightClass.Economy));
		menu.add(new FoodItem("Vegetariskt", "Vegetariskt", 200, FlightClass.NONE));
		menu.add(new FoodItem("Lyx", "Lyx", 200, FlightClass.Business));
		menu.add(new FoodItem("Flaesk", "Blomkaol", 254, FlightClass.NONE));

		Ticket ticket = new Ticket(20000, customerList.get(0), FlightClass.Business, fi1, getMenu(FlightClass.Economy));
		ticketList.add(ticket);

		tripList.get(0).setMenu(menu);
		tripList.get(1).setMenu(menu);
		tripList.get(2).setMenu(menu);
	}

	private List<FoodItem> getMenu(FlightClass flightClass) {
		return menu.stream().filter(e -> e.getAssociation() == FlightClass.NONE || e.getAssociation() == flightClass)
				.collect(Collectors.toList());
	}

	private void printMenu(List<FoodItem> list) {
		int index = 0;
		for (FoodItem next : list) {
			System.out.println("[" + index++ + "]" + next);
		}
	}

	private List<FoodItem> decideFoodOnThePlane(FlightClass flightClass) {

		int totalCharge = 0;
		List<FoodItem> tempMenu = getMenu(flightClass);
		List<FoodItem> selection = new ArrayList<>();

		boolean isOrdering = true;
		int loopIndex = 0;

		while (isOrdering) {

			// ONLY SHOW IF LOOP RAN MORE THAN ONCE
			if (loopIndex++ > 0) {
				System.out.println("YOUR CURRENT SELECTION:\n");
				for (FoodItem item : selection) {
					System.out.println("- " + item.getName() + " : " + item.getPrice());
				}
				System.out.println("\nThis will be added to your total ticket price: " + totalCharge
						+ ":-\nPick more items if you want, or " + menu.size() + " to finish order");
			}

			System.out.println("Pick something from the menu: ");
			printMenu(tempMenu);
			System.out.println("[" + tempMenu.size() + "] - END ORDER -\n");

			int foodIndex = getNumberFromUser("Pick something from the menu");

			while (foodIndex > tempMenu.size())
				foodIndex = getNumberFromUser("Not a valid ID. Please try again:");

			if (foodIndex == tempMenu.size()) {
				System.out.println(
						"You have finished pre-ordering food. Your new ticket price will be calculated below...");
				isOrdering = false;
				return selection;
			}

			selection.add(tempMenu.get(foodIndex));
			totalCharge = totalCharge + tempMenu.get(foodIndex).getPrice();

		}
		return null;
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
		System.out.println(String.format("%3s%11s", "ID", "Route"));

		for (Trip t : tripList)
			if (t.hasSeatsLeftInBusiness() && t.hasSeatsLeftInEconomy())
				System.out.println(String.format("%3d%11s", (tripList.indexOf(t) + 1), t.getFlightInformation()));
	}

	private void printTicketList() {
		System.out.println(String.format("%15s%20s%10s%10s", "Route", "Customer", "Class", "Price"));

		for (Ticket t : ticketList)
			System.out.println(t);
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
		return (number - 1);
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
