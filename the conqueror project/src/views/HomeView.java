package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import listeners.HomeViewListener;

public class HomeView extends JFrame implements ActionListener
{
	private JButton startGame = new JButton("Start Game");;
	private JButton cairoButton = new JButton("Cairo");
	private JButton romeButton = new JButton("Rome");
	private JButton spartaButton = new JButton("Sparta");
	private String selectedCity;
	private HomeViewListener listener;
	private JTextField enterYourNameField;
	private JLabel welcome = new JLabel("Enter Your Name");

	public void setListener(HomeViewListener listener)
	{
		this.listener = listener;
	}

	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		
		// background image
		g2D.drawImage(new ImageIcon("resources/startScreen.jpg").getImage(), 0, 0, null);
		
		// fix buttons
		startGame.repaint();
		cairoButton.repaint();
		spartaButton.repaint();
		romeButton.repaint();
		welcome.repaint();
	}
	public void loadButton(JButton theButton)
	{
		theButton.setIcon(new ImageIcon("resources/"+theButton.getText()+"Image.jpg"));
		theButton.setPreferredSize(new Dimension(300,220));	
		theButton.setVerticalTextPosition(JButton.TOP);
		theButton.setHorizontalTextPosition(JButton.CENTER);
		theButton.setBackground(Color.LIGHT_GRAY);
	}
	public HomeView()
	{
		super();
		
		// text and labels
		welcome.setHorizontalAlignment(JLabel.CENTER);
		enterYourNameField = new JTextField();
		enterYourNameField.setPreferredSize(new Dimension(250, 25));
		enterYourNameField.setHorizontalAlignment(JTextField.CENTER);
		
		// panels
		JPanel topPanel = new JPanel();
		JPanel midPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JPanel filler = new JPanel();
		topPanel.setLayout(new BorderLayout());
		JPanel welcomePanel = new JPanel();
		JPanel chooseCityPanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		bottomPanel.setPreferredSize(new Dimension(0, 420));
		bottomPanel.setLayout(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(0, 230));
		
		// buttons
		loadButton(cairoButton);
		loadButton(spartaButton);
		loadButton(romeButton);
		
		startGame.setForeground(Color.WHITE);
		startGame.setBackground(Color.DARK_GRAY);
		startGame.setFocusable(false);
		startGame.addActionListener(this);
		
		// adding components
		this.add(topPanel, BorderLayout.NORTH);
		this.add(midPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);

		topPanel.add(filler, BorderLayout.CENTER);
		topPanel.add(welcomePanel, BorderLayout.SOUTH);
		welcomePanel.add(welcome, BorderLayout.CENTER);
		midPanel.add(enterYourNameField);
		bottomPanel.add(buttonPanel, BorderLayout.CENTER);
		buttonPanel.add(startGame);
		bottomPanel.add(chooseCityPanel, BorderLayout.NORTH);
		chooseCityPanel.add(cairoButton,BorderLayout.SOUTH);
		chooseCityPanel.add(spartaButton,BorderLayout.SOUTH);
		chooseCityPanel.add(romeButton,BorderLayout.SOUTH);

		// frame properties
		this.setSize(1280, 720);
		this.setTitle("The Conquerer");
		this.setResizable(false);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.validate();
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == startGame)
		{
			if (!enterYourNameField.getText().equals(""))
			{
				listener.onStartGame(enterYourNameField.getText());
				startGame.setEnabled(false);
				enterYourNameField.setEditable(false);
			}
			else
			{
				welcome.setText("ENTER A NAME !!!");
				welcome.setForeground(Color.red);
			}
		}

	}

}
