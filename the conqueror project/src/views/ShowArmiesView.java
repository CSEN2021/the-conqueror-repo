package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
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
		JButton armyButton;
		JPanel midPanel = new JPanel();
		
		ArrayList<Army> controlledArmies = thegame.getPlayer().getControlledArmies();
		for (int i = 0; i < controlledArmies.size(); i++)
		{
			Army army = controlledArmies.get(i);
			armyButton = new JButton("Army " + (i + 1) + '\n' + army.toString());
			armyButton.setAlignmentX(CENTER_ALIGNMENT);
			armyButton.setAlignmentY(CENTER_ALIGNMENT);
			
			
			armyButton.setPreferredSize(new Dimension(150,100));
			armyButton.setBackground(Color.LIGHT_GRAY);
			midPanel.add(armyButton);
		}
		this.add(midPanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int indexOfArmy = ((JButton) e.getSource()).getText().charAt(6) - 1;
		
	}
	
}
