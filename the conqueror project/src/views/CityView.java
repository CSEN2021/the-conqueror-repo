package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CityView extends JFrame implements ActionListener
{
	
	public CityView()
	{
		super();
		
		JPanel topPanel = new JPanel(new BorderLayout());
		JPanel midPanel = new JPanel();
		JPanel bottomPanel = new JPanel(new BorderLayout());
		JPanel filler = new JPanel();
		midPanel.setLayout(new GridLayout(1, 3));
		
		midPanel.add(new JButton("Build"));
		midPanel.add(new JButton("Upgrade"));
		midPanel.add(new JButton("Recruit"));
		
		bottomPanel.setPreferredSize(new Dimension(0, 320));
		midPanel.setPreferredSize(new Dimension(0, 420));
		topPanel.setPreferredSize(new Dimension(0, 130));
		
		
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		topPanel.add(filler, BorderLayout.CENTER);

		
		
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		new CityView();
	}

}