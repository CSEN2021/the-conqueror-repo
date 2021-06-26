package listeners;

import engine.City;
import units.Army;

public interface ShowAllArmiesViewListener {

	public void onArmySelected(Army army);
	public void onDefendingArmySelected(City city);
	
}
