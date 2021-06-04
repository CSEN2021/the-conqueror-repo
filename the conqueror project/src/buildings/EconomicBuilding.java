package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public abstract class EconomicBuilding extends Building{

	//constructor
	public EconomicBuilding(int cost,int upgradeCost)
	{
		super(cost, upgradeCost);
	}
	
	public abstract int harvest();
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException
	{
		if (this.isCoolDown() == true)
		{
			throw new BuildingInCoolDownException();
		}
		else
		{
			if (this.getLevel() == 2)
			{
				throw new MaxLevelException();
			}
			else
			{
				setLevel(2);
				if (this instanceof Farm)
					this.setUpgradeCost(700);
				else
					this.setUpgradeCost(1000);
			}
		}
	}
}
