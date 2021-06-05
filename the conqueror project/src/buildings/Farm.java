package buildings;

import exceptions.*;

public class Farm extends EconomicBuilding
{

	// constructor
	public Farm()
	{
		super(1000, 500);
	}

	// methods
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException
	{
		// check for exceptions
		super.upgrade();
		setLevel(getLevel() + 1);
		this.setUpgradeCost(700);
	}

	public int harvest()
	{
		switch (getLevel())
		{
			case 1:
				return 500;
			case 2:
				return 700;
			default:
				return 1000;
		}
	}
}
