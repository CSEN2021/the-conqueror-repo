package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import buildings.ArcheryRange;
import engine.City;
import engine.Game;
import listeners.CityViewListener;

public class CityView extends TemplateView implements ActionListener
{
	private City currentCity;
	private CityViewListener listener;
	
	
	public City getCurrentCity() {
		return currentCity;
	}
	
	
	public void setCurrentCity(City currentCity) {
		this.currentCity = currentCity;
	}



	private JLabel buildLabel = new JLabel("Click to Build");
	private JLabel upgradeLabel = new JLabel("Click to Upgrade");
	private JLabel recruitLabel = new JLabel("Click to Recruit");
	private JButton barracksButton = new JButton("No Barracks");
	private JButton barracksLvlButton = new JButton("Level : 0");
	private JButton barracksRecruitButton = new JButton("No unit");
	private JButton archeryRangeButton = new JButton("No ArcheryRange\n Click to build");
	private JButton archeryRangeLvlButton = new JButton("Level : 0");
	private JButton archeryRangeRecruitButton = new JButton("No unit");
	private JButton stableButton = new JButton("No Stable\n Click to build");
	private JButton stableLvlButton = new JButton("Level : 0");
	private JButton stableRecruitButton = new JButton("No unit");
	private JButton marketButton = new JButton("No Market\n Click to build");
	private JButton marketLvlButton = new JButton("Level : 0");
	private JButton farmButton = new JButton("No Farm\n Click to build");
	private JButton farmLvlButton = new JButton("Level : 0");
	
	
	private JLabel fillerLabel = new JLabel("");
	private JPanel midPanel = new JPanel();
	private JPanel bottomPanel = new JPanel(new BorderLayout());
	private JPanel fillerPanel = new JPanel();
	
	
	
	
	
	public void setUpButton2(JButton theButton, String path )
	{
		theButton.setIcon(new ImageIcon("resources/" + path));
		theButton.setForeground(Color.WHITE);
		theButton.setBackground(Color.DARK_GRAY);
		theButton.setFocusable(false);
		theButton.repaint();
	}
	
	public CityView(Game theGame)
	{
		super(theGame);
		
		// components
		setUpLabel(upgradeLabel);
		setUpLabel(buildLabel);
		setUpLabel(recruitLabel);

		setUpButton(archeryRangeButton);
		setUpButton(archeryRangeLvlButton);
		setUpButton(archeryRangeRecruitButton);
		setUpButton(barracksButton);
		setUpButton(barracksLvlButton);
		setUpButton(barracksRecruitButton);
		setUpButton(stableButton);
		setUpButton(stableLvlButton);
		setUpButton(stableRecruitButton);
		setUpButton(marketButton);
		setUpButton(marketLvlButton);
		setUpButton(farmButton);
		setUpButton(farmLvlButton);
		
		//adding listeners
		
		archeryRangeButton.addActionListener(this);
		stableButton.addActionListener(this);
		barracksButton.addActionListener(this);
		farmButton.addActionListener(this);
		marketButton.addActionListener(this);	
		
		archeryRangeLvlButton.addActionListener(this);
		stableLvlButton.addActionListener(this);
		barracksLvlButton.addActionListener(this);
		farmLvlButton.addActionListener(this);
		marketLvlButton.addActionListener(this);
		
		archeryRangeLvlButton.setEnabled(false);
		stableLvlButton.setEnabled(false);
		barracksLvlButton.setEnabled(false);
		farmLvlButton.setEnabled(false);
		marketLvlButton.setEnabled(false);
		
		// panels
		midPanel.setLayout(new GridLayout(0, 3));
		midPanel.setPreferredSize(new Dimension(0, 200));
		
		// add
		
		add(midPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		midPanel.add(buildLabel);
		midPanel.add(upgradeLabel);
		midPanel.add(recruitLabel);
		
		midPanel.add(archeryRangeButton);
		midPanel.add(archeryRangeLvlButton);
		midPanel.add(archeryRangeRecruitButton);
		midPanel.add(barracksButton);
		midPanel.add(barracksLvlButton);
		midPanel.add(barracksRecruitButton);
		midPanel.add(stableButton);
		midPanel.add(stableLvlButton);
		midPanel.add(stableRecruitButton);
		
		midPanel.add(marketButton);
		midPanel.add(marketLvlButton);
		midPanel.add(fillerLabel);
		midPanel.add(farmButton);
		midPanel.add(farmLvlButton);
		
		
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == archeryRangeButton)
		{
			listener.onBuild("ArcheryRange");
			archeryRangeButton.setEnabled(false);
			archeryRangeButton.setText("ArcheryRange Built");
			archeryRangeLvlButton.setEnabled(true);
			archeryRangeLvlButton.setText("Level: 1");
		}
		else if (e.getSource() == barracksButton)
		{
			listener.onBuild("Barracks");
			barracksButton.setEnabled(false);
			barracksButton.setText("Barracks Built");
			barracksLvlButton.setText("Level: 1");
			barracksLvlButton.setEnabled(true);
		}
		else if (e.getSource() == stableButton)
		{
			listener.onBuild("Stable");
			stableButton.setEnabled(false);	
			stableButton.setText("Stable Built");
			stableLvlButton.setText("Level: 1");
			stableLvlButton.setEnabled(true);
		}
		else if (e.getSource() == farmButton)
		{
			listener.onBuild("Farm");
			farmButton.setEnabled(false);
			farmButton.setText("Farm Built");
			farmLvlButton.setText("Level: 1");
			farmLvlButton.setEnabled(true);
		}
		else if (e.getSource() == marketButton)
		{
			listener.onBuild("Market");
			marketButton.setEnabled(false);
			marketButton.setText("Market Built");
			marketLvlButton.setText("Level: 1");
			marketLvlButton.setEnabled(true);
			
		}
		int currentLevel;
		if (e.getSource() == marketLvlButton)
		{
			listener.onUpgrade("Market");
			currentLevel = Integer.parseInt(marketLvlButton.getText().charAt(7) + "") + 1;
			marketLvlButton.setText("Level: " + currentLevel);
			marketLvlButton.setEnabled(false);
		}
		else if (e.getSource() == farmLvlButton)
		{
			listener.onUpgrade("Farm");
			currentLevel = Integer.parseInt(farmLvlButton.getText().charAt(7) + "") + 1;
			farmLvlButton.setText("Level: " + currentLevel);
			farmLvlButton.setEnabled(false);
		}
		else if (e.getSource() == stableLvlButton)
		{
			listener.onUpgrade("Stable");
			currentLevel = Integer.parseInt(stableLvlButton.getText().charAt(7) + "") + 1;
			stableLvlButton.setText("Level: " + currentLevel);
			stableLvlButton.setEnabled(false);
		}
		else if (e.getSource() == barracksLvlButton)
		{
			listener.onUpgrade("Barracks");
			currentLevel = Integer.parseInt(barracksLvlButton.getText().charAt(7) + "") + 1;
			barracksLvlButton.setText("Level: " + currentLevel );
			barracksLvlButton.setEnabled(false);
		}
		else if (e.getSource() == archeryRangeLvlButton)
		{
			listener.onUpgrade("ArcheryRange");
			currentLevel = Integer.parseInt(archeryRangeLvlButton.getText().charAt(7) + "") + 1;
			archeryRangeLvlButton.setText("Level: " + currentLevel);
			archeryRangeLvlButton.setEnabled(false);
		}
		
		
	}

	public CityViewListener getListener() {
		return listener;
	}

	public void setListener(CityViewListener listener) {
		this.listener = listener;
	}


	public JButton getBarracksLvlLabel() {
		return barracksLvlButton;
	}


	public void setBarracksLvlLabel(JButton barracksLvlLabel) {
		this.barracksLvlButton = barracksLvlLabel;
	}


	public JButton getArcheryRangeLvllLabel() {
		return archeryRangeLvlButton;
	}


	public void setArcheryRangeLvllLabel(JButton archeryRangeLvllLabel) {
		this.archeryRangeLvlButton = archeryRangeLvllLabel;
	}


	public JButton getStableLvlLabel() {
		return stableLvlButton;
	}


	public void setStableLvlLabel(JButton stableLvlLabel) {
		this.stableLvlButton = stableLvlLabel;
	}


	public JButton getMarketLvlLabel() {
		return marketLvlButton;
	}


	public void setMarketLvlLabel(JButton marketLvlLabel) {
		this.marketLvlButton = marketLvlLabel;
	}


	public JButton getFarmLvlLabel() {
		return farmLvlButton;
	}


	public void setFarmLvlLabel(JButton farmLvlLabel) {
		this.farmLvlButton = farmLvlLabel;
	}
	
}