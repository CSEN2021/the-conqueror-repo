package buildings;

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
	
}
