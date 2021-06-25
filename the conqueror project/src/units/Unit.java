package units;

import exceptions.FriendlyFireException;

public abstract class Unit
{

	// instance variables
	private int level; // READ ONLY
	private int maxSoldierCount; // READ ONLY
	private int currentSoldierCount;
	private double idleUpkeep; // READ ONLY
	private double marchingUpkeep; // READ ONLY
	private double siegeUpkeep; // READ ONLY
	private Army parentArmy;

	// constructor with and without parent army
	public Unit(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep)
	{
		this.level = level;
		this.maxSoldierCount = maxSoldierCount;
		this.idleUpkeep = idleUpkeep;
		this.marchingUpkeep = marchingUpkeep;
		this.siegeUpkeep = siegeUpkeep;
		this.currentSoldierCount = maxSoldierCount;
	}

	public Unit(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep,
			Army parentArmy)
	{
		this.level = level;
		this.maxSoldierCount = maxSoldierCount;
		this.idleUpkeep = idleUpkeep;
		this.marchingUpkeep = marchingUpkeep;
		this.siegeUpkeep = siegeUpkeep;
		this.currentSoldierCount = maxSoldierCount;
		this.parentArmy = parentArmy;
	}

	// methods
	public void attack(Unit target) throws FriendlyFireException
	{
		// checks if the target is one of the units in the player's army(still
		// incomplete i think), Mok says we can just check parent army of target and
		// attacker ?
		if (this.parentArmy.getUnits().contains(target))
		{
			throw new FriendlyFireException();
		}

		// calculates factor using dynamic polymorphism
		double factor = calcFactor(target);

		// decrease target soldiers by the following equation
		double newcount = target.getCurrentSoldierCount() - this.currentSoldierCount * factor;
		if(newcount<0) {
			newcount=0;
		}
		target.setCurrentSoldierCount((int)newcount);
		target.getParentArmy().handleAttackedUnit(target);
	}

	abstract public double calcFactor(Unit target);

	public String toString()
	{
		return "";
	}
	
	public String unitInfo()
	{
		return "";
	}
	
	// getters and setters
	public int getCurrentSoldierCount()
	{
		return currentSoldierCount;
	}

	public void setCurrentSoldierCount(int currentSoldierCount)
	{
		this.currentSoldierCount = currentSoldierCount;
	}

	public int getLevel()
	{
		return level;
	}

	public int getMaxSoldierCount()
	{
		return maxSoldierCount;
	}

	public double getIdleUpkeep()
	{
		return idleUpkeep;
	}

	public double getMarchingUpkeep()
	{
		return marchingUpkeep;
	}

	public double getSiegeUpkeep()
	{
		return siegeUpkeep;
	}

	public Army getParentArmy()
	{
		return parentArmy;
	}

	public void setParentArmy(Army parentArmy)
	{
		this.parentArmy = parentArmy;
	}

}
