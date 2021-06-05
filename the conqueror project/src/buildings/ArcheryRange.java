package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.*;

public class ArcheryRange extends MilitaryBuilding
{

	// constructor

	public ArcheryRange()
	{
		super(1500, 800, 400);
	}

	// methods

	public void upgrade() throws BuildingInCoolDownException, MaxLevelException
	{
		// check for exceptions
		super.upgrade();
		setLevel(getLevel() + 1);
		this.setUpgradeCost(700);
		if (getLevel() == 2)
			setRecruitmentCost(450);
		else
			setRecruitmentCost(500);
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
		return Archer.create(getLevel() + "");
	}
}
