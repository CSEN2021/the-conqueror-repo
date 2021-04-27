package engine;

import java.util.*;
import buildings.*;
import units.*;

public class City {
	
	//instance variables
	private String name;	//read only
	private ArrayList<EconomicBuilding> economicalBuildings;	//read only
	private ArrayList<MilitaryBuilding> militaryBuildings;	//read only
	private Army defendingArmy;
	private int turnsUnderSiege = 0;
	private boolean underSiege = false;
	
	//constructor
	public City(String name) {
		this.name = name;
		economicalBuildings = new ArrayList<>();
		militaryBuildings = new ArrayList<>();
		defendingArmy = new Army(name);
	}


	//getters and setters
	
	public Army getDefendingArmy() {
		return defendingArmy;
	}

	public void setDefendingArmy(Army defendingArmy) {
		this.defendingArmy = defendingArmy;
	}

	public int getTurnsUnderSiege() {
		return turnsUnderSiege;
	}

	public void setTurnsUnderSiege(int turnsUnderSiege) {
		this.turnsUnderSiege = turnsUnderSiege;
	}

	public boolean isUnderSiege() {
		return underSiege;
	}

	public void setUnderSiege(boolean underSiege) {
		this.underSiege = underSiege;
	}

	public String getName() {
		return name;
	}

	public ArrayList<EconomicBuilding> getEconomicalBuildings() {
		return economicalBuildings;
	}

	public ArrayList<MilitaryBuilding> getMilitaryBuildings() {
		return militaryBuildings;
	}
	
}
