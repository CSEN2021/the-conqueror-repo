package buildings;
import units.*;
import exceptions.*;
public abstract class MilitaryBuilding extends Building{
	
	//instance variables
	private int recruitmentCost;
	private int currentRecruit;
	final private int maxRecruit = 3;	//read only
	
	//constructor
	public MilitaryBuilding(int cost, int upgradeCost,int recruitmentCost)
	{
		super(cost, upgradeCost);
		this.recruitmentCost = recruitmentCost;
	}

	//getters and setters
	public int getRecruitmentCost() {
		return recruitmentCost;
	}

	public void setRecruitmentCost(int recruitmentCost) {
		this.recruitmentCost = recruitmentCost;
	}

	public int getCurrentRecruit() {
		return currentRecruit;
	}

	public void setCurrentRecruit(int currentRecruit) {
		this.currentRecruit = currentRecruit;
	}

	public int getMaxRecruit() {
		return maxRecruit;
	}
	
	public abstract Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException;
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException
	{
	
		if (this.isCoolDown() == true)
		{
			throw new BuildingInCoolDownException();
		}
		else
		{
			int level = this.getLevel();
			if (level == 3)
			{
				throw new MaxLevelException();
			}
			else
			{
				
				this.setLevel(level + 1);
				if (this instanceof ArcheryRange)
				{
					if (level == 2)
					{
						this.setUpgradeCost(700);
					}
				}
				else if (this instanceof ArcheryRange)
				{
					this.setUpgradeCost(700);
				}
				else if (this instanceof Barracks)
				{
					this.setUpgradeCost(1500);
				}
				else
				{
					this.setUpgradeCost(2000);
				}
					
			}
				
		}
	}
	
	
	
	
}
