package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.*;

public class Stable extends MilitaryBuilding
{

	// constructor
	public Stable()
	{
		super(2500, 1500, 600);
	}

	// methods
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException
	{
		// check for exceptions
		super.upgrade();
		setLevel(getLevel() + 1);
		this.setUpgradeCost(2000);
		if (getLevel() == 2)
			setRecruitmentCost(650);
		else
			setRecruitmentCost(700);
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
		return Cavalry.create(getLevel() + "");
	}
}
