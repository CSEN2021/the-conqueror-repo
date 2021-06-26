package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import engine.Game;
import engine.Player;

public class TemplateView extends JFrame
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

	public void updateStats(Game theGame)
	{
		goldLabel.setText("Gold : " + theGame.getPlayer().getTreasury());
		foodLabel.setText("Food : " + theGame.getPlayer().getFood());
		playerNameLabel.setText("Player : " + theGame.getPlayer().getName());
		turnCountLabel.setText("Turn : " + theGame.getCurrentTurnCount());
	}

	public TemplateView(Game theGame)
	{
		// initialize labels
		setGold(theGame.getPlayer().getTreasury());
		setFood(theGame.getPlayer().getFood());
		setPlayerName(theGame.getPlayer().getName());
		setTurnCount(theGame.getCurrentTurnCount());
		updateStats(theGame);
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
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		validate();
		repaint();
	}

	public void fixPaint()
	{
		goldLabel.repaint();
		foodLabel.repaint();
		playerNameLabel.repaint();
		turnCountLabel.repaint();
		topPanel.repaint();
	}

	public void setUpButton(JButton theButton)
	{
		theButton.setForeground(Color.WHITE);
		theButton.setBackground(Color.DARK_GRAY);
		theButton.setFocusable(false);
		theButton.repaint();
	}
	
	public void setUpButton(JToggleButton theButton)
	{
		theButton.setForeground(Color.WHITE);
		theButton.setBackground(Color.DARK_GRAY);
		theButton.setFocusable(false);
		theButton.repaint();
	}

	public void setUpLabel(JLabel theLabel)
	{
		theLabel.setHorizontalAlignment(JLabel.CENTER);
		theLabel.setBackground(Color.DARK_GRAY);
		theLabel.setForeground(Color.WHITE);
		theLabel.setOpaque(true);
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

	public JLabel getGoldLabel()
	{
		return goldLabel;
	}

	public void setGoldLabel(JLabel goldLabel)
	{
		this.goldLabel = goldLabel;
	}

	public JLabel getFoodLabel()
	{
		return foodLabel;
	}

	public void setFoodLabel(JLabel foodLabel)
	{
		this.foodLabel = foodLabel;
	}

	public JLabel getPlayerNameLabel()
	{
		return playerNameLabel;
	}

	public void setPlayerNameLabel(JLabel playerNameLabel)
	{
		this.playerNameLabel = playerNameLabel;
	}

	public JLabel getTurnCountLabel()
	{
		return turnCountLabel;
	}

	public void setTurnCountLabel(JLabel turnCountLabel)
	{
		this.turnCountLabel = turnCountLabel;
	}

}
