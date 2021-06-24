package listeners;

import javax.swing.JButton;

import engine.*;
import units.*;

public interface WorldMapViewListener
{
	public void onOpenCity(JButton openedButton);
	public void onEndTurn();
	public void onTargetCity(JButton openedButton);
	public void onInitiate();
	public void onRelocateUnit(JButton openedButton);
}
