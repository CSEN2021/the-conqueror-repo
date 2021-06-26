package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import java.io.IOException;
import java.util.ArrayList;

import engine.*;
import units.*;


public class BattleView extends TemplateView implements ActionListener  {
	ArrayList<JButton> playerUnitsButtons = new ArrayList<>();
	ArrayList<JButton> enemyUnitsButtons = new ArrayList<>();
	public BattleView(Game theGame ,Army playerArmy,Army enemyArmy ) {
		super(theGame);
		String name = "ansfna"; //dummy name remove after test
		JPanel playerArmyPanel = new JPanel();
		JPanel enemyArmyPanel = new JPanel();
		JPanel battleInfoPanel = new JPanel(new BorderLayout());
		JPanel restPanel = new JPanel(null);
		JLabel playerLabel = new JLabel("player");
		JLabel enemyLabel = new JLabel("target city : " + name); // change to targetCityName instead of just name after testing
		

		setUpLabel(playerLabel);
		setUpLabel(enemyLabel);
		playerLabel.setPreferredSize(new Dimension(300,30));
		enemyLabel.setPreferredSize(new Dimension(300,30));
//		playerLabel.setFont(new Font("Serif", Font.BOLD, 20));

//		enemyLabel.setFont(new Font("Serif", Font.BOLD, 20));
//		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		enemyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		restPanel.setBackground(Color.gray);
		add(restPanel,BorderLayout.CENTER);
		playerArmyPanel.setBounds(0,0,300,650);
		enemyArmyPanel.setBounds(965,0,300,650);
		playerArmyPanel.setBackground(Color.gray);
		enemyArmyPanel.setBackground(Color.gray);
		battleInfoPanel.setBackground(Color.lightGray);
		battleInfoPanel.setBounds(301,0,664,650);
		
		playerArmyPanel.add(playerLabel);                                                //labels on both armies
		enemyArmyPanel.add(enemyLabel);
		//playerArmy.getUnits().get(i).toString().split(" ")[0] to get unit type as a string
		JButton unitButton;
		
		String buttonString;
		for(int i = 0;i<playerArmy.getUnits().size();i++) {
			  buttonString = playerArmy.getUnits().get(i).unitInfo()+" , current soldier count is " + playerArmy.getUnits().get(i).getCurrentSoldierCount();
			  unitButton = new JButton(buttonString);
			  unitButton.addActionListener(this);
			  playerUnitsButtons.add(unitButton);
			  setUpButton(unitButton);
			  playerArmyPanel.add(unitButton);
		}
		
		for(int i = 0;i<enemyArmy.getUnits().size();i++) {
			  buttonString = enemyArmy.getUnits().get(i).unitInfo()+" , current soldier count is " + enemyArmy.getUnits().get(i).getCurrentSoldierCount() ;
			  unitButton = new JButton(buttonString);
			  unitButton.addActionListener(this);
			  enemyUnitsButtons.add(unitButton);
			  setUpButton(unitButton);
			  enemyArmyPanel.add(unitButton);
		}
		
		restPanel.add(enemyArmyPanel,BorderLayout.WEST);
		restPanel.add(playerArmyPanel,BorderLayout.EAST);
		restPanel.add(battleInfoPanel,BorderLayout.CENTER);
		
		
		
		
		revalidate();
		
		
	}
	public static void main(String[] args) {
		Game theGame = null;
		Army theArmy = new Army("7amada");
		String theCity = null;
		theArmy.getUnits().add(Archer.create("1"));
		
		theArmy.getUnits().add(Archer.create("1"));
		theArmy.getUnits().add(Archer.create("1"));
		theArmy.getUnits().add(Archer.create("1"));
		theArmy.getUnits().add(Archer.create("1"));
		theArmy.getUnits().add(Archer.create("1"));
		theArmy.getUnits().add(Archer.create("1"));
		theArmy.getUnits().add(Archer.create("1"));
		theArmy.getUnits().add(Archer.create("1"));
		theArmy.getUnits().add(Archer.create("1"));
		theArmy.getUnits().add(Archer.create("1"));
		try {
			theGame = new Game("ahmed", "cairo");
		} catch (IOException e) {
			e.printStackTrace();
		}
		BattleView battleView = new BattleView(theGame,theArmy,theArmy );
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton)e.getSource();
		String buttonString = clickedButton.getText();
		System.out.println(playerUnitsButtons.indexOf(clickedButton));
		System.out.println(enemyUnitsButtons.indexOf(clickedButton));
		
	}

}
