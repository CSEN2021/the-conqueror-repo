package units;

public class Archer extends Unit
{

	// constructor
	public Archer(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep)
	{
		super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep);
	}

	public Archer(int level, int maxSoldierCount, double idleUpkeep, double marchingUpkeep, double siegeUpkeep,
			Army parentArmy)
	{
		super(level, maxSoldierCount, idleUpkeep, marchingUpkeep, siegeUpkeep, parentArmy);
	}

	// make an archer given the level only

	public static Archer create(String level)
	{
		switch (level)
		{
			case "1":
				return new Archer(1, 60, 0.4, 0.5, 0.6);
			case "2":
				return new Archer(2, 60, 0.4, 0.5, 0.6);
			default:
				return new Archer(3, 70, 0.5, 0.6, 0.7);
		}
	}

	// make an archer given the level and parent army
	public static Archer create(String level, Army parentArmy)
	{
		switch (level)
		{
			case "1":
				return new Archer(1, 60, 0.4, 0.5, 0.6, parentArmy);
			case "2":
				return new Archer(2, 60, 0.4, 0.5, 0.6, parentArmy);
			default:
				return new Archer(3, 70, 0.5, 0.6, 0.7, parentArmy);
		}
	}

	// methods
	public double calcFactor(Unit target)
	{
		if (target instanceof Cavalry && getLevel() < 3)
		{
			return 0.1;
		}
		else if ((getLevel() == 1 && target instanceof Infantry)
				|| (getLevel() == 3 && target instanceof Cavalry))
		{
			return 0.2;
		}
		else if ((getLevel() == 1 && target instanceof Archer)
				|| (getLevel() == 2 && target instanceof Infantry))
		{
			return 0.3;
		}
		else if ((getLevel() == 2 && target instanceof Archer)
				|| (getLevel() == 3 && target instanceof Infantry))
		{
			return 0.4;
		}
		else
		{
			return 0.5;
		}
	}
}
