package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.Game;
import listeners.TargetACityViewListener;

public class TargetACityView extends TemplateComboBoxView implements ActionListener
{
	TargetACityViewListener listener;
	Boolean isChoosingUnit = false;

	public TargetACityView(Game theGame, String[] forComboBox)
	{
		super(theGame, forComboBox);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == comboBox && isChoosingUnit == false)
		{
			listener.onCityTargeted((String)comboBox.getSelectedItem());
		}
		else 
		{
			listener.onArmyTargeting(comboBox.getSelectedIndex());
		}
	}

	public void setIsChoosingUnit(Boolean isChoosingUnit)
	{
		this.isChoosingUnit = isChoosingUnit;
	}
	
	public void setListener(TargetACityViewListener listener)
	{
		this.listener = listener;
	}
	
}
