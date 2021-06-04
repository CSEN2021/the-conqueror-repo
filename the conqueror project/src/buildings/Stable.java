package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxRecruitedException;
import units.Infantry;
import units.Unit;
import units.*;
public class Stable extends MilitaryBuilding {

	//constructor
	public Stable() 
	{
		super(2500, 1500, 600);
	}

	public Unit recruit() throws BuildingInCoolDownException,MaxRecruitedException
	{
		if (this.isCoolDown() == true) {
			throw new BuildingInCoolDownException();
		}
		else 
		{
			if (this.getCurrentRecruit() == this.getMaxRecruit())
			{
				throw new MaxRecruitedException();
			}
			else
			{
				this.setCurrentRecruit(getCurrentRecruit() + 1);
				return Cavalry.create(this.getLevel() + "");
			}
		}
		
	}
	
}
