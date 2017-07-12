package com.lexicon.models;

public class Ticket {
	
	private int price;
	private Customer customer;
	private FlightClass flightClass;
	private FoodItem food;
	

	public Ticket(int price, Customer customer, FlightClass flightClass){
		this.price = price;
		this.customer = customer;
		this.flightClass = flightClass;
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
	
	public FoodItem getFoodItem(){
		return food;
	}
	
	public void setFoodItem(FoodItem food){
		this.food = food;
	}
	
}
