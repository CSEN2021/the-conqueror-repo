package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import engine.City;
import engine.Game;
import units.Army;
import units.Unit;

public class ShowArmiesView extends TemplateView implements ActionListener
{
	
	public ShowArmiesView(Game thegame) {
		super(thegame);
		JTextArea armyJTextArea;
		JPanel midPanel = new JPanel();
		
		ArrayList<Army> controlledArmies = thegame.getPlayer().getControlledArmies();
		for (int i = 0; i < controlledArmies.size(); i++)
		{
			Army army = controlledArmies.get(i);
			armyJTextArea = new JTextArea("Army " + (i + 1) + '\n' + army.toString());
			armyJTextArea.setAlignmentX(CENTER_ALIGNMENT);
			armyJTextArea.setAlignmentY(CENTER_ALIGNMENT);
			
			
			armyJTextArea.setPreferredSize(new Dimension(150,100));
			armyJTextArea.setBackground(Color.LIGHT_GRAY);
			midPanel.add(armyJTextArea);
		}
		this.add(midPanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
