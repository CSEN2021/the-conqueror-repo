package buildings;

import exceptions.*;

public abstract class Building
{

	// instance variables
	private int cost; // read only
	private int level = 1;
	private int upgradeCost;
	private boolean coolDown = true;

	// constructor
	public Building(int cost, int upgradeCost)
	{
		this.cost = cost;
		this.upgradeCost = upgradeCost;
	}

	// methods
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException
	{
		// check for exceptions
		if (this.isCoolDown() == true)
		{
			throw new BuildingInCoolDownException();
		}
		if (this.getLevel() == 3)
		{
			throw new MaxLevelException();
		}
		coolDown = true;
	}

	// getters and setters
	public int getLevel()
	{
		return level;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	public int getUpgradeCost()
	{
		return upgradeCost;
	}

	public void setUpgradeCost(int upgradeCost)
	{
		this.upgradeCost = upgradeCost;
	}

	public boolean isCoolDown()
	{
		return coolDown;
	}

	public void setCoolDown(boolean coolDown)
	{
		this.coolDown = coolDown;
	}

	public int getCost()
	{
		return cost;
	}

}
