package com.lexicon.models;

public class FoodItem {
	
	private String foodName;
	private String foodDescription;
	private double price;
	private String menuAssociation;
	
	// This is the default constructor everything is being sent to.
	// All constructors initialises foodItem to GENERIC
	public FoodItem(String name, String description, double price) {
		this.foodName = name;
		this.foodDescription = description;
		this.price = price;
		this.menuAssociation = "GENERIC";
	}
	
	// Single name and price constructor
	public FoodItem(String name, double price) {
		this(name, "", price);
	}
	
	// Single name constructor
	public FoodItem(String name) {
		this(name, "", 0.0);
	}

	// EMPTY constructor.
	public FoodItem() {
		this("", "", 0.0);
	}
	
	
	
	
	public void setName(String newName) {
		this.foodName = newName;
	}
	public String getName() {
		return this.foodName;
	}

	@Override
	public String toString() {
		return "FoodItem [foodName=" + foodName + ", foodDescription=" + foodDescription + ", price=" + price
				+ ", menuAssociation=" + menuAssociation + "]";
	}
	
	
	
	
}
