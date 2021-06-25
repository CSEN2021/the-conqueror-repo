package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import engine.Game;
import listeners.WorldMapViewListener;

public class WorldMapView extends TemplateView implements ActionListener
{
	private Game theGame;
	private JButton cairoButton = new JButton("Cairo");
	private JButton spartaButton = new JButton("Sparta");
	private JButton romeButton = new JButton("Rome");
	private JButton endTurnButton = new JButton("End Turn");
	private JButton targetButton = new JButton("Target a City");
	private JButton iniateArmyButton = new JButton("Iniate an Army");
	private JButton reloacteButton = new JButton("Relocate a unit");
	private WorldMapViewListener listener;
	private JPanel bottomPanel = new JPanel();
	private JPanel midPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private JTextArea armyTextArea = new JTextArea("Controlled Armies :\n");

	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;

		// background image
		g2D.drawImage(new ImageIcon("resources/worldMap.png").getImage(), 0, 0, null);

		// fix buttons
		fixPaint();
	}

	public WorldMapView(Game theGame)
	{
		super(theGame);
		this.theGame = theGame;
		// components
		
		cairoButton.addActionListener(this);
		if(!theGame.getPlayer().getControlledCities().get(0).getName().equals("Cairo"))
			cairoButton.setEnabled(false);
		spartaButton.addActionListener(this);
		if(!theGame.getPlayer().getControlledCities().get(0).getName().equals("Sparta"))
			spartaButton.setEnabled(false);
		romeButton.addActionListener(this);
		if(!theGame.getPlayer().getControlledCities().get(0).getName().equals("Rome"))
			romeButton.setEnabled(false);
		
		endTurnButton.addActionListener(this);
		targetButton.addActionListener(this);
		iniateArmyButton.addActionListener(this);
		reloacteButton.addActionListener(this);
		
		setUpButton(endTurnButton);
		setUpButton(romeButton);
		setUpButton(cairoButton);
		setUpButton(spartaButton);
		setUpButton(targetButton);
		setUpButton(reloacteButton);
		setUpButton(iniateArmyButton);
		
		armyTextArea.setBackground(new Color(0x3E4149));
		armyTextArea.setForeground(Color.white);
		armyTextArea.setPreferredSize(new Dimension(300,720));
		
		
		// panels

		bottomPanel.setBackground(new Color(0x3E4149));
		rightPanel.setBackground(new Color(0x3E4149));

		// add
		add(rightPanel, BorderLayout.EAST);
		add(bottomPanel, BorderLayout.SOUTH);
		add(midPanel, BorderLayout.CENTER);

		bottomPanel.add(iniateArmyButton);
		bottomPanel.add(reloacteButton);
		bottomPanel.add(targetButton);
		bottomPanel.add(endTurnButton);

		rightPanel.add(armyTextArea);

		midPanel.add(cairoButton, BorderLayout.CENTER);
		midPanel.add(spartaButton, BorderLayout.EAST);
		midPanel.add(romeButton, BorderLayout.WEST);
		midPanel.setOpaque(false);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		revalidate();
	}


	public void updateStats(Game theGame)
	{
		super.updateStats(theGame);
		for (int i = 0; i < theGame.getPlayer().getControlledArmies().size(); i++)
		{
			armyTextArea.setText("Controlled Armies :" +
					'\n' + theGame.getPlayer().getControlledArmies().get(i));
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
		bottomPanel.repaint();
		endTurnButton.repaint();
		rightPanel.repaint();
		armyTextArea.repaint();
		targetButton.repaint();
		reloacteButton.repaint();
		iniateArmyButton.repaint();
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

		}
		else if (e.getSource() == iniateArmyButton)
		{
			listener.onInitiate();
		}
		else if (e.getSource() == reloacteButton)
		{

		}

	}

	public void setListener(WorldMapViewListener listener)
	{
		this.listener = listener;
	}

}
