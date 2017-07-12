package com.lexicon.models;

public class FoodItem {
	
	private String foodName;
	private String foodDescription;
	private double price;
	private String menuAssociation;
	
	public FoodItem(String name, String description, double price) {
		this.foodName = name;
		this.foodDescription = description;
		this.price = price;
	}
	
	// Description-less constructor
	public FoodItem(String name, double price) {
		this(name, "", price);
	}
	
	public FoodItem(String name) {
		this(name, "", 0.0);
	}

	// Empty, default constructor.
	public FoodItem() {
		this("", "", 0.0);
	}
	
	
}
