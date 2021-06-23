package views;

import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;



public class HomeView extends JFrame{

	public HomeView()
	{
		new JFrame();
		this.setSize(1280, 720);
		
		Label welcome = new Label("Welcome");
		JButton startGame = new JButton("Start Game");
		JTextField playerName = new JTextField("Enter your name");
		
		JPanel topPanel = new JPanel();
		JPanel midPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JPanel filler = new JPanel();
		topPanel.setLayout(new BorderLayout());
		JPanel welcomePanel = new JPanel();
		JTextField enterYourNameField = new JTextField("Please enter your name");
		midPanel.setBackground(Color.CYAN);
		welcomePanel.setBackground(Color.red);
		bottomPanel.setPreferredSize(new Dimension(0,330));
		
		topPanel.setPreferredSize(new Dimension(0,320));
		this.add(topPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);

		
		topPanel.add(filler,BorderLayout.CENTER);
		topPanel.add(welcomePanel,BorderLayout.SOUTH);
		welcomePanel.add(welcome,BorderLayout.CENTER);
		midPanel.add(enterYourNameField);
		bottomPanel.add(startGame,BorderLayout.CENTER);
		
       
		this.setTitle("The Conquerer");
		this.setResizable(false);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.validate();
		this.repaint();
	}
}
