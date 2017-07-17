package com.lexicon.ui;

import java.util.ArrayList;
import java.util.Scanner;
import com.lexicon.handlers.*;
import com.lexicon.models.*;


// Manages commands at top level as well as hand over of the ticketList 
// between DemoManager, BookingManager and ReportManager.
public class UI {

	String companyName = "FlyHigh";
	String indent3 = "   ";
	
	String cmd;
	Scanner sc = new Scanner(System.in);

	public void start() {

		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		DemoData demoData = new DemoData();
		BookingManager bookingManager = new BookingManager();
		ReportManager reportManager = new ReportManager();

		System.out.println("Welcome to " + companyName);

		System.out.println(indent3 + "Do you want to load demo data? (y/n) > ");
		boolean ok = false;
		do {
			cmd = getCmd();
			switch (cmd) {
			case "y" : {
				System.out.println(" cmd = y");
				demoData.setDemoTickets();
				ticketList = demoData.getDemoTickets();
				ok = true;
				break;
			}
			case "n" : {
				System.out.println(" cmd = n");
				ok = true;
				break;
			}
			default : System.out.println(indent3 + "Invalid command > ");
			}
		} while (!ok);

		
		boolean exitTime = false;
		do {
			System.out.println(indent3 + "Select: book, report, or exit > ");
			cmd = getCmd();
			switch (cmd) {
			case "book" : {				
				//TODO Adjust BookingManager so it takes a ticketList as input and returns it as output after completed bookings.
				bookingManager.start();
				break;
			}
			case "report" : {
				reportManager.printReport(ticketList);
				break;
			}
			case "exit" : {
				System.out.println(indent3 + "Thank you!");
				ok = true;
				break;
			}
			default : {
				System.out.println(indent3 + "Invalid command > ");
			}
			}
		} while (!exitTime);
	}


	public String getCmd() {
		String input = sc.nextLine();
		return input.trim().toLowerCase();
	}
}
