package com.lexicon.ui;

public class Ticket {
	
	private int price;
	//private int travelClass; // Not in here as it is right now
	//private Customer customer;

	
	public Ticket(int price){
		this.price = price;
	}
	
	public int getPrice(){
		return price;
	}

	
}
