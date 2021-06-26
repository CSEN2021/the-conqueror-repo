package listeners;

import javax.swing.JButton;

public interface WorldMapViewListener
{
	public void onOpenCity(JButton openedButton);
	public void onEndTurn();
	public void onTargetCity();
	public void onInitiate();
	public void onRelocateUnit();
	public void onShowAllArmies();
	public void onEnterBattle(String cityGettingAttacked);
}
