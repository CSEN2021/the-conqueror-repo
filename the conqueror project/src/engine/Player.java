package engine;

import java.util.*;
import units.*;
import exceptions.*;
import buildings.*;

public class Player
{

	// instance variables
	private String name; // read only
	private ArrayList<City> controlledCities; // read only
	private ArrayList<Army> controlledArmies; // read only
	private double treasury;
	private double food;

	// constructor
	public Player(String name)
	{
		this.name = name;
		controlledCities = new ArrayList<>();
		controlledArmies = new ArrayList<>();
	}

	// methods
	public void recruitUnit(String type, String cityName)
			throws BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException
	{
		// get the city
		City TheCity = null;
		for (int i = 0; i < controlledCities.size(); i++)
		{
			if (controlledCities.get(i).getName().equals(cityName))
			{
				TheCity = controlledCities.get(i);
			}
		}
		ArrayList<MilitaryBuilding> militaryBuildings = TheCity.getMilitaryBuildings();
		// get the military building
		MilitaryBuilding TheBuilding = null;
		switch (type)
		{
			case ("Archer"):
				for (int i = 0; i < militaryBuildings.size(); i++)
				{
					if (militaryBuildings.get(i) instanceof ArcheryRange)
					{
						TheBuilding = militaryBuildings.get(i);
					}
				}
				break;
			case ("Cavalry"):
				for (int i = 0; i < militaryBuildings.size(); i++)
				{
					if (militaryBuildings.get(i) instanceof Stable)
					{
						TheBuilding = militaryBuildings.get(i);
					}
				}
				break;
			case ("Infantry"):
				for (int i = 0; i < militaryBuildings.size(); i++)
				{
					if (militaryBuildings.get(i) instanceof Barracks)
					{
						TheBuilding = militaryBuildings.get(i);
					}
				}
				break;
		}
		// recruit the unit
		if (TheBuilding.getRecruitmentCost() > treasury)
			throw new NotEnoughGoldException();
		treasury -= TheBuilding.getRecruitmentCost();
		Unit TheUnit = TheBuilding.recruit();
		TheUnit.setParentArmy(TheCity.getDefendingArmy());

		// shouldn't we check that we are not adding to a full army ?
		TheCity.getDefendingArmy().getUnits().add(TheUnit);
	}

	public void build(String type, String cityName) throws NotEnoughGoldException
	{
		// get the city
		City TheCity = null;
		for (int i = 0; i < controlledCities.size(); i++)
		{
			if (controlledCities.get(i).getName().equals(cityName))
			{
				TheCity = controlledCities.get(i);
			}
		}
		Building TheBuilding = null;
		int cost = 0;
		switch (type)
		{
			case "Farm":
				cost = new Farm().getCost();
				TheBuilding = new Farm();
				break;
			case "Market":
				cost = new Market().getCost();
				TheBuilding = new Market();
				break;
			case "Barracks":
				cost = new Barracks().getCost();
				TheBuilding = new Barracks();
				break;
			case "ArcheryRange":
				cost = new ArcheryRange().getCost();
				TheBuilding = new ArcheryRange();
				break;
			default:
				cost = new Stable().getCost();
				TheBuilding = new Stable();
				break;
		}
		if (cost > treasury)
			throw new NotEnoughGoldException();
		if (type.equals("Farm") || type.equals("Market"))
			TheCity.getEconomicalBuildings().add((EconomicBuilding) TheBuilding);
		else
			TheCity.getMilitaryBuildings().add((MilitaryBuilding) TheBuilding);
		treasury -= cost;
		// Make sure to update the coolDown value after performing the action. As per
		// the game rules, player can only have one building from each type. ????????

	}

	public void upgradeBuilding(Building b)
			throws NotEnoughGoldException, BuildingInCoolDownException, MaxLevelException
	{
		if (b.getUpgradeCost() > treasury)
			throw new NotEnoughGoldException();
		b.upgrade();
		treasury -= b.getUpgradeCost();
	}

	public void initiateArmy(City city, Unit unit)
	{
		Army TheArmy = new Army(city.getName());
		Army oldArmy = unit.getParentArmy();
		unit.setParentArmy(TheArmy);
		TheArmy.getUnits().add(unit);
		oldArmy.getUnits().remove(unit);
		controlledArmies.add(TheArmy);
	}

	public void laySiege(Army army, City city) throws TargetNotReachedException, FriendlyCityException
	{
		if(!army.getCurrentLocation().equals(city.getName()))
			throw new TargetNotReachedException();
		if(controlledCities.contains(city))
			throw new FriendlyCityException();
		army.setCurrentStatus(Status.BESIEGING);
		city.setUnderSiege(true);
		// update turnsUnderSiege at the end of turn ?
	}

	// setters and getters
	public double getTreasury()
	{
		return treasury;
	}

	public void setTreasury(double treasury)
	{
		this.treasury = treasury;
	}

	public double getFood()
	{
		return food;
	}

	public void setFood(double food)
	{
		this.food = food;
	}

	public String getName()
	{
		return name;
	}

	public ArrayList<City> getControlledCities()
	{
		return controlledCities;
	}

	public ArrayList<Army> getControlledArmies()
	{
		return controlledArmies;
	}

}
