package engine;

import java.util.*;
import units.*;

public class Player {
	
	//instance variables
	private String name;	//read only
	private ArrayList<City> controlledCities;	//read only
	private ArrayList<Army> controlledArmies;	//read only
	private double treasury;
	private double food;
	
	//constructor
	public Player(String name) {
		this.name = name;
		controlledCities = new ArrayList<>();
		controlledArmies = new ArrayList<>();
	}

	//setters and getters
	public double getTreasury() {
		return treasury;
	}

	public void setTreasury(double treasury) {
		this.treasury = treasury;
	}

	public double getFood() {
		return food;
	}

	public void setFood(double food) {
		this.food = food;
	}

	public String getName() {
		return name;
	}

	public ArrayList<City> getControlledCities() {
		return controlledCities;
	}

	public ArrayList<Army> getControlledArmies() {
		return controlledArmies;
	}
	
}
