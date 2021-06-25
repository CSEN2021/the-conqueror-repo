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



	private JLabel buildButton = new JLabel("Click to Build");
	private JLabel upgradeButton = new JLabel("Click to Upgrade");
	private JLabel recruitButton = new JLabel("Click to Recruit");
	private JButton barracksLabel = new JButton("No Barracks\n Click to build");
	private JButton barracksLvlLabel = new JButton("Level : 0");
	private JButton barracksRecruitLabel = new JButton("No unit");
	private JButton archeryRangeLabel = new JButton("No ArcheryRange\n Click to build");
	private JButton archeryRangeLvllLabel = new JButton("Level : 0");
	private JButton archeryRangeRecruitLabel = new JButton("No unit");
	private JButton stableLabel = new JButton("No Stable\n Click to build");
	private JButton stableLvlLabel = new JButton("Level : 0");
	private JButton stableRecruitLabel = new JButton("No unit");
	private JButton marketLabel = new JButton("No Market\n Click to build");
	private JButton marketLvlLabel = new JButton("Level : 0");
	private JButton farmLabel = new JButton("No Farm\n Click to build");
	private JButton farmLvlLabel = new JButton("Level : 0");
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
		setUpLabel(upgradeButton);
		setUpLabel(buildButton);
		setUpLabel(recruitButton);

		setUpButton(archeryRangeLabel);
		setUpButton(archeryRangeLvllLabel);
		setUpButton(archeryRangeRecruitLabel);
		setUpButton(barracksLabel);
		setUpButton(barracksLvlLabel);
		setUpButton(barracksRecruitLabel);
		setUpButton(stableLabel);
		setUpButton(stableLvlLabel);
		setUpButton(stableRecruitLabel);
		setUpButton(marketLabel);
		setUpButton(marketLvlLabel);
		setUpButton(farmLabel);
		setUpButton(farmLvlLabel);
		
		//adding listeners
		
		archeryRangeLabel.addActionListener(this);
		stableLabel.addActionListener(this);
		barracksLabel.addActionListener(this);
		farmLabel.addActionListener(this);
		marketLabel.addActionListener(this);	
		
		archeryRangeLvllLabel.addActionListener(this);
		stableLvlLabel.addActionListener(this);
		barracksLvlLabel.addActionListener(this);
		farmLvlLabel.addActionListener(this);
		marketLvlLabel.addActionListener(this);
		
		archeryRangeLvllLabel.setEnabled(false);
		stableLvlLabel.setEnabled(false);
		barracksLvlLabel.setEnabled(false);
		farmLvlLabel.setEnabled(false);
		marketLvlLabel.setEnabled(false);
		
		// panels
		midPanel.setLayout(new GridLayout(0, 3));
		midPanel.setPreferredSize(new Dimension(0, 200));
		
		// add
		
		add(midPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		midPanel.add(buildButton);
		midPanel.add(upgradeButton);
		midPanel.add(recruitButton);
		
		midPanel.add(archeryRangeLabel);
		midPanel.add(archeryRangeLvllLabel);
		midPanel.add(archeryRangeRecruitLabel);
		midPanel.add(barracksLabel);
		midPanel.add(barracksLvlLabel);
		midPanel.add(barracksRecruitLabel);
		midPanel.add(stableLabel);
		midPanel.add(stableLvlLabel);
		midPanel.add(stableRecruitLabel);
		
		midPanel.add(marketLabel);
		midPanel.add(marketLvlLabel);
		midPanel.add(fillerLabel);
		midPanel.add(farmLabel);
		midPanel.add(farmLvlLabel);
		
		
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == archeryRangeLabel)
		{
			listener.onBuild("ArcheryRange");
			archeryRangeLabel.setEnabled(false);
			archeryRangeLabel.setText("ArcheryRange Built");
			archeryRangeLvllLabel.setEnabled(true);
			archeryRangeLvllLabel.setText("Level: 1");
		}
		else if (e.getSource() == barracksLabel)
		{
			listener.onBuild("Barracks");
			barracksLabel.setEnabled(false);
			barracksLabel.setText("Barracks Built");
			barracksLvlLabel.setText("Level: 1");
			barracksLvlLabel.setEnabled(true);
		}
		else if (e.getSource() == stableLabel)
		{
			listener.onBuild("Stable");
			stableLabel.setEnabled(false);	
			stableLabel.setText("Stable Built");
			stableLvlLabel.setText("Level: 1");
			stableLvlLabel.setEnabled(true);
		}
		else if (e.getSource() == farmLabel)
		{
			listener.onBuild("Farm");
			farmLabel.setEnabled(false);
			farmLabel.setText("Farm Built");
			farmLvlLabel.setText("Level: 1");
			farmLvlLabel.setEnabled(true);
		}
		else if (e.getSource() == marketLabel)
		{
			listener.onBuild("Market");
			marketLabel.setEnabled(false);
			marketLabel.setText("Market Built");
			marketLvlLabel.setText("Level: 1");
			marketLvlLabel.setEnabled(true);
			
		}
		int currentLevel;
		if (e.getSource() == marketLvlLabel)
		{
			listener.onUpgrade("Market");
			currentLevel = Integer.parseInt(marketLvlLabel.getText().charAt(7) + "") + 1;
			marketLvlLabel.setText("Level: " + currentLevel);
			marketLvlLabel.setEnabled(false);
		}
		else if (e.getSource() == farmLvlLabel)
		{
			listener.onUpgrade("Farm");
			currentLevel = Integer.parseInt(farmLvlLabel.getText().charAt(7) + "") + 1;
			farmLvlLabel.setText("Level: " + currentLevel);
			farmLvlLabel.setEnabled(false);
		}
		else if (e.getSource() == stableLvlLabel)
		{
			listener.onUpgrade("Stable");
			currentLevel = Integer.parseInt(stableLvlLabel.getText().charAt(7) + "") + 1;
			stableLvlLabel.setText("Level: " + currentLevel);
			stableLvlLabel.setEnabled(false);
		}
		else if (e.getSource() == barracksLvlLabel)
		{
			listener.onUpgrade("Barracks");
			currentLevel = Integer.parseInt(barracksLvlLabel.getText().charAt(7) + "") + 1;
			barracksLvlLabel.setText("Level: " + currentLevel );
			barracksLvlLabel.setEnabled(false);
		}
		else if (e.getSource() == archeryRangeLvllLabel)
		{
			listener.onUpgrade("ArcheryRange");
			currentLevel = Integer.parseInt(archeryRangeLvllLabel.getText().charAt(7) + "") + 1;
			archeryRangeLvllLabel.setText("Level: " + currentLevel);
			archeryRangeLvllLabel.setEnabled(false);
		}
		
		
	}

	public CityViewListener getListener() {
		return listener;
	}

	public void setListener(CityViewListener listener) {
		this.listener = listener;
	}


	public JButton getBarracksLvlLabel() {
		return barracksLvlLabel;
	}


	public void setBarracksLvlLabel(JButton barracksLvlLabel) {
		this.barracksLvlLabel = barracksLvlLabel;
	}


	public JButton getArcheryRangeLvllLabel() {
		return archeryRangeLvllLabel;
	}


	public void setArcheryRangeLvllLabel(JButton archeryRangeLvllLabel) {
		this.archeryRangeLvllLabel = archeryRangeLvllLabel;
	}


	public JButton getStableLvlLabel() {
		return stableLvlLabel;
	}


	public void setStableLvlLabel(JButton stableLvlLabel) {
		this.stableLvlLabel = stableLvlLabel;
	}


	public JButton getMarketLvlLabel() {
		return marketLvlLabel;
	}


	public void setMarketLvlLabel(JButton marketLvlLabel) {
		this.marketLvlLabel = marketLvlLabel;
	}


	public JButton getFarmLvlLabel() {
		return farmLvlLabel;
	}


	public void setFarmLvlLabel(JButton farmLvlLabel) {
		this.farmLvlLabel = farmLvlLabel;
	}
	
}