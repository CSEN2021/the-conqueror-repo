package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.Farm;
import buildings.Market;
import buildings.Stable;
import engine.City;
import engine.Game;
import listeners.CityViewListener;

public class CityView extends TemplateView implements ActionListener
{
	private City currentCity;
	private CityViewListener listener;

	public City getCurrentCity()
	{
		return currentCity;
	}

	public void setCurrentCity(City currentCity)
	{
		this.currentCity = currentCity;
	}

	private JButton barracksButton = new JButton("Build Barracks. Cost: 2000");
	private JButton barracksLvlButton = new JButton("Build to upgrade.");
	private JButton barracksRecruitButton = new JButton("Build Barracks to recruit");

	private JButton archeryRangeButton = new JButton("Build an ArcheryRange. Cost: 1500");
	private JButton archeryRangeLvlButton = new JButton("Build to upgrade.");
	private JButton archeryRangeRecruitButton = new JButton("Build an Archeryrange to recruit");

	private JButton stableButton = new JButton("Build a Stable. Cost: 2500");
	private JButton stableLvlButton = new JButton("Build to upgrade.");
	private JButton stableRecruitButton = new JButton("Build a Stable to recruit");

	private JButton marketButton = new JButton("Build a Market. Cost: 1500");
	private JButton marketLvlButton = new JButton("Build to upgrade.");

	private JButton farmButton = new JButton("Build a Farm. Cost: 1000");
	private JButton farmLvlButton = new JButton("Build to upgrade.");

	private JButton showDefendingArmy = new JButton("Show Defending Army");

	private JPanel midPanel = new JPanel();
	private JPanel bottomPanel = new JPanel(new BorderLayout());

	JTextArea defendingArmiesTextArea = new JTextArea();
	JLabel cityIcon;

	public JLabel getCityIcon()
	{
		return cityIcon;
	}

	public void setCityIcon(JLabel cityIcon)
	{
		this.cityIcon = cityIcon;
	}

	public void setUpButtonWithImage(JButton theButton, String path)
	{
		/*
		 * theButton.setIcon(new ImageIcon("resources/" + path));
		 * theButton.setForeground(Color.WHITE);
		 * theButton.setBackground(Color.DARK_GRAY); theButton.setFocusable(false);
		 * theButton.repaint();
		 */

		theButton.setIcon(new ImageIcon("resources/" + path));
		theButton.setHorizontalTextPosition(JButton.CENTER);
		theButton.setVerticalTextPosition(JButton.CENTER);
		theButton.setForeground(Color.white);
		theButton.setFont(new Font("Arial", Font.PLAIN, 40));
	}

	public CityView(Game theGame, City theCity)
	{
		super(theGame);

		// components
		currentCity = theCity;
		int currentLevel;
		Building currentBuilding;
		archeryRangeLvlButton.setEnabled(false);
		stableLvlButton.setEnabled(false);
		barracksLvlButton.setEnabled(false);
		farmLvlButton.setEnabled(false);
		marketLvlButton.setEnabled(false);

		archeryRangeRecruitButton.setEnabled(false);
		barracksRecruitButton.setEnabled(false);
		stableRecruitButton.setEnabled(false);

		for (int i = 0; i < currentCity.getMilitaryBuildings().size(); i++)
		{
			currentBuilding = currentCity.getMilitaryBuildings().get(i);

			if (currentBuilding instanceof ArcheryRange)
			{
				currentLevel = currentBuilding.getLevel();
				archeryRangeLvlButton.setText("Level: " + currentLevel + " Cost: " + currentBuilding.getUpgradeCost());
				archeryRangeRecruitButton
						.setText("Archer, Cost: " + (((ArcheryRange) (currentBuilding)).getRecruitmentCost()));
				archeryRangeLvlButton.setEnabled(true);
				archeryRangeButton.setEnabled(false);
				archeryRangeRecruitButton.setEnabled(true);
				if (currentLevel == 3)
				{
					archeryRangeLvlButton.setText("Max Level");
				}

			}
			else if (currentBuilding instanceof Barracks)
			{
				currentLevel = currentBuilding.getLevel();
				barracksLvlButton.setText("Level: " + currentLevel + " Cost: " + currentBuilding.getUpgradeCost());

				barracksRecruitButton
						.setText("Infantry, Cost: " + (((Barracks) (currentBuilding)).getRecruitmentCost()));
				barracksLvlButton.setEnabled(true);
				barracksButton.setEnabled(false);
				barracksRecruitButton.setEnabled(true);
				if (currentLevel == 3)
				{
					barracksLvlButton.setText("Max Level");
				}

			}
			else if (currentBuilding instanceof Stable)
			{
				currentLevel = currentBuilding.getLevel();

				stableLvlButton.setText("Level: " + currentLevel + " Cost: " + currentBuilding.getUpgradeCost());
				stableRecruitButton.setText("");
				stableRecruitButton.setText("Cavalry, Cost: " + (((Stable) (currentBuilding)).getRecruitmentCost()));
				stableLvlButton.setEnabled(true);
				stableButton.setEnabled(false);
				stableRecruitButton.setEnabled(true);
				if (currentLevel == 3)
				{
					stableLvlButton.setText("Max Level");
				}

			}

		}

		for (int i = 0; i < currentCity.getEconomicalBuildings().size(); i++)
		{
			currentBuilding = currentCity.getEconomicalBuildings().get(i);
			if (currentBuilding instanceof Farm)
			{
				currentLevel = currentBuilding.getLevel();
				farmLvlButton.setText("Level: " + currentLevel + " Cost: " + currentBuilding.getUpgradeCost());

				farmLvlButton.setEnabled(true);
				farmButton.setEnabled(false);
				if (currentLevel == 3)
				{
					farmLvlButton.setText("Max Level");
				}

			}
			else if (currentBuilding instanceof Market)
			{
				currentLevel = currentBuilding.getLevel();
				marketLvlButton.setText("Level: " + currentLevel + " Cost: " + currentBuilding.getUpgradeCost());

				marketLvlButton.setEnabled(true);
				marketButton.setEnabled(false);
				if (currentLevel == 3)
				{
					marketLvlButton.setText("Max Level");
				}

			}
		}

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

		setUpButton(showDefendingArmy);

		// adding listeners

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

		stableRecruitButton.addActionListener(this);
		archeryRangeRecruitButton.addActionListener(this);
		barracksRecruitButton.addActionListener(this);

		showDefendingArmy.addActionListener(this);

		archeryRangeButton.setBackground(Color.decode("#000b14"));
		barracksButton.setBackground(Color.decode("#000b14"));
		stableButton.setBackground(Color.decode("#000b14"));
		farmButton.setBackground(Color.decode("#003666"));
		marketButton.setBackground(Color.decode("#003666"));

		archeryRangeLvlButton.setBackground(Color.decode("#002c52"));
		barracksLvlButton.setBackground(Color.decode("#002c52"));
		stableLvlButton.setBackground(Color.decode("#002c52"));
		farmLvlButton.setBackground(Color.decode("#002c52"));
		marketLvlButton.setBackground(Color.decode("#002c52"));

		archeryRangeRecruitButton.setBackground(Color.decode("#DA0037"));
		barracksRecruitButton.setBackground(Color.decode("#DA0037"));
		stableRecruitButton.setBackground(Color.decode("#DA0037"));

		showDefendingArmy.setBackground(Color.decode("#444444"));

		// panels
		midPanel.setLayout(new GridLayout(0, 3));
		midPanel.setPreferredSize(new Dimension(0, 200));

		// add

		add(midPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

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
		midPanel.add(showDefendingArmy);
		midPanel.add(farmButton);
		midPanel.add(farmLvlButton);

		cityIcon = new JLabel();

		/*
		 * JButton button = new JButton( "Centered" ); button.setIcon( new
		 * ImageIcon("resources/Cairo 2.jpg") );
		 * button.setHorizontalTextPosition(JButton.CENTER);
		 * button.setVerticalTextPosition(JButton.CENTER);
		 * button.setForeground(Color.white); button.setFont(new Font("Arial",
		 * Font.PLAIN, 40)); midPanel.add(button);
		 */

		midPanel.add(cityIcon);

	}

	public void unlockRecruitButtons()
	{
		if (getBarracksButton().isEnabled() == false)
		{
			getBarracksRecruitButton().setEnabled(true);
		}
		if (getArcheryRangeButton().isEnabled() == false)
		{
			getArcheryRangeRecruitButton().setEnabled(true);
		}
		if (getStableButton().isEnabled() == false)
		{
			getStableRecruitButton().setEnabled(true);
		}
	}

	public void drawDefendingArmy()
	{
		defendingArmiesTextArea.setText(currentCity.getDefendingArmy().toString());
		defendingArmiesTextArea.setBackground(Color.DARK_GRAY);
		defendingArmiesTextArea.setForeground(Color.white);
		defendingArmiesTextArea.setEditable(false);
		JLabel fillJLabel = new JLabel();
		fillJLabel.setPreferredSize(new Dimension(475, 0));
		fillJLabel.setBackground(Color.DARK_GRAY);
		fillJLabel.setOpaque(true);
		bottomPanel.add(fillJLabel, BorderLayout.WEST);
		bottomPanel.add(defendingArmiesTextArea, BorderLayout.CENTER);
	}

	public JButton getBarracksRecruitButton()
	{
		return barracksRecruitButton;
	}

	public void setBarracksRecruitButton(JButton barracksRecruitButton)
	{
		this.barracksRecruitButton = barracksRecruitButton;
	}

	public JButton getArcheryRangeRecruitButton()
	{
		return archeryRangeRecruitButton;
	}

	public void setArcheryRangeRecruitButton(JButton archeryRangeRecruitButton)
	{
		this.archeryRangeRecruitButton = archeryRangeRecruitButton;
	}

	public JButton getStableRecruitButton()
	{
		return stableRecruitButton;
	}

	public void setStableRecruitButton(JButton stableRecruitButton)
	{
		this.stableRecruitButton = stableRecruitButton;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{

		// Building

		if (e.getSource() == archeryRangeButton)
		{
			listener.onBuild("ArcheryRange");
		}
		else if (e.getSource() == barracksButton)
		{
			listener.onBuild("Barracks");
		}
		else if (e.getSource() == stableButton)
		{
			listener.onBuild("Stable");
		}
		else if (e.getSource() == farmButton)
		{
			listener.onBuild("Farm");
		}
		else if (e.getSource() == marketButton)
		{
			listener.onBuild("Market");
		}

		// Upgrading
		if (e.getSource() == marketLvlButton)
		{
			listener.onUpgrade("Market");
		}
		else if (e.getSource() == farmLvlButton)
		{
			listener.onUpgrade("Farm");
		}
		else if (e.getSource() == stableLvlButton)
		{
			listener.onUpgrade("Stable");
		}
		else if (e.getSource() == barracksLvlButton)
		{
			listener.onUpgrade("Barracks");
		}
		else if (e.getSource() == archeryRangeLvlButton)
		{
			listener.onUpgrade("ArcheryRange");
		}

		// Recruiting
		if (e.getSource() == archeryRangeRecruitButton)
		{
			listener.onRecruit("Archer");
		}
		else if (e.getSource() == barracksRecruitButton)
		{
			listener.onRecruit("Infantry");
		}
		else if (e.getSource() == stableRecruitButton)
		{
			listener.onRecruit("Cavalry");
		}

		if (e.getSource() == showDefendingArmy)
		{
			listener.onShowDefendingArmy();
		}

	}

	public JButton getBarracksButton()
	{
		return barracksButton;
	}

	public void setBarracksButton(JButton barracksButton)
	{
		this.barracksButton = barracksButton;
	}

	public JButton getBarracksLvlButton()
	{
		return barracksLvlButton;
	}

	public void setBarracksLvlButton(JButton barracksLvlButton)
	{
		this.barracksLvlButton = barracksLvlButton;
	}

	public JButton getArcheryRangeButton()
	{
		return archeryRangeButton;
	}

	public void setArcheryRangeButton(JButton archeryRangeButton)
	{
		this.archeryRangeButton = archeryRangeButton;
	}

	public JButton getArcheryRangeLvlButton()
	{
		return archeryRangeLvlButton;
	}

	public void setArcheryRangeLvlButton(JButton archeryRangeLvlButton)
	{
		this.archeryRangeLvlButton = archeryRangeLvlButton;
	}

	public JButton getStableButton()
	{
		return stableButton;
	}

	public void setStableButton(JButton stableButton)
	{
		this.stableButton = stableButton;
	}

	public JButton getStableLvlButton()
	{
		return stableLvlButton;
	}

	public void setStableLvlButton(JButton stableLvlButton)
	{
		this.stableLvlButton = stableLvlButton;
	}

	public JButton getMarketButton()
	{
		return marketButton;
	}

	public void setMarketButton(JButton marketButton)
	{
		this.marketButton = marketButton;
	}

	public JButton getMarketLvlButton()
	{
		return marketLvlButton;
	}

	public void setMarketLvlButton(JButton marketLvlButton)
	{
		this.marketLvlButton = marketLvlButton;
	}

	public JButton getFarmButton()
	{
		return farmButton;
	}

	public void setFarmButton(JButton farmButton)
	{
		this.farmButton = farmButton;
	}

	public JButton getFarmLvlButton()
	{
		return farmLvlButton;
	}

	public void setFarmLvlButton(JButton farmLvlButton)
	{
		this.farmLvlButton = farmLvlButton;
	}

	public CityViewListener getListener()
	{
		return listener;
	}

	public void setListener(CityViewListener listener)
	{
		this.listener = listener;
	}

	public JButton getBarracksLvlLabel()
	{
		return barracksLvlButton;
	}

	public void setBarracksLvlLabel(JButton barracksLvlLabel)
	{
		this.barracksLvlButton = barracksLvlLabel;
	}

	public JButton getArcheryRangeLvllLabel()
	{
		return archeryRangeLvlButton;
	}

	public void setArcheryRangeLvllLabel(JButton archeryRangeLvllLabel)
	{
		this.archeryRangeLvlButton = archeryRangeLvllLabel;
	}

	public JButton getStableLvlLabel()
	{
		return stableLvlButton;
	}

	public void setStableLvlLabel(JButton stableLvlLabel)
	{
		this.stableLvlButton = stableLvlLabel;
	}

	public JButton getMarketLvlLabel()
	{
		return marketLvlButton;
	}

	public void setMarketLvlLabel(JButton marketLvlLabel)
	{
		this.marketLvlButton = marketLvlLabel;
	}

	public JButton getFarmLvlLabel()
	{
		return farmLvlButton;
	}

	public void setFarmLvlLabel(JButton farmLvlLabel)
	{
		this.farmLvlButton = farmLvlLabel;
	}

}