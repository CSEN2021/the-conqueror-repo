package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import org.hamcrest.core.IsInstanceOf;

import units.*;
import units.*;
import buildings.*;
import engine.*;
import exceptions.*;
import listeners.*;
import views.*;

public class Controller implements HomeViewListener, WorldMapViewListener, InitiateArmyViewListener, CityViewListener
{
	private HomeView homeScreen;
	private WorldMapView worldMapView;
	private CityView cityView;
	private Game theGame;
	private InitiateArmyView initiateArmyView;

	public Controller()
	{
		this.homeScreen = new HomeView();
		homeScreen.setListener(this);
	}

	public static void main(String[] args)
	{
		new Controller();
		// new Game(null, null);
	}

	@Override
	public void onStartGame(String playerName, String playerCity, HomeView startScreen)
	{
		try
		{
			theGame = new Game(playerName, playerCity);
			startScreen.dispose();
			worldMapView = new WorldMapView(theGame);
			worldMapView.setListener(this);
//			worldMapView.setGold(theGame.getPlayer().getTreasury());
//			worldMapView.setPlayerName(playerName);
//			worldMapView.setTurnCount(theGame.getCurrentTurnCount());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// actions performed

	@Override
	public void onOpenCity(JButton openedButton)
	{
		if (cityView == null)
		{
			cityView = new CityView(theGame);
		}
		else
		{
			cityView.setVisible(true);
			cityView.updateStats(theGame);
		}
		cityView.setCurrentCity(theGame.findCity(openedButton.getText()));
		cityView.setListener(this);

	}

	@Override
	public void onEndTurn()
	{
		theGame.endTurn();
		worldMapView.updateStats(theGame);
		cityView.getBarracksLvlLabel().setEnabled(true);
	}

	@Override
	public void onTargetCity(JButton openedButton)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onInitiate()
	{
		String[] cities =
		{ "Cairo", "Sparta", "Rome" };
		initiateArmyView = new InitiateArmyView(theGame, cities);
		initiateArmyView.setListener(this);
		// theGame.getPlayer().initiateArmy(city, unit);
	}

	@Override
	public void onInitiateCity(String cityName)
	{
		theGame.findCity(cityName).getDefendingArmy().getUnits().add(Archer.create("3"));
		ArrayList<Unit> units = theGame.findCity(cityName).getDefendingArmy().getUnits();
		String[] unitsArray = new String[units.size()];
		for (int i = 0; i < units.size(); i++)
		{
			if (units.get(i) instanceof Archer)
			{
				unitsArray[i] = "Archer " + units.get(i).getLevel();
			}
			else if (units.get(i) instanceof Infantry)
			{
				unitsArray[i] = "Infantry " + units.get(i).getLevel();
			}
			else
			{
				unitsArray[i] = "Cavalry " + units.get(i).getLevel();
			}
		}
		initiateArmyView.dispose();
		initiateArmyView = new InitiateArmyView(theGame, unitsArray);
	}

	@Override
	public void onInitiateUnit(String unitToBeInitiated)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onRelocateUnit(JButton openedButton)
	{
		// TODO Auto-generated method stub

	}

	public void onBuild(String stringBuildling)
	{
		// TODO Auto-generated method stub
		String currentCityName = cityView.getCurrentCity().getName();
		try
		{
			theGame.getPlayer().build(stringBuildling, currentCityName);
			switch (stringBuildling)
			{
				case "Farm":
				{
					cityView.getFarmButton().setEnabled(false);
					cityView.getFarmButton().setText("Farm Built");
					cityView.getFarmLvlButton().setText("Level: 1");
					cityView.getFarmLvlButton().setEnabled(true);
					break;
				}
				case "Market":
				{
					cityView.getMarketButton().setEnabled(false);
					cityView.getMarketButton().setText("Market Built");
					cityView.getMarketLvlButton().setText("Level: 1");
					cityView.getMarketLvlButton().setEnabled(true);
					break;
				}
				case "ArcheryRange":
				{
					cityView.getArcheryRangeButton().setEnabled(false);
					cityView.getArcheryRangeButton().setText("ArcheryRange Built");
					cityView.getArcheryRangeLvlButton().setEnabled(true);
					cityView.getArcheryRangeLvlButton().setText("Level: 1");
					break;
				}
				case "Barracks":
				{
					cityView.getBarracksButton().setEnabled(false);
					cityView.getBarracksButton().setText("Barracks Built");
					cityView.getBarracksLvlButton().setText("Level: 1");
					cityView.getBarracksLvlButton().setEnabled(true);
					break;
				}
				case "Stable":
				{
					cityView.getStableButton().setEnabled(false);	
					cityView.getStableButton().setText("Stable Built");
					cityView.getStableLvlButton().setText("Level: 1");
					cityView.getStableLvlButton().setEnabled(true);
					break;
				}
			}
		}
		catch (NotEnoughGoldException e)
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "You don't have enough gold!", "Warning",
					JOptionPane.ERROR_MESSAGE);
		}
		cityView.updateStats(theGame);
		worldMapView.updateStats(theGame);
	}

	public void onUpgrade(String stringBuildling)
	{
		// TODO Auto-generated method stub
		String currentCityName = cityView.getCurrentCity().getName();
		ArrayList<EconomicBuilding> economicBuildings = theGame.findCity(currentCityName).getEconomicalBuildings();
		ArrayList<MilitaryBuilding> militaryBuildings = theGame.findCity(currentCityName).getMilitaryBuildings();
		
		try
		{
			int currentLevel;
			switch (stringBuildling)
			{
				case "Farm":
				{
					for (int i = 0; i < economicBuildings.size(); i++)
					{
						if (economicBuildings.get(i) instanceof Farm)
						{
							theGame.getPlayer().upgradeBuilding(economicBuildings.get(i));
							currentLevel = Integer.parseInt(cityView.getFarmLvlButton().getText().charAt(7) + "") + 1;
							cityView.getFarmLvlButton().setText("Level: " + currentLevel);
							cityView.getFarmButton().setEnabled(false);
							break;
						}
					}
					break;
				}
				case "Market":
				{
					for (int i = 0; i < economicBuildings.size(); i++)
					{
						if (economicBuildings.get(i) instanceof Market)
						{
							theGame.getPlayer().upgradeBuilding(economicBuildings.get(i));
							currentLevel = Integer.parseInt(cityView.getMarketLvlLabel().getText().charAt(7) + "") + 1;
							cityView.getMarketLvlButton().setText("Level: " + currentLevel);
							cityView.getMarketButton().setEnabled(false);
							break;
						}
					}
					break;
				}
				case "ArcheryRange":
				{
					for (int i = 0; i < militaryBuildings.size(); i++)
					{
						if (militaryBuildings.get(i) instanceof ArcheryRange)
						{
							theGame.getPlayer().upgradeBuilding(militaryBuildings.get(i));
							currentLevel = Integer.parseInt(cityView.getArcheryRangeLvlButton().getText().charAt(7) + "") + 1;
							cityView.getArcheryRangeLvlButton().setText("Level: " + currentLevel);
							cityView.getArcheryRangeLvlButton().setEnabled(false);
							break;
						}
					}
					break;

				}
				case "Barracks":
				{
					for (int i = 0; i < militaryBuildings.size(); i++)
					{
						if (militaryBuildings.get(i) instanceof Barracks)
						{
							theGame.getPlayer().upgradeBuilding(militaryBuildings.get(i));;
							currentLevel = Integer.parseInt(cityView.getBarracksLvlButton().getText().charAt(7) + "") + 1;
							cityView.getBarracksLvlButton().setText("Level: " + currentLevel );
							cityView.getBarracksButton().setEnabled(false);
							break;
						}
					}
					break;

				}
				case "Stable":
				{
					for (int i = 0; i < militaryBuildings.size(); i++)
					{
						if (militaryBuildings.get(i) instanceof Stable)
						{
							theGame.getPlayer().upgradeBuilding(militaryBuildings.get(i));;
							currentLevel = Integer.parseInt(cityView.getStableLvlButton().getText().charAt(7) + "") + 1;
							cityView.getStableLvlButton().setText("Level: " + currentLevel);
							cityView.getStableButton().setEnabled(false);
							break;
						}
					}
					break;
				}
				
			}
			cityView.updateStats(theGame);
			worldMapView.updateStats(theGame);

		}
		catch (MaxLevelException maxLevelException)
		{
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "You have reached the max level. You can't upgrade.", "Warning",
					JOptionPane.ERROR_MESSAGE);
		}
		catch(BuildingInCoolDownException bInCoolDownException)
		{
			JOptionPane.showMessageDialog(null, "Building is in cool down.", "Warning",
					JOptionPane.ERROR_MESSAGE);
		}
		catch (NotEnoughGoldException notEnoughGoldException) 
		{
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "You don't have enough gold", "Warning",
					JOptionPane.ERROR_MESSAGE);
		}
		cityView.updateStats(theGame);
		worldMapView.updateStats(theGame);
	}

	@Override
	public void onRecruit()
	{
		// TODO Auto-generated method stub

	}

}
