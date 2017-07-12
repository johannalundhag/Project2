package com.lexicon.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lexicon.models.FoodItem;
import com.lexicon.models.Trip;

public class BookingManager {
	
	private List<Trip> testList;
	
	public BookingManager() {
		testList = new ArrayList<>();
	}
	
	public void start() {
		System.out.println("Application entry point");
	}

}
