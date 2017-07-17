package com.lexicon.handlers;

import java.util.ArrayList;
//import java.util.List;
import java.time.LocalDate;
// import java.util.Date;
import com.lexicon.models.*;


// *** KOLLA UPP NAMNGIVNING OCH ANROP/RETURVÄRDEN!

// 	List<FoodItem> food = new ArrayList<>();
//	menu.add(new FoodItem("Koett", "Koett", 200, FlightClass.Economy));
//	menu.add(new FoodItem("Fisk", "Fisk", 200, FlightClass.Economy));
//	menu.add(new FoodItem("Vegetariskt", "Vegetariskt", 200, FlightClass.NONE));
//	menu.add(new FoodItem("Lyx", "Lyx", 200, FlightClass.Business));
//	menu.add(new FoodItem("Flaesk", "Blomkaol", 254, FlightClass.NONE));
//  Ticket ticket = new Ticket(20000, customerList.get(0), FlightClass.Business, fi1, getMenu(FlightClass.Economy));
//  ticketList.add(ticket);


// Creates a list of tickets to be used in demos of the Report Manager class.
// Limitation: No food orders are included currently.
public class DemoData {

	ArrayList<Ticket> demoTickets = new ArrayList<Ticket>();

	String fromDestination;
	String toDestination;
	LocalDate departureDate;

	public DemoData() {
	}
	
	public ArrayList<Ticket> setDemoTickets() {
		
		// Plane(int id, String name, int Business, int Economy)
		Plane plane1 = new Plane(101, "Seagull", 5, 5);

		
		// FlightInformation(String fromDestination, String toDestination, LocalDate departureDate) 
		// FlightInformation(String fromDestination, String toDestination, Date departureDate)
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


		// Trip(FlightInformation flightInfo, Plane plane, int priceInFirstClass, int priceInSecondClass)
		// Not needed for ReportManager??
		// Trip trip1 = new Trip(flight1, plane1, 4000, 1500);
		// Trip trip2 = new Trip(flight2, plane1, 4000, 1500);
		// Trip trip3 = new Trip(flight3, plane1, 4000, 1500);
		// Trip trip4 = new Trip(flight4, plane1, 4000, 1500);
		// Trip trip5 = new Trip(flight5, plane1, 4000, 1500);
		// Trip trip6 = new Trip(flight6, plane1, 4000, 1500);

		// Trip trip101 = new Trip(flight101, plane1, 20000, 5000);
		// Trip trip102 = new Trip(flight102, plane1, 20000, 5000);
		// Trip trip103 = new Trip(flight103, plane1, 20000, 5000);
		// Trip trip104 = new Trip(flight104, plane1, 20000, 5000);
		// Trip trip105 = new Trip(flight105, plane1, 20000, 5000);
		// Trip trip106 = new Trip(flight106, plane1, 20000, 5000);
		

		// Ticket(int price, Customer customer, FlightClass flightClass, FlightInformation flightInformation, List<FoodItem> list)
		Ticket ticket1 = new Ticket(1500, new Customer("Adam Bertilsson"), FlightClass.Economy, flight1, null);
		demoTickets.add(ticket1);
		Ticket ticket2 = new Ticket(1500, new Customer("Adam Bertilsson"), FlightClass.Economy, flight2, null);
		demoTickets.add(ticket2);
		Ticket ticket3 = new Ticket(1500, new Customer("Adam Bertilsson"), FlightClass.Economy, flight3, null);
		demoTickets.add(ticket3);
		Ticket ticket4 = new Ticket(1500, new Customer("Adam Bertilsson"), FlightClass.Economy, flight4, null);
		demoTickets.add(ticket4);
		Ticket ticket5 = new Ticket(4000, new Customer("Cecilia Davidsson"), FlightClass.Business, flight5, null);
		demoTickets.add(ticket5);
		Ticket ticket6 = new Ticket(4000, new Customer("Cecilia Davidsson"), FlightClass.Business, flight6, null);
		demoTickets.add(ticket6);
		Ticket ticket7 = new Ticket(1500, new Customer("Adam Bertilsson"), FlightClass.Economy, flight5, null);
		demoTickets.add(ticket7);
		Ticket ticket8 = new Ticket(1500, new Customer("Adam Bertilsson"), FlightClass.Economy, flight6, null);
		demoTickets.add(ticket8);

		Ticket ticket101 = new Ticket(5000, new Customer("Adam Bertilsson"), FlightClass.Economy, flight101, null);
		demoTickets.add(ticket101);
		Ticket ticket102 = new Ticket(5000, new Customer("Adam Bertilsson"), FlightClass.Economy, flight102, null);
		demoTickets.add(ticket102);
		Ticket ticket103 = new Ticket(5000, new Customer("Adam Bertilsson"), FlightClass.Economy, flight103, null);
		demoTickets.add(ticket103);
		Ticket ticket104 = new Ticket(5000, new Customer("Adam Bertilsson"), FlightClass.Economy, flight104, null);
		demoTickets.add(ticket104);
		Ticket ticket105 = new Ticket(20000, new Customer("Cecilia Davidsson"), FlightClass.Business, flight105, null);
		demoTickets.add(ticket105);
		Ticket ticket106 = new Ticket(20000, new Customer("Cecilia Davidsson"), FlightClass.Business, flight106, null);
		demoTickets.add(ticket106);
		Ticket ticket107 = new Ticket(5000, new Customer("Adam Bertilsson"), FlightClass.Economy, flight105, null);
		demoTickets.add(ticket107);
		Ticket ticket108 = new Ticket(5000, new Customer("Adam Bertilsson"), FlightClass.Economy, flight106, null);
		demoTickets.add(ticket108);	
				
		return demoTickets;
	}

	public ArrayList<Ticket> getDemoTickets() {
		return demoTickets;
	}
}
