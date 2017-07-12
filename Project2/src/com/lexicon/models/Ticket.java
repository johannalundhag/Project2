package com.lexicon.models;

public abstract class Ticket {
	
	protected int price;
	protected Customer customer;

	public Ticket(Customer customer){
		this.customer = customer;
	}
	
	public int getPrice(){
		return price;
	}
	
	public Customer getCustomer(){
		return customer;
	}
	
}
