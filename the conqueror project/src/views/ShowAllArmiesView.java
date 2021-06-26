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
import listeners.ShowAllArmiesViewListener;
import units.Army;
import units.Unit;

public class ShowAllArmiesView extends TemplateView implements ActionListener
{
	ArrayList<Army> controlledArmies;
	Game theGame;
	private ShowAllArmiesViewListener listener;
	public ShowAllArmiesViewListener getListener() {
		return listener;
	}

	public void setListener(ShowAllArmiesViewListener listener) {
		this.listener = listener;
	}

	public ShowAllArmiesView(Game theGame) {
		super(theGame);
		this.theGame = theGame;
		JButton armyButton;
		JPanel midPanel = new JPanel();
		ArrayList<Army> controlledArmies = theGame.getPlayer().getControlledArmies();
		ArrayList<City> controlledCities = theGame.getPlayer().getControlledCities();
		for (int i = 0; i < controlledCities.size(); i++)
		{
			armyButton = new JButton("Defending Army of " + controlledCities.get(i).getName());
			armyButton.setAlignmentX(CENTER_ALIGNMENT);
			armyButton.setAlignmentY(CENTER_ALIGNMENT);
			
			
			armyButton.setPreferredSize(new Dimension(200,100));
			armyButton.setBackground(Color.LIGHT_GRAY);
			midPanel.add(armyButton);
			armyButton.addActionListener(this);
		}
		for (int i = 0; i < controlledArmies.size(); i++)
		{
			
			armyButton = new JButton("Army " + (i + 1));
			armyButton.setAlignmentX(CENTER_ALIGNMENT);
			armyButton.setAlignmentY(CENTER_ALIGNMENT);
			
			
			armyButton.setPreferredSize(new Dimension(150,100));
			armyButton.setBackground(Color.LIGHT_GRAY);
			midPanel.add(armyButton);
			armyButton.addActionListener(this);
		}
		this.add(midPanel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String[] buttonText = ((JButton) e.getSource()).getText().split(" ");
		
		ArrayList<Army> controlledArmies = theGame.getPlayer().getControlledArmies();
		if (buttonText[0].equals("Defending"))
		{
			System.out.println(buttonText[3]);
			if (buttonText[3].equals("Rome"))
			{
				listener.onDefendingArmySelected(theGame.findCity("Rome"));
			}
			else if (buttonText[3].equals("Sparta")) {
				listener.onDefendingArmySelected(theGame.findCity("Sparta"));
			}
			else
			{
				listener.onDefendingArmySelected(theGame.findCity("Cairo"));
			}
		}
		else 
		{
			int indexOfArmy = Integer.parseInt(((JButton) e.getSource()).getText().charAt(5) + "") - 1;
			listener.onArmySelected(controlledArmies.get(indexOfArmy));
		}
		
	}
		
		
		
	
	
}
