package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.Game;

public class CityView extends TemplateView implements ActionListener
{
	private JButton buildButton = new JButton("Build");
	private JButton upgradeButton = new JButton("Upgrade");
	private JButton recruitButton = new JButton("Recruit Button");
	private JPanel midPanel = new JPanel();
	private JPanel bottomPanel = new JPanel(new BorderLayout());
	private JPanel filler = new JPanel();
	
	public CityView(Game theGame)
	{
		super(theGame);
		
		// components
		midPanel.add(buildButton);
		midPanel.add(upgradeButton);
		midPanel.add(recruitButton);
		setUpButton(upgradeButton);
		setUpButton(buildButton);
		setUpButton(recruitButton);
		
		// panels
		midPanel.setLayout(new GridLayout(1, 0));
		
		//midPanel.setPreferredSize(new Dimension(0, 420));
		
		// add
		add(midPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}