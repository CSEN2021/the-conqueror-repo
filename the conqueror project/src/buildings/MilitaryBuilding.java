package buildings;

public class MilitaryBuilding extends Building{

	private int recruitmentCost;
	private int currentRecruit;
	final private int maxRecruit = 3;
	
	public MilitaryBuilding(int cost, int upgradeCost,int recruitmentCost)
	{
		super(cost, upgradeCost);
		this.recruitmentCost = recruitmentCost;
	}
	
}
