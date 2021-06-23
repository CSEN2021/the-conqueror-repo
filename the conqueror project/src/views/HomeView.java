package views;

import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class HomeView extends JFrame{

	public HomeView()
	{
		new JFrame();
		this.setSize(1280, 720);
		Label welcome = new Label("Welcome");
		JButton startGame = new JButton("Start Game");
		JTextField playerName = new JTextField();
		JLabel pic = new JLabel();
		ImageIcon image = new ImageIcon("Images/480378.jpg");
		
		this.add(pic);
        playerName.setSize(new Dimension(5,10));
        startGame.setSize(50,100);
		this.setLayout(new GridLayout(7,7));
		for (int i = 0; i < 49; i++)
		{
			if (i == 17)
			{
				JPanel panel = new JPanel();
				
				panel.add(welcome,BorderLayout.CENTER);
				this.add(panel);
			}
			else if (i == 24)
			{
				this.add(playerName,BorderLayout.CENTER);
			}
			else if (i == 31)
			{
				this.add(startGame,BorderLayout.CENTER);
			}
			else
			{
				Label panel = new Label();
				this.add(panel,BorderLayout.CENTER);
			}
		}
				
		this.pack();
		this.setTitle("Homepage");
		this.setResizable(false);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.validate();
		this.repaint();
	}
	
	public static void main(String[] args) {
		new HomeView();
	}
}
