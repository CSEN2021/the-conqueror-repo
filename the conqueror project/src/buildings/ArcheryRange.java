package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxRecruitedException;
import units.*;

public class ArcheryRange extends MilitaryBuilding{

	//constructor
	public ArcheryRange()
	{
		super(1500, 800, 400);
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
				return Archer.create(this.getLevel() + "");
			}
		}
		
	}
}

