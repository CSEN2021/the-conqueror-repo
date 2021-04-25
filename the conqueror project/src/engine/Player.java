package engine;

import java.util.*;
import buildings.*;
import units.*;

public class Player {
	
	//instance variables
	private String name;	//read only
	private ArrayList<City> controlledCities;	//read only
	//private ArrayList<Army> controlledArmies;	//read only
	private double treasury;
	private double food;
	
	//constructor
	public Player(String name) {
		this.name = name;
	}
	
	//setters and getters
}
