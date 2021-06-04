package buildings;
import exceptions.*;
public class Farm extends EconomicBuilding{

	//constructor
	public Farm()
	{
		super(1000, 500);
	}
	
	
	
	
	
	
	
	
	
	
	
	public int harvest()
	{
		int level = getLevel();
		if (level == 1)
		{
			return 500;
		}
		else if (level == 2)
		{
			return 700;
		}
		else
		{
			return 1000;
		}
	}
	
	
	
}
