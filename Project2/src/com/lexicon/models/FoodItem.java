package com.lexicon.models;

public class FoodItem {

	private String foodName;
	private String foodDescription;
	private int price;
	private FlightClass menuAssociation;

	// This is the default constructor everything is being sent to.
	// All constructors initialises foodItem to GENERIC
	public FoodItem(String name, String description, int price, FlightClass menuAssociation) {
		this.foodName = name;
		this.foodDescription = description;
		this.price = price;
		this.menuAssociation = menuAssociation;
	}

	// Single name and price constructor
	public FoodItem(String name, int price) {
		this(name, "", price, FlightClass.NONE);
	}

	// Single name constructor
	public FoodItem(String name) {
		this(name, "", 0, FlightClass.NONE);
	}

	// EMPTY constructor.
	public FoodItem() {
		this("", "", 0, FlightClass.NONE);
	}

	public void setAssociation(int association) {
		switch (association) {
		case 1:
			this.menuAssociation = FlightClass.firstClass;
			break;
		case 2:
			this.menuAssociation = FlightClass.secondClass;
			break;
		case 3:
			this.menuAssociation = FlightClass.NONE;
			break;
		default:
			System.out.println("The only valid options are 1-3");
			break;
		}
	}
	
	public FlightClass getAssociation(){
		return menuAssociation;
	}

	public void setName(String newName) {
		this.foodName = newName;
	}

	public String getName() {
		return this.foodName;
	}

	public void setDescription(String newDescription) {
		this.foodDescription = newDescription;
	}

	public String getDescription() {
		return this.foodDescription;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "FoodItem [foodName=" + foodName + ", foodDescription=" + foodDescription + ", price=" + price+ "]";
				//+ ", menuAssociation=" + menuAssociation + "]";
	}

}
