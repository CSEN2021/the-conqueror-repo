package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import listeners.HomeViewListener;

public class StartScreen extends JFrame implements ActionListener
{
	private JToggleButton startGame = new JToggleButton("Start Game");
	private JToggleButton cairoButton = new JToggleButton("Cairo");
	private JToggleButton romeButton = new JToggleButton("Rome");
	private JToggleButton spartaButton = new JToggleButton("Sparta");
	private String selectedCity = "";
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
		theButton.setIcon(new ImageIcon("resources/" + theButton.getText() + "Image.jpg"));
		theButton.setPreferredSize(new Dimension(300, 220));
		theButton.setVerticalTextPosition(JToggleButton.TOP);
		theButton.setHorizontalTextPosition(JToggleButton.CENTER);
		theButton.setBackground(Color.LIGHT_GRAY);
		theButton.addActionListener(this);
	}
	
	public void loadButton(JToggleButton theButton)
	{
		theButton.setIcon(new ImageIcon("resources/" + theButton.getText() + "Image.jpg"));
		theButton.setPreferredSize(new Dimension(300, 220));
		theButton.setVerticalTextPosition(JToggleButton.TOP);
		theButton.setHorizontalTextPosition(JToggleButton.CENTER);
		theButton.setBackground(Color.DARK_GRAY);
		theButton.setForeground(Color.white);
		theButton.addActionListener(this);
	}

	public StartScreen()
	{
		// text and labels
		welcome.setHorizontalAlignment(JLabel.CENTER);
		enterYourNameField = new JTextField();
		enterYourNameField.setPreferredSize(new Dimension(250, 25));
		enterYourNameField.setHorizontalAlignment(JTextField.CENTER);

		// panels
		JPanel topPanel = new JPanel(new BorderLayout());
		JPanel midPanel = new JPanel();
		JPanel bottomPanel = new JPanel(new BorderLayout());
		JPanel filler = new JPanel();
		JPanel enterYourNamePanel = new JPanel();
		JPanel chooseCityPanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		bottomPanel.setPreferredSize(new Dimension(0, 420));
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
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		topPanel.add(filler, BorderLayout.CENTER);
		topPanel.add(enterYourNamePanel, BorderLayout.SOUTH);
		enterYourNamePanel.add(welcome, BorderLayout.CENTER);

		midPanel.add(enterYourNameField);

		bottomPanel.add(buttonPanel, BorderLayout.CENTER);
		buttonPanel.add(startGame);
		bottomPanel.add(chooseCityPanel, BorderLayout.NORTH);
		chooseCityPanel.add(cairoButton, BorderLayout.SOUTH);
		chooseCityPanel.add(spartaButton, BorderLayout.SOUTH);
		chooseCityPanel.add(romeButton, BorderLayout.SOUTH);

		// frame properties
		
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
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == cairoButton && cairoButton.isSelected())
		{
			spartaButton.setSelected(false);
			romeButton.setSelected(false);
		}
		else if(e.getSource() == spartaButton && spartaButton.isSelected())
		{
			cairoButton.setSelected(false);
			romeButton.setSelected(false);
		}
		else if(e.getSource() == romeButton && romeButton.isSelected())
		{
			cairoButton.setSelected(false);
			spartaButton.setSelected(false);
		}
		if (e.getSource() == startGame)
		{
			if (!enterYourNameField.getText().equals("") && !selectedCity.equals("") && (romeButton.isSelected()||spartaButton.isSelected()||cairoButton.isSelected()))
			{
				listener.onStartGame(enterYourNameField.getText(), selectedCity, this);
				startGame.setEnabled(false);
				enterYourNameField.setEditable(false);
				//this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "please enter a name & select a city","Warning", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			selectedCity = ((JToggleButton) e.getSource()).getText();
		}

	}

}
