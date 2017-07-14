package com.lexicon.models;

public class Ticket {

	private int price;
	private Customer customer;
	private FlightClass flightClass;
	private FoodItem food;
	private FlightInformation flightInformation;

	public Ticket(int price, Customer customer, FlightClass flightClass, FlightInformation flightInformation) {
		this.price = price;
		this.customer = customer;
		this.flightClass = flightClass;
		this.flightInformation = flightInformation;
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

	public FoodItem getFoodItem() {
		return food;
	}
	
	public String foodString(){
		if(food == null)
			return "No";
		else
			return "Yes";
		
	}

	public void setFoodItem(FoodItem food) {
		this.food = food;
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
