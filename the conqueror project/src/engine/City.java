package engine;

import java.util.*;
import buildings.*;
import units.*;

public class City {
	
	//instance variables
	private String name;	//read only
	private ArrayList<EconomicBuilding> economicalBuildings;	//read only
	private ArrayList<MilitaryBuilding> militaryBuildings;	//read only
	//private Army defendingArmy;
	private int turnsUnderSiege;
	private boolean underSiege = false;
	
	//constructor
	public City(String name) {
		this.name = name;
		//how should I initialize the other variables ?
	}
	
	//getters and setters
}
