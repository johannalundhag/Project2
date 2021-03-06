package com.lexicon.handlers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
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
		DemoData demoData = new DemoData();
		demoData.setDemoTickets();
		demoList = demoData.getDemoTickets();
		
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
				report.printReport(ticketList);
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
		LocalDate date = LocalDate.now().minusDays(1);
		FlightInformation fi1 = new FlightInformation("ARN", "LHR", date);
		FlightInformation fi2 = new FlightInformation("ARN", "LAX", date);
		FlightInformation fi3 = new FlightInformation("LHR", "LAX", date);
		FlightInformation flight1 = new FlightInformation("ARN", "CPH", LocalDate.parse("2015-02-01"));
		FlightInformation flight2 = new FlightInformation("CPH", "ARN", LocalDate.parse("2015-02-02"));
		FlightInformation flight3 = new FlightInformation("ARN", "CPH", LocalDate.parse("2015-02-11"));
		FlightInformation flight4 = new FlightInformation("CPH", "ARN", LocalDate.parse("2015-02-12"));
		FlightInformation flight5 = new FlightInformation("ARN", "CPH", LocalDate.parse("2015-02-13"));
		FlightInformation flight6 = new FlightInformation("CPH", "ARN", LocalDate.parse("2015-02-14"));

		FlightInformation flight101 = new FlightInformation("ARN", "LHR", LocalDate.parse("2016-11-01"));
		FlightInformation flight102 = new FlightInformation("LHR", "ARN", LocalDate.parse("2016-11-02"));
		FlightInformation flight103 = new FlightInformation("ARN", "LHR", LocalDate.parse("2016-11-11"));
		FlightInformation flight104 = new FlightInformation("LHR", "ARN", LocalDate.parse("2016-11-12"));
		FlightInformation flight105 = new FlightInformation("ARN", "LHR", LocalDate.parse("2016-11-13"));
		FlightInformation flight106 = new FlightInformation("LHR", "ARN", LocalDate.parse("2016-11-14"));
		

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

		Ticket ticket1 = new Ticket(20200, customerList.get(0), FlightClass.Business, fi1, getMenu(FlightClass.Business));
		Ticket ticket2 = new Ticket(6000, customerList.get(1), FlightClass.Economy, fi2, getMenu(FlightClass.Economy));
		Ticket ticket3 = new Ticket(21000, customerList.get(0), FlightClass.Business, fi2, getMenu(FlightClass.Business));
		Ticket ticket4 = new Ticket(5000, customerList.get(1), FlightClass.Economy, fi2, getMenu(FlightClass.Economy));
		ticketList.add(ticket1);
		ticketList.add(ticket2);
		ticketList.add(ticket3);
		ticketList.add(ticket4);
		
		Ticket ticket11 = new Ticket(1500, new Customer("Adam Bertilsson"), FlightClass.Economy, flight1, null);
		ticketList.add(ticket11);
		Ticket ticket12 = new Ticket(1500, new Customer("Adam Bertilsson"), FlightClass.Economy, flight2, null);
		ticketList.add(ticket12);
		Ticket ticket13 = new Ticket(1500, new Customer("Adam Bertilsson"), FlightClass.Economy, flight3, null);
		ticketList.add(ticket13);
		Ticket ticket14 = new Ticket(1500, new Customer("Adam Bertilsson"), FlightClass.Economy, flight4, null);
		ticketList.add(ticket14);
		Ticket ticket5 = new Ticket(4000, new Customer("Cecilia Davidsson"), FlightClass.Business, flight5, null);
		ticketList.add(ticket5);
		Ticket ticket6 = new Ticket(4000, new Customer("Cecilia Davidsson"), FlightClass.Business, flight6, null);
		ticketList.add(ticket6);
		Ticket ticket7 = new Ticket(1500, new Customer("Adam Bertilsson"), FlightClass.Economy, flight5, null);
		ticketList.add(ticket7);
		Ticket ticket8 = new Ticket(1500, new Customer("Adam Bertilsson"), FlightClass.Economy, flight6, null);
		ticketList.add(ticket8);

		Ticket ticket101 = new Ticket(5000, new Customer("Adam Bertilsson"), FlightClass.Economy, flight101, null);
		ticketList.add(ticket101);
		Ticket ticket102 = new Ticket(5000, new Customer("Adam Bertilsson"), FlightClass.Economy, flight102, null);
		ticketList.add(ticket102);
		Ticket ticket103 = new Ticket(5000, new Customer("Adam Bertilsson"), FlightClass.Economy, flight103, null);
		ticketList.add(ticket103);
		Ticket ticket104 = new Ticket(5000, new Customer("Adam Bertilsson"), FlightClass.Economy, flight104, null);
		ticketList.add(ticket104);
		Ticket ticket105 = new Ticket(20000, new Customer("Cecilia Davidsson"), FlightClass.Business, flight105, null);
		ticketList.add(ticket105);
		Ticket ticket106 = new Ticket(20000, new Customer("Cecilia Davidsson"), FlightClass.Business, flight106, null);
		ticketList.add(ticket106);
		Ticket ticket107 = new Ticket(5000, new Customer("Adam Bertilsson"), FlightClass.Economy, flight105, null);
		ticketList.add(ticket107);
		Ticket ticket108 = new Ticket(5000, new Customer("Adam Bertilsson"), FlightClass.Economy, flight106, null);
		ticketList.add(ticket108);	

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
