package buildings;
import units.*;
import exceptions.*;
public class Barracks extends MilitaryBuilding {

	//constructor
	public Barracks()
	{
		super(2000, 1000, 500);
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
				return Infantry.create(this.getLevel() + "");
			}
		}
		
	}
	
}
