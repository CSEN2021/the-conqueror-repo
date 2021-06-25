package listeners;

public interface RelocateUnitListener
{
	public void onRelocateArmyFrom(int armyFrom);
	public void onRelocateUnitChosen(int unitToBeInitiated);
	public void onRelocateArmyTo(int armyTo);
}
