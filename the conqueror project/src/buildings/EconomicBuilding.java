package buildings;

public abstract class EconomicBuilding extends Building{

	//constructor
	public EconomicBuilding(int cost, int upgradeCost)
	{
		super(cost, upgradeCost);
	}
	
	//methods
	public abstract int harvest();
	
}
