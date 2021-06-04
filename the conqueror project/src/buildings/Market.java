package buildings;

public class Market extends EconomicBuilding{

	//constructor
	public Market()
	{
		super(1500, 700);
	}
	
	public int harvest()
	{
		int level = getLevel();
		if (level == 1)
		{
			return 1000;
		}
		else if (level == 2)
		{
			return 1500;
		}
		else
		{
			return 2000;
		}
	}
	
}
