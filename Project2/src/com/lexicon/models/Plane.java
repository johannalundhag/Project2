package com.lexicon.models;

public abstract class Plane {
	
	private int id;
	private String name;
	private int firstClass;
	private int secondClass;
	// private int noOfSeats = firstClass + secondClass;

	public Plane(int id, String name, int firstClass, int secondClass) {
		this.id = id;
		this.name = name;
		this.firstClass = firstClass;
		this.secondClass = secondClass;
	}
	
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getFirstClass() {
		return firstClass;
	}
	
	
	public int getSecondClass() {
		return secondClass;
	}

}