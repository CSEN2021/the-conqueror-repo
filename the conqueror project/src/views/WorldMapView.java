package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Game;
import engine.Player;

public class WorldMapView extends JFrame implements ActionListener
{
	private JPanel topPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private double gold;
	private String playerName;
	private int turnCount;
	private JLabel goldLabel = new JLabel();
	private JLabel playerNameLabel = new JLabel();
	private JLabel turnCountLabel = new JLabel();
	
	public void updateLabels()
	{
		goldLabel.setText("Gold : " + gold);
		playerNameLabel.setText("Player : " + playerName);
		turnCountLabel.setText("Turn : " + turnCount);
	}

	public WorldMapView(Game theGame)
	{
		// initialize labels
		setGold(theGame.getPlayer().getTreasury());
		setPlayerName(theGame.getPlayer().getName());
		setTurnCount(theGame.getCurrentTurnCount());
		updateLabels();
		goldLabel.setBackground(new Color(0xFAD63F));
		goldLabel.setOpaque(true);
		turnCountLabel.setBackground(new Color(0xE288F5));
		turnCountLabel.setOpaque(true);
		playerNameLabel.setBackground(new Color(0x6E93FA));
		playerNameLabel.setOpaque(true);
		
		// panels
		
		// adding components
		add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.add(playerNameLabel);
		bottomPanel.add(turnCountLabel);
		bottomPanel.add(goldLabel);
		
		// frame properties
		setIconImage(new ImageIcon("resources/icon.png").getImage());
		setSize(1280, 720);
		setTitle("The Conquerer");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		validate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}

	public void setGold(double gold)
	{
		this.gold = gold;
	}

	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}

	public void setTurnCount(int turnCount)
	{
		this.turnCount = turnCount;
	}

	

}
