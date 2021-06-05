package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Market extends EconomicBuilding
{

	// constructor
	public Market()
	{
		super(1500, 700);
	}

	// methods

	public void upgradeHlp() throws BuildingInCoolDownException, MaxLevelException
	{
		setLevel(getLevel() + 1);
		this.setUpgradeCost(1000);
	}

	public int harvest()
	{
		switch (getLevel())
		{
			case 1:
				return 1000;
			case 2:
				return 1500;
			default:
				return 2000;
		}
	}

}
