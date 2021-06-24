package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
	private JPanel fillerPanel = new JPanel();
	private JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
	private JPanel bottomPanel = new JPanel(new BorderLayout());
	private JPanel statsPanel = new JPanel();
	// stats
	private double gold;
	private double food;
	private String playerName;
	private int turnCount;
	
	private JLabel goldLabel = new JLabel();
	private JLabel foodLabel = new JLabel();
	private JLabel playerNameLabel = new JLabel();
	private JLabel turnCountLabel = new JLabel();
	
	public void updateLabels()
	{
		goldLabel.setText("Gold : " + gold);
		foodLabel.setText("Food : " + food);
		playerNameLabel.setText("Player : " + playerName);
		turnCountLabel.setText("Turn : " + turnCount);
	}

	public WorldMapView(Game theGame)
	{
		// initialize labels
		setGold(theGame.getPlayer().getTreasury());
		setFood(theGame.getPlayer().getFood());
		setPlayerName(theGame.getPlayer().getName());
		setTurnCount(theGame.getCurrentTurnCount());
		updateLabels();
		goldLabel.setBackground(new Color(0xFAD63F));
		goldLabel.setOpaque(true);
		foodLabel.setBackground(new Color(0xF58C3C));
		foodLabel.setOpaque(true);
		turnCountLabel.setBackground(new Color(0xE288F5));
		turnCountLabel.setOpaque(true);
		playerNameLabel.setBackground(new Color(0x6E93FA));
		playerNameLabel.setOpaque(true);
		
		// panels
		
		
		// adding components
		add(topPanel, BorderLayout.NORTH);
		topPanel.setBackground(Color.red);
		topPanel.add(statsPanel, BorderLayout.WEST);
		topPanel.setBackground(new Color(0x3E4149));
		statsPanel.setOpaque(false);
		statsPanel.add(playerNameLabel);
		statsPanel.add(turnCountLabel);
		statsPanel.add(goldLabel);
		statsPanel.add(foodLabel);
		
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
	
	public void setFood(double food)
	{
		this.food = food;
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
