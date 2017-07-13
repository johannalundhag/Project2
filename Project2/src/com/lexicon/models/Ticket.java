package com.lexicon.models;

public class Ticket {
	
	private int price;
	private Customer customer;
	private FlightClass flightClass;
	private FoodItem food;
	private FlightInformation flightInformation;

	public Ticket(int price, Customer customer, FlightClass flightClass, FlightInformation flightInformation){
		this.price = price;
		this.customer = customer;
		this.flightClass = flightClass;
		this.flightInformation = flightInformation;
	}
	
	public int getPrice(){
		return price;
	}
	
	public Customer getCustomer(){
		return customer;
	}
	
	public FlightClass getFlightClass(){
		return flightClass;
	}
	
	public FlightInformation getFlightInformation(){
		return flightInformation;
	}
	
	public FoodItem getFoodItem(){
		return food;
	}
	
	public void setFoodItem(FoodItem food){
		this.food = food;
	}
}
