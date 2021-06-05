package engine;

import java.util.*;
import units.*;
import exceptions.*;
import buildings.*;

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
	
	// methods
	public void recruitUnit(String type,String cityName) throws
	BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException {
		// get the city
		City TheCity = null;
		for(int i = 0; i < controlledCities.size(); i++)
		{
			if(controlledCities.get(i).getName().equals(cityName))
			{
				TheCity = controlledCities.get(i);
			}
		}
		ArrayList<MilitaryBuilding> militaryBuildings = TheCity.getMilitaryBuildings();
		// get the military building
		MilitaryBuilding thebuilding;
		switch(type) {
		case ("Archer"):
			for(int i =0; i < militaryBuildings.size(); i++)
			{
				if(militaryBuildings.get(i) instanceof ArcheryRange)
				{
					thebuilding = militaryBuildings.get(i);
				}
			}
			break;
		case ("Cavalry"):
			for(int i =0; i < militaryBuildings.size(); i++)
			{
				if(militaryBuildings.get(i) instanceof Stable)
				{
					thebuilding = militaryBuildings.get(i);
				}
			}
			break;
		case ("Infantry"):
			for(int i =0; i < militaryBuildings.size(); i++)
			{
				if(militaryBuildings.get(i) instanceof Barracks)
				{
					thebuilding = militaryBuildings.get(i);
				}
			}
			break;
		}

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
