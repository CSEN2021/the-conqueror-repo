package engine;

import java.util.*;
import buildings.*;
import units.*;

public class City
{

	// instance variables
	private String name; // read only
	private ArrayList<EconomicBuilding> economicalBuildings; // read only
	private ArrayList<MilitaryBuilding> militaryBuildings; // read only
	private Army defendingArmy;
	private int turnsUnderSiege = -1;
	private boolean underSiege = false;

	// constructor
	public City(String name)
	{
		this.name = name;
		economicalBuildings = new ArrayList<>();
		militaryBuildings = new ArrayList<>();
		defendingArmy = new Army(name);
	}

	// methods
	public Building findBuilding(String theBuilding)
	{

		for (int i = 0; i < economicalBuildings.size(); i++)
		{
			if (theBuilding.equalsIgnoreCase("farm") && economicalBuildings.get(i) instanceof Farm)
			{
				return (Farm) economicalBuildings.get(i);
			}
			else if (theBuilding.equalsIgnoreCase("market") && economicalBuildings.get(i) instanceof Market)
			{
				return (Market) economicalBuildings.get(i);
			}
		}
		for (int i = 0; i < militaryBuildings.size(); i++)
		{
			if (theBuilding.equalsIgnoreCase("barracks") && militaryBuildings.get(i) instanceof Barracks)
			{
				return (Barracks) militaryBuildings.get(i);
			}
			else if (theBuilding.equalsIgnoreCase("archeryrange") && militaryBuildings.get(i) instanceof ArcheryRange)
			{
				return (ArcheryRange) militaryBuildings.get(i);
			}
			else if (theBuilding.equalsIgnoreCase("stable") && militaryBuildings.get(i) instanceof Stable)
			{
				return (Stable) militaryBuildings.get(i);
			}
		}
		return null;
	}

	// getters and setters
	public Army getDefendingArmy()
	{
		return defendingArmy;
	}

	public void setDefendingArmy(Army defendingArmy)
	{
		this.defendingArmy = defendingArmy;
	}

	public int getTurnsUnderSiege()
	{
		return turnsUnderSiege;
	}

	public void setTurnsUnderSiege(int turnsUnderSiege)
	{
		this.turnsUnderSiege = turnsUnderSiege;
	}

	public boolean isUnderSiege()
	{
		return underSiege;
	}

	public void setUnderSiege(boolean underSiege)
	{
		this.underSiege = underSiege;
	}

	public String getName()
	{
		return name;
	}

	public ArrayList<EconomicBuilding> getEconomicalBuildings()
	{
		return economicalBuildings;
	}

	public ArrayList<MilitaryBuilding> getMilitaryBuildings()
	{
		return militaryBuildings;
	}

}
