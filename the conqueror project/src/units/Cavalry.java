package units;

public class Cavalry extends Unit
{

	// constructor
	public Cavalry(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep)
	{
		super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
	}

	public Cavalry(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep,
			Army parentArmy)
	{
		super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep, parentArmy);
	}

	// make a cavalry given the level only
	public static Cavalry create(String level)
	{
		switch (level)
		{
			case "1":
				return new Cavalry(1, 40, 0.6, 0.7, 0.75);
			case "2":
				return new Cavalry(2, 40, 0.6, 0.7, 0.75);
			default:
				return new Cavalry(3, 60, 0.7, 0.8, 0.9);
		}
	}

	// make an archer given the level and parent army
	public static Cavalry create(String level, Army parentArmy)
	{
		switch (level)
		{
			case "1":
				return new Cavalry(1, 40, 0.6, 0.7, 0.75, parentArmy);
			case "2":
				return new Cavalry(2, 40, 0.6, 0.7, 0.75, parentArmy);
			default:
				return new Cavalry(3, 60, 0.7, 0.8, 0.9, parentArmy);
		}
	}

	// methods
	public String toString()
	{
		return "Cavalry " + this.getLevel();
	}
	public double calcFactor(Unit target)
	{
		if (target instanceof Cavalry && getLevel() < 3)
		{
			return 0.2;
		}
		else if ((getLevel() == 1 && target instanceof Infantry)
				|| (getLevel() == 3 && target instanceof Cavalry))
		{
			return 0.3;
		}
		else if (getLevel() == 2 && target instanceof Infantry)
		{
			return 0.4;
		}
		else if ((getLevel() == 1 && target instanceof Archer)
				|| (getLevel() == 3 && target instanceof Infantry))
		{
			return 0.5;
		}
		else if (getLevel() == 2 && target instanceof Archer)
		{
			return 0.6;
		}
		else
		{
			return 0.7;
		}
	}
}
