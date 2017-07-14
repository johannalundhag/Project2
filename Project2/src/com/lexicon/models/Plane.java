package com.lexicon.models;

public class Plane {

	private int id;
	private String name;
	private int business;
	private int economy;
	// private int noOfSeats = firstClass + secondClass;

	public Plane(int id, String name, int business, int economy) {
		this.id = id;
		this.name = name;
		this.business = business;
		this.economy = economy;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getBusiness() {
		return business;
	}

	public int getEconomy() {
		return economy;
	}

}