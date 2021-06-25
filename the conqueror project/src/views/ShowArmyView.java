package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import engine.City;
import engine.Game;
import views.*;
import units.*;

public class ShowArmyView extends TemplateView implements ActionListener
{
	
	public ShowArmyView(Game thegame, City theCity)
	{
		super(thegame);
		JTextArea unitTextArea;
		JPanel midPanel = new JPanel();
		
		ArrayList<Unit> defendingArmy= theCity.getDefendingArmy().getUnits();
		for (int i = 0; i < defendingArmy.size(); i++)
		{
			Unit unit = defendingArmy.get(i);
			unitTextArea = new JTextArea(" " + unit.unitInfo());
			unitTextArea.setAlignmentX(CENTER_ALIGNMENT);
			unitTextArea.setAlignmentY(CENTER_ALIGNMENT);
			unitTextArea.append("\n Current Soldier Count : " + unit.getCurrentSoldierCount());
			unitTextArea.append("\n Max Soldier Count : " + unit.getMaxSoldierCount());
			
			unitTextArea.setPreferredSize(new Dimension(150,100));
			unitTextArea.setBackground(Color.LIGHT_GRAY);
			midPanel.add(unitTextArea);
		}
		this.add(midPanel, BorderLayout.CENTER);
	}
	
	public ShowArmyView(Game theGame, int indexOfArmy)
	{
		super(theGame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
