package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import engine.Game;
import listeners.WorldMapViewListener;
import units.Status;

public class WorldMapView extends TemplateView implements ActionListener
{
	private Game theGame;
	private JButton cairoButton = new JButton("Cairo");
	private JButton spartaButton = new JButton("Sparta");
	private JButton romeButton = new JButton("Rome");
	private JButton endTurnButton = new JButton("End Turn");
	private JButton targetButton = new JButton("Target a City");
	private JButton initiateArmyButton = new JButton("Initiate an Army");
	private JButton reloacteButton = new JButton("Relocate a unit");
	private JButton showAllArmiesButton = new JButton("Show All Armies");
	private JButton enterBattleCairoButton = new JButton("Enter Battle");
	private JButton enterBattleSpartaButton = new JButton("Enter Battle");
	private JButton enterBattleRomeButton = new JButton("Enter Battle");

	private WorldMapViewListener listener;
	private JPanel bottomPanel = new JPanel();
	private JPanel midPanel = new JPanel(null);
	private JPanel rightPanel = new JPanel(new BorderLayout());
	private JPanel marchingPanel = new JPanel(new BorderLayout());
	private JTextArea controlledArmiesTextArea = new JTextArea("");
	private JTextArea marchingArmiesTextArea = new JTextArea("");
	private JLabel controlledArmiesLabel = new JLabel("Static Armies :\n");
	private JLabel marchingArmiesLabel = new JLabel("Attacking Armies :\n");

	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;

		// background image
		g2D.drawImage(new ImageIcon("resources/worldMap.115.png").getImage(), 0, 0, null);

		// fix buttons
		fixPaint();
	}

	public WorldMapView(Game theGame)
	{
		super(theGame);
		this.theGame = theGame;

		// components

		cairoButton.addActionListener(this);
		if (!theGame.getPlayer().getControlledCities().get(0).getName().equals("Cairo"))
			cairoButton.setEnabled(false);
		spartaButton.addActionListener(this);
		if (!theGame.getPlayer().getControlledCities().get(0).getName().equals("Sparta"))
			spartaButton.setEnabled(false);
		romeButton.addActionListener(this);
		if (!theGame.getPlayer().getControlledCities().get(0).getName().equals("Rome"))
			romeButton.setEnabled(false);
		
		enterBattleCairoButton.setEnabled(false);
		enterBattleRomeButton.setEnabled(false);
		enterBattleSpartaButton.setEnabled(false);

		endTurnButton.addActionListener(this);
		targetButton.addActionListener(this);
		initiateArmyButton.addActionListener(this);
		reloacteButton.addActionListener(this);
		showAllArmiesButton.addActionListener(this);

		setUpButton(endTurnButton);
		setUpButton(romeButton);
		setUpButton(cairoButton);
		setUpButton(spartaButton);
		setUpButton(targetButton);
		setUpButton(reloacteButton);
		setUpButton(initiateArmyButton);
		setUpButton(showAllArmiesButton);
		setUpButton(enterBattleRomeButton);
		setUpButton(enterBattleCairoButton);
		setUpButton(enterBattleSpartaButton);

		controlledArmiesTextArea.setBackground(new Color(0x404B69));
		controlledArmiesTextArea.setForeground(Color.white);
		controlledArmiesTextArea.setPreferredSize(new Dimension(350, 350));
		marchingArmiesTextArea.setBackground(new Color(0xF73859));
		marchingArmiesTextArea.setForeground(Color.white);
		marchingArmiesTextArea.setPreferredSize(new Dimension(350, 300));

		setUpLabel(controlledArmiesLabel);
		setUpLabel(marchingArmiesLabel);
		controlledArmiesLabel.setBackground(controlledArmiesTextArea.getBackground());
		marchingArmiesLabel.setBackground(marchingArmiesTextArea.getBackground());

		// panels

		bottomPanel.setBackground(new Color(0x3E4149));
		rightPanel.setBackground(new Color(0x39A6A3));

		// add
		add(rightPanel, BorderLayout.EAST);
		add(bottomPanel, BorderLayout.SOUTH);
		add(midPanel, BorderLayout.CENTER);

		bottomPanel.add(initiateArmyButton);
		bottomPanel.add(reloacteButton);
		bottomPanel.add(showAllArmiesButton);
		bottomPanel.add(targetButton);
		bottomPanel.add(endTurnButton);

		rightPanel.add(controlledArmiesLabel, BorderLayout.NORTH);
		rightPanel.add(controlledArmiesTextArea, BorderLayout.CENTER);
		rightPanel.add(marchingPanel, BorderLayout.SOUTH);

		marchingPanel.add(marchingArmiesLabel, BorderLayout.NORTH);
		marchingPanel.add(marchingArmiesTextArea, BorderLayout.CENTER);

		midPanel.add(cairoButton);
		cairoButton.setBounds(50, 90, 100, 50);
		midPanel.add(spartaButton);
		spartaButton.setBounds(750, 80, 100, 50);
		midPanel.add(romeButton);
		romeButton.setBounds(50, 550, 100, 50);
		midPanel.add(enterBattleCairoButton);
		enterBattleCairoButton.setBounds(160, 90, 100, 50);
		midPanel.add(enterBattleSpartaButton);
		enterBattleSpartaButton.setBounds(750, 140, 100, 50);
		midPanel.add(enterBattleRomeButton);
		enterBattleRomeButton.setBounds(160, 550, 100, 50);
		

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		revalidate();
	}

	public void updateArmiesPanel(Game theGame)
	{
		controlledArmiesTextArea.setText("");
		marchingArmiesTextArea.setText("");
		for (int i = 0; i < theGame.getPlayer().getControlledArmies().size(); i++)
		{
			String string = theGame.getPlayer().getControlledArmies().get(i).toString();
			if (theGame.getPlayer().getControlledArmies().get(i).getCurrentStatus() == Status.MARCHING)
			{
				marchingArmiesTextArea.append("Army " + (i + 1) + " ( Marching to "
						+ theGame.getPlayer().getControlledArmies().get(i).getTarget() + ", Distance left is "
						+ theGame.getPlayer().getControlledArmies().get(i).getDistancetoTarget() + " ): \n" + string);
			}
			else if (theGame.getPlayer().getControlledArmies().get(i).getCurrentStatus() == Status.BESIEGING)
			{
				controlledArmiesTextArea.append("Army " + (i + 1) + " ( Besieging "
						+ theGame.getPlayer().getControlledArmies().get(i).getCurrentLocation() + " ): \n" + string
						+ "\n");
			}
			else
			{
				controlledArmiesTextArea.append("Army " + (i + 1) + " ( at "
						+ theGame.getPlayer().getControlledArmies().get(i).getCurrentLocation() + " ): \n" + string
						+ "\n");
			}
		}
		repaint();
	}

	@Override
	public void fixPaint()
	{
		super.fixPaint();
		cairoButton.repaint();
		spartaButton.repaint();
		romeButton.repaint();
		enterBattleCairoButton.repaint();
		enterBattleRomeButton.repaint();
		enterBattleSpartaButton.repaint();
		bottomPanel.repaint();
		endTurnButton.repaint();
		rightPanel.repaint();
		controlledArmiesTextArea.repaint();
		targetButton.repaint();
		reloacteButton.repaint();
		initiateArmyButton.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == endTurnButton)
		{
			listener.onEndTurn();
		}
		else if (e.getSource() == cairoButton || e.getSource() == romeButton || e.getSource() == spartaButton)
		{
			listener.onOpenCity((JButton) e.getSource());
		}
		else if (e.getSource() == targetButton)
		{
			listener.onTargetCity();
		}
		else if (e.getSource() == initiateArmyButton)
		{
			listener.onInitiate();
		}
		else if (e.getSource() == reloacteButton)
		{
			listener.onRelocateUnit();
		}
		else if (e.getSource() == showAllArmiesButton)
		{
			listener.onShowAllArmies();
		}
		else if (e.getSource() == enterBattleCairoButton)
		{
			listener.onEnterBattle("Cairo");
		}
		else if (e.getSource() == enterBattleRomeButton)
		{
			listener.onEnterBattle("Rome");
		}
		else if (e.getSource() == enterBattleSpartaButton)
		{
			listener.onEnterBattle("Sparta");
		}
	}

	public void setListener(WorldMapViewListener listener)
	{
		this.listener = listener;
	}

}
