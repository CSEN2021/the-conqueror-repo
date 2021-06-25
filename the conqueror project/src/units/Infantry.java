package units;

public class Infantry extends Unit
{

	// constructor
	public Infantry(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep)
	{
		super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
	}

	public Infantry(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep,
			Army parentArmy)
	{
		super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep, parentArmy);
	}

	// make an infantry given the level only
	public static Infantry create(String level)
	{
		switch (level)
		{
			case "1":
				return new Infantry(1, 50, 0.5, 0.6, 0.7);
			case "2":
				return new Infantry(2, 50, 0.5, 0.6, 0.7);
			default:
				return new Infantry(3, 60, 0.6, 0.7, 0.8);
		}
	}

	// make an archer given the level and parent army
	public static Infantry create(String level, Army parentArmy)
	{
		switch (level)
		{
			case "1":
				return new Infantry(1, 50, 0.5, 0.6, 0.7, parentArmy);
			case "2":
				return new Infantry(2, 50, 0.5, 0.6, 0.7, parentArmy);
			default:
				return new Infantry(3, 60, 0.6, 0.7, 0.8, parentArmy);
		}
	}

	// methods
	public String toString()
	{
		return "Infantry " + this.getLevel();
	}
	public double calcFactor(Unit target)
	{
		if ((getLevel() == 1 && target instanceof Infantry) || (getLevel() == 1 && target instanceof Cavalry))
		{
			return 0.1;
		}
		else if ((getLevel() == 2 && target instanceof Infantry)
				|| (getLevel() == 2 && target instanceof Cavalry))
		{
			return 0.2;
		}
		else if (getLevel() == 3 && target instanceof Cavalry)
		{
			return 0.25;
		}
		else if ((getLevel() == 1 && target instanceof Archer)
				|| (getLevel() == 3 && target instanceof Infantry))
		{
			return 0.3;
		}
		else if (getLevel() == 2 && target instanceof Archer)
		{
			return 0.4;
		}
		else
		{
			return 0.5;
		}
	}
}
