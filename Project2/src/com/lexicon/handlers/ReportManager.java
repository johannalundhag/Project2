package com.lexicon.handlers;

import java.time.*;
import java.util.Scanner;
import java.util.ArrayList;
// import java.util.List;
import com.lexicon.models.*;


// Generates economic reports.
public class ReportManager {

	Scanner sc = new Scanner(System.in);


	public ReportManager() {
	}
	

	public void printReport(ArrayList<Ticket> ticketList) {

		String companyName = "FlyHigh";
		String indent3 = "   ";
		
		float margin = 0.3F;
		int accSales = 0;
		
		LocalDate todayMinus1 = LocalDate.now().minusDays(1);
		LocalDate firstDate = LocalDate.parse("0000-01-01");
		LocalDate finalDate = todayMinus1;

		boolean ok = false;				
		do {

			boolean ok1 = false;
			do {
				System.out.println(indent3 + "Give beginning day of report period (yyyy-mm-dd) > ");
				try {
					firstDate = LocalDate.parse(getIndata());
					if (firstDate.isAfter(todayMinus1)) {
						System.out.println(indent3 + "Report period must start before today.");
					} else { ok1 = true; }
				}
				catch (DateTimeException e) {
					System.out.println(indent3 + "Wrong date format, use yyyy-mm-dd > ");
					ok1 = false; 
				}
			} while (!ok1);

			ok1 = false;
			do {
				System.out.println(indent3 + "Give end day of report period (yyyy-mm-dd) > ");
				try {
					finalDate = LocalDate.parse(getIndata());
					if (finalDate.isAfter(todayMinus1)) {
						System.out.println(indent3 + "Report period must end before today.");
					} else { ok1 = true; }}
				catch (DateTimeException e) {
					System.out.println(indent3 + "Wrong date format, use yyyy-mm-dd > ");
					ok1 = false; 
				}
			} while (!ok1);
			if (firstDate.isAfter(finalDate)) {
				System.out.println("The first date cannot be after the end date.");
			} else  { ok = true; }
		} while (!ok);


		System.out.println("Economic Report for " + companyName);

		// firstDate = LocalDate.parse("2016-09-28");		// Basic test
		// finalDate = LocalDate.parse("2017-08-18");		// Basic test	
		LocalDate firstDateMinus1 = firstDate.minusDays(1);
		LocalDate finalDatePlus1 = finalDate.plusDays(1);

		System.out.println(indent3 + "Period: " + firstDate + " - " + finalDate);

		LocalDate d;
		for (Ticket t : ticketList) {
			d = t.getFlightInformation().getDepartureLocalDate();	
			if (d.isAfter(firstDateMinus1) && d.isBefore(finalDatePlus1)) {
				accSales = accSales + t.getPrice();
			}
		}

		System.out.println(indent3 + "Total sales : " + accSales);
		System.out.println(indent3 + "Total income: " + Math.round(accSales * margin));	
	}


	public String getIndata() {
		String input = sc.nextLine();
		return input.trim().toLowerCase();
	}
}
