package com.lexicon.models;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

	private int price;
	private Customer customer;
	private FlightClass flightClass;
	private List<FoodItem> foodList;
	private FlightInformation flightInformation;

	public Ticket(int price, Customer customer, FlightClass flightClass, FlightInformation flightInformation) {
		this.price = price;
		this.customer = customer;
		this.flightClass = flightClass;
		this.flightInformation = flightInformation;
		this.foodList = new ArrayList<>();
	}

	public int getPrice() {
		return price;
	}

	public Customer getCustomer() {
		return customer;
	}

	public FlightClass getFlightClass() {
		return flightClass;
	}

	public FlightInformation getFlightInformation() {
		return flightInformation;
	}
	
	public void setFoodList(List<FoodItem> newList) {
		this.foodList = newList;
	}
	
	public List<FoodItem> getFoodItemList() {
		return foodList;
	}
	
	public String foodString(){
		if(foodList == null)
			return "No";
		else
			return "Yes";
		
	}
	
	public void printTicket(){
		System.out.println("Name: " + customer.getName());
		System.out.println("Route: " + flightInformation);
		System.out.println("Food: " + foodString());
		System.out.println("Price: " + price);
	}

	public String toString() {
		return String.format("%15s%20s%10s%10d", flightInformation, customer.getName(), flightClass, price);
	}
}
