package buildings;
import exceptions.*;
public abstract class Building {
	
	//instance variables
	private int cost;	// read only
	private int level = 1;
	private int upgradeCost;
	private boolean coolDown = true;
	
	//constructor
	public Building(int cost ,int upgradeCost)
	{
		this.cost = cost;
		this.upgradeCost = upgradeCost;
	}

	//getters and setters
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getUpgradeCost() {
		return upgradeCost;
	}

	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = upgradeCost;
	}

	public boolean isCoolDown() {
		return coolDown;
	}

	public void setCoolDown(boolean coolDown) {
		this.coolDown = coolDown;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException
	{
		
		if (coolDown == true)
		{
			throw new BuildingInCoolDownException();
		}
		else
		{
		
			if (this instanceof Farm || this instanceof Market)
			{
				if (this.getLevel() == 2)
				{
					throw new MaxLevelException();
				}
				else
				{
					setLevel(2);
					if (this instanceof Farm)
						upgradeCost = 700;
					else
						upgradeCost = 1000;
				}
			}
			else if (this instanceof Barracks || this instanceof ArcheryRange || this instanceof Stable)
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
}
