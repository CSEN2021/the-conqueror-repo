package views;

import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;



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
		
		
		this.add(topPanel,BorderLayout.NORTH);
		this.add(midPanel,BorderLayout.CENTER);
		
		topPanel.setPreferredSize(new Dimension(100,360));
		topPanel.setBackground(Color.GRAY);
		
		
        playerName.setSize(new Dimension(5,10));
		midPanel.setLayout(new BorderLayout());
        
        midPanel.add(playerName,BorderLayout.NORTH);
		midPanel.add(startGame,BorderLayout.CENTER);
		topPanel.add(welcome,BorderLayout.CENTER);
		
		this.setTitle("Homepage");
		this.setResizable(false);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.validate();
		this.repaint();
	}
}
