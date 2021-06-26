package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import java.io.IOException;
import java.util.ArrayList;

import engine.*;
import listeners.BattleViewListener;
import units.*;


public class BattleView extends TemplateView implements ActionListener  {
	ArrayList<JToggleButton> playerUnitsButtons = new ArrayList<>();
	ArrayList<JToggleButton> enemyUnitsButtons = new ArrayList<>();
	ButtonGroup playerUnitsButtonGroup = new ButtonGroup();
	ButtonGroup enemyUnitsButtonGroup = new ButtonGroup();
	
	
	JPanel playerArmyPanel = new JPanel(new GridLayout(0,1));
	JPanel enemyArmyPanel = new JPanel(new GridLayout(0,2));
	JPanel middlePanel = new JPanel(null);
	JPanel restPanel = new JPanel(null);
	JLabel playerLabel = new JLabel("player");
	JLabel enemyLabel;
	JLabel chooseAttackingUnitMsg = new JLabel("choose attacking unit");
	JLabel chooseTargetUnitMsg = new JLabel("choose unit to target");
	JPanel battleLogPanel = new JPanel();
	JPanel battleOptionsPanel = new JPanel(new GridLayout(0,3));
	JButton attackButton = new JButton("manual attack");
	JButton autoResolveButton = new JButton("auto resolve the battle");
	JButton retreatButton = new JButton("retreat");
	
	BattleViewListener listener;
	
	public void setListener(BattleViewListener listener)
	{
		this.listener = listener;
	}
	
	public BattleView(Game theGame ,Army playerArmy,City targetCity) {
		super(theGame);
		String name = targetCity.getName(); 
		enemyLabel = new JLabel("target city : " + name);
		Army enemyArmy = targetCity.getDefendingArmy();
		
		setUpLabel(playerLabel);
		setUpLabel(enemyLabel);
		setUpLabel(chooseAttackingUnitMsg);
		setUpLabel(chooseTargetUnitMsg);
		playerLabel.setPreferredSize(new Dimension(300,30));
		enemyLabel.setPreferredSize(new Dimension(300,30));
		chooseAttackingUnitMsg.setPreferredSize(new Dimension(300,30));
		chooseTargetUnitMsg.setPreferredSize(new Dimension(300,30));
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
		middlePanel.setBackground(Color.lightGray);
		middlePanel.setBounds(301,0,664,650);
		battleLogPanel.setBounds(0,0,664,600);
		battleOptionsPanel.setBounds(0,601,664,40);
		
		battleLogPanel.setBackground(Color.lightGray);
		battleOptionsPanel.setBackground(Color.gray);
		battleOptionsPanel.add(playerLabel);
		
		middlePanel.add(battleOptionsPanel,BorderLayout.SOUTH);
		middlePanel.add(battleLogPanel,BorderLayout.CENTER);
		
		playerArmyPanel.add(playerLabel);                                                //labels on both armies
		playerArmyPanel.add(chooseAttackingUnitMsg);                                                //labels on both armies
		enemyArmyPanel.add(enemyLabel);
		enemyArmyPanel.add(chooseTargetUnitMsg);
		
		setUpButton(attackButton);
		setUpButton(autoResolveButton);
		setUpButton(retreatButton);
		battleOptionsPanel.add(attackButton);
		battleOptionsPanel.add(autoResolveButton);
		battleOptionsPanel.add(retreatButton);
		//playerArmy.getUnits().get(i).toString().split(" ")[0] to get unit type as a string
		JToggleButton unitButton;
		
		String buttonString;
		for(int i = 0;i<playerArmy.getUnits().size();i++) {
			  buttonString = playerArmy.getUnits().get(i).unitInfo()+" , current soldier count is " + playerArmy.getUnits().get(i).getCurrentSoldierCount();
			  unitButton = new JToggleButton(buttonString);
			  unitButton.addActionListener(this);
			  playerUnitsButtons.add(unitButton);
			  playerUnitsButtonGroup.add(unitButton);
			  setUpButton(unitButton);
			  
			  playerArmyPanel.add(unitButton);
			  
		}
		
		for(int i = 0;i<enemyArmy.getUnits().size();i++) {
			  buttonString = enemyArmy.getUnits().get(i).unitInfo()+" , current soldier count is " + enemyArmy.getUnits().get(i).getCurrentSoldierCount() ; //there is a way to fix this using lvl for level and count for current soldier count as shorthandd terms to save space
			  unitButton = new JToggleButton(buttonString);
			  unitButton.addActionListener(this);
			  enemyUnitsButtons.add(unitButton);
			  enemyUnitsButtonGroup.add(unitButton);
			  setUpButton(unitButton);
			  unitButton.setMargin(new Insets(0, 0, 0, 0));
			  enemyArmyPanel.add(unitButton);
		}
		
		restPanel.add(enemyArmyPanel,BorderLayout.WEST);
		restPanel.add(playerArmyPanel,BorderLayout.EAST);
		restPanel.add(middlePanel,BorderLayout.CENTER);
		
		
		
		
		revalidate();
		
		
	}
//	public static void main(String[] args) {
//		Game theGame = null;
//		Army theArmy = new Army("7amada");
//		String theCity = null;
//		theArmy.getUnits().add(Archer.create("1"));
//		
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		theArmy.getUnits().add(Archer.create("1"));
//		
//		
//		
//		try {
//			theGame = new Game("ahmed", "cairo");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		BattleView battleView = new BattleView(theGame,theArmy, );
//	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == attackButton) {
			listener.onAttack();
		}else if (e.getSource() == autoResolveButton) {
			listener.onAutoResolve();
		}else if(e.getSource() == retreatButton) {
			listener.onRetreat();
		}
	}

	
	//setters and getters
	
	public ArrayList<JToggleButton> getPlayerUnitsButtons() {
		return playerUnitsButtons;
	}

	public void setPlayerUnitsButtons(ArrayList<JToggleButton> playerUnitsButtons) {
		this.playerUnitsButtons = playerUnitsButtons;
	}

	public ArrayList<JToggleButton> getEnemyUnitsButtons() {
		return enemyUnitsButtons;
	}

	public void setEnemyUnitsButtons(ArrayList<JToggleButton> enemyUnitsButtons) {
		this.enemyUnitsButtons = enemyUnitsButtons;
	}

	public ButtonGroup getEnemyUnitsButtonGroup() {
		return enemyUnitsButtonGroup;
	}

	public void setEnemyUnitsButtonGroup(ButtonGroup enemyUnitsButtonGroup) {
		this.enemyUnitsButtonGroup = enemyUnitsButtonGroup;
	}

	public JPanel getPlayerArmyPanel() {
		return playerArmyPanel;
	}

	public void setPlayerArmyPanel(JPanel playerArmyPanel) {
		this.playerArmyPanel = playerArmyPanel;
	}

	public JPanel getEnemyArmyPanel() {
		return enemyArmyPanel;
	}

	public void setEnemyArmyPanel(JPanel enemyArmyPanel) {
		this.enemyArmyPanel = enemyArmyPanel;
	}

	public JPanel getMiddlePanel() {
		return middlePanel;
	}

	public void setMiddlePanel(JPanel middlePanel) {
		this.middlePanel = middlePanel;
	}

	public JLabel getPlayerLabel() {
		return playerLabel;
	}

	public void setPlayerLabel(JLabel playerLabel) {
		this.playerLabel = playerLabel;
	}

	public JLabel getEnemyLabel() {
		return enemyLabel;
	}

	public void setEnemyLabel(JLabel enemyLabel) {
		this.enemyLabel = enemyLabel;
	}

	public JLabel getChooseAttackingUnitMsg() {
		return chooseAttackingUnitMsg;
	}

	public void setChooseAttackingUnitMsg(JLabel chooseAttackingUnitMsg) {
		this.chooseAttackingUnitMsg = chooseAttackingUnitMsg;
	}

	public JLabel getChooseTargetUnitMsg() {
		return chooseTargetUnitMsg;
	}

	public void setChooseTargetUnitMsg(JLabel chooseTargetUnitMsg) {
		this.chooseTargetUnitMsg = chooseTargetUnitMsg;
	}

	public JPanel getBattleLogPanel() {
		return battleLogPanel;
	}

	public void setBattleLogPanel(JPanel battleLogPanel) {
		this.battleLogPanel = battleLogPanel;
	}
	
	
	

}
