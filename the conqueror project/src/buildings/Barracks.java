package buildings;

import units.*;
import exceptions.*;

public class Barracks extends MilitaryBuilding
{

	// constructor
	public Barracks()
	{
		super(2000, 1000, 500);
	}

	// methods
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException
	{
		// check for exceptions
		super.upgrade();
		setLevel(getLevel() + 1);
		this.setUpgradeCost(1500);
		if (getLevel() == 2)
			setRecruitmentCost(550);
		else
			setRecruitmentCost(600);
	}

	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException
	{
		if (isCoolDown() == true)
		{
			throw new BuildingInCoolDownException();
		}
		if (getCurrentRecruit() == getMaxRecruit())
		{
			throw new MaxRecruitedException();
		}
		setCurrentRecruit(getCurrentRecruit() + 1);
		return Infantry.create(getLevel() + "");
	}

}
