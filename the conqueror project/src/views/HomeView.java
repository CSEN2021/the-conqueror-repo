package views;

import java.awt.*;
import javax.swing.*;


public class HomeView extends JFrame{

	public HomeView()
	{
		new JFrame();
		this.setSize(1280, 720);
		
		Label welcome = new Label("   Enter Your Name");
		JButton startGame = new JButton("Start Game");
		JTextField enterYourNameField = new JTextField();
		enterYourNameField.setPreferredSize(new Dimension(100,25));

		JPanel topPanel = new JPanel();
		JPanel midPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JPanel filler = new JPanel();
		topPanel.setLayout(new BorderLayout());
		JPanel welcomePanel = new JPanel();
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
