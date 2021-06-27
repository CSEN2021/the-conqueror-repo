package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.*;
import javax.swing.*;

import units.*;
import buildings.*;
import engine.*;
import exceptions.*;
import listeners.*;
import views.*;

public class Controller implements StartScreenListener, WorldMapViewListener, InitiateArmyViewListener,
		CityViewListener, RelocateUnitListener, ShowArmyViewListener, ShowAllArmiesViewListener,
		TargetACityViewListener, BattleViewListener
{
	// Main Method
	public static void main(String[] args)
	{
		new Controller();
	}

	// instance variables
	private StartScreen startScreen;
	private WorldMapView worldMapView;
	private CityView cityView;
	private Game theGame;
	private InitiateArmyView initiateArmyView;
	private RelocateUnitView relocateUnitView;
	private City armyInitiationCity;
	private String targetedCity;
	private ShowAllArmiesView showAllArmiesView;
	private ShowArmyView showArmyView;
	private TargetACityView targetACityView;
	private BattleView battleView;

	private ArrayList<Army> allArmies;
	private Army armyRelocateFrom;
	private Unit unitRelocate;
	private Army armyRelocateTo;

	// Constructor
	public Controller()
	{
		this.startScreen = new StartScreen();
		startScreen.setListener(this);
	}

	// StartScreen Listener
	@Override
	public void onStartGame(String playerName, String playerCity, StartScreen startScreen)
	{
		try
		{
			theGame = new Game(playerName, playerCity);

			startScreen.dispose();
			worldMapView = new WorldMapView(theGame);
			worldMapView.setListener(this);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// WorldMapView Listener

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

		if (cityView.getCurrentCity().getName().equals("Cairo"))
		{
			cityView.getCityIcon().setIcon(new ImageIcon("resources/Cairo 2.jpg"));
		}
		else if (cityView.getCurrentCity().getName().equals("Rome"))
		{
			cityView.getCityIcon().setIcon(new ImageIcon("resources/Rome 2.jpg"));
		}
		else
		{
			cityView.getCityIcon().setIcon(new ImageIcon("resources/SpartaLogo.png"));
		}
		/*
		 * if (cityView.getCurrentCity().equals("Cairo")) {
		 * cityView.setUpButton2(cityView.getArcheryRangeRecruitButton(),
		 * "CairoArcher.jpg"); } else if (cityView.getCurrentCity().equals("Rome")) {
		 * cityView.setUpButton2(cityView.getArcheryRangeRecruitButton(),
		 * "RomeArcher.jpg"); } else {
		 * cityView.setUpButton2(cityView.getArcheryRangeRecruitButton(),
		 * "RomeArcher.jpg"); }
		 */
		cityView.getCityIcon().setText("TEST");
		cityView.drawDefendingArmy();
		cityView.setListener(this);
	}

	@Override
	public void onEndTurn()
	{
		for(int i = 0; i < theGame.getAvailableCities().size(); i++)
		{
			if(theGame.getAvailableCities().get(i).getTurnsUnderSiege() == 3 && battleView == null)
			{
				onEnterBattle(theGame.getAvailableCities().get(i).getName());
			}
		}
		
		if(battleView != null)
		{
			JOptionPane.showMessageDialog(null, "Finish your battle before ending turn !", "Finish the Battle",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		else
		{
			if(worldMapView.getBeseigeCairoButton().isEnabled() && !theGame.findCity("Cairo").isUnderSiege())
			{
				JOptionPane.showMessageDialog(null, "Enter Battle or Besige the Targeted Cities !", "Finish the Battle",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(worldMapView.getBeseigeCairoButton().isEnabled() && !theGame.findCity("Cairo").isUnderSiege())
			{
				JOptionPane.showMessageDialog(null, "Enter Battle or Besige the Targeted Cities !", "Finish the Battle",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(worldMapView.getBeseigeCairoButton().isEnabled() && !theGame.findCity("Cairo").isUnderSiege())
			{
				JOptionPane.showMessageDialog(null, "Enter Battle or Besige the Targeted Cities !", "Finish the Battle",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
		theGame.endTurn();
		if (theGame.isGameOver() == true)
		{
			worldMapView.dispose();
			if (theGame.getPlayer().getControlledCities().size() == theGame.getAvailableCities().size())
			{
				JOptionPane.showMessageDialog(null, "Game Over ! :) , You Won !", "GameOver",
						JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Game Over ! :( , You Lost...", "GameOver",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
		worldMapView.updateStats(theGame);

		if (cityView != null)
		{
			cityView.unlockRecruitButtons();
		}

		for (int i = 0; i < theGame.getPlayer().getControlledArmies().size(); i++)
		{
			if (theGame.getPlayer().getControlledArmies().get(i).getDistancetoTarget() == 0)
			{
				if (theGame.getPlayer().getControlledArmies().get(i).getCurrentLocation().equalsIgnoreCase("Cairo"))
				{
					worldMapView.getEnterBattleCairoButton().setEnabled(true);
					worldMapView.getBeseigeCairoButton().setEnabled(true);
					if (theGame.getPlayer().getControlledCities().contains(theGame.findCity("Cairo")))
					{
						worldMapView.getEnterBattleRomeButton().setEnabled(false);
						worldMapView.getBeseigeCairoButton().setEnabled(false);
					}
				}
				else if (theGame.getPlayer().getControlledArmies().get(i).getCurrentLocation().equalsIgnoreCase("Rome"))
				{
					worldMapView.getEnterBattleRomeButton().setEnabled(true);
					worldMapView.getBeseigeRomeButton().setEnabled(true);
					if (theGame.getPlayer().getControlledCities().contains(theGame.findCity("Rome")))
					{
						worldMapView.getEnterBattleRomeButton().setEnabled(false);
						worldMapView.getBeseigeRomeButton().setEnabled(false);
					}
				}
				else
				{
					worldMapView.getEnterBattleSpartaButton().setEnabled(true);
					worldMapView.getBeseigeSpartaButton().setEnabled(true);
					if (theGame.getPlayer().getControlledCities().contains(theGame.findCity("Sparta")))
					{
						worldMapView.getBeseigeSpartaButton().setEnabled(false);
						worldMapView.getEnterBattleRomeButton().setEnabled(false);
					}
				}
			}
		}
		worldMapView.updateArmiesPanel(theGame);
	}

	@Override
	public void onShowAllArmies()
	{
		showAllArmiesView = new ShowAllArmiesView(theGame);
		showAllArmiesView.setListener(this);
	}

	@Override
	public void onEnterBattle(String cityGettingAttacked)
	{
		for (int i = 0; i < theGame.getPlayer().getControlledArmies().size(); i++)
		{
			if (theGame.getPlayer().getControlledArmies().get(i).getCurrentLocation().equals(cityGettingAttacked))
			{
				battleView = new BattleView(theGame, theGame.getPlayer().getControlledArmies().get(i),
						theGame.findCity(cityGettingAttacked));
			}
		}
		worldMapView.updateArmiesPanel(theGame);
		battleView.setListener(this);

	}

	@Override
	public void onBeseige(String besiegedCity)
	{
		for(int i =0 ;i <theGame.getPlayer().getControlledArmies().size();i++)
		{
			if(theGame.getPlayer().getControlledArmies().get(i).getCurrentLocation().equalsIgnoreCase(besiegedCity))
			{
				try
				{
					theGame.getPlayer().laySiege(theGame.getPlayer().getControlledArmies().get(i), theGame.findCity(besiegedCity));
					worldMapView.updateArmiesPanel(theGame);
				}
				catch (TargetNotReachedException e)
				{
					JOptionPane.showMessageDialog(null, "Can't besiege city before reaching it", "Warning", JOptionPane.ERROR_MESSAGE);
				}
				catch (FriendlyCityException e)
				{
					JOptionPane.showMessageDialog(null, "Can't besiege friendly city", "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	@Override
	public void onTargetCity()
	{
		ArrayList<City> availableCities = theGame.getAvailableCities();
		String[] targetableCities = new String[availableCities.size()
				- theGame.getPlayer().getControlledCities().size()];
		for (int i = 0; i < availableCities.size(); i++)
		{
			if (!theGame.getPlayer().getControlledCities().contains(availableCities.get(i)))
			{
				targetableCities[i] = availableCities.get(i).getName();
			}
		}
		targetACityView = new TargetACityView(theGame, targetableCities);
		targetACityView.setListener(this);
		targetACityView.getChooseLabel().setText("Choose a City to Target");
		targetACityView.setTitle("Target A City");
	}

	@Override
	public void onInitiate()
	{
		String[] cities = new String[theGame.getPlayer().getControlledCities().size()];
		for (int i = 0; i < theGame.getPlayer().getControlledCities().size(); i++)
		{
			cities[i] = theGame.getPlayer().getControlledCities().get(i).getName();
		}
		initiateArmyView = new InitiateArmyView(theGame, cities);
		initiateArmyView.setListener(this);
	}

	@Override
	public void onRelocateUnit()
	{
		allArmies = new ArrayList<>();
		for (int i = 0; i < theGame.getPlayer().getControlledCities().size(); i++)
		{
			allArmies.add(theGame.getPlayer().getControlledCities().get(i).getDefendingArmy());
		}
		allArmies.addAll(theGame.getPlayer().getControlledArmies());

		int totalSize = theGame.getPlayer().getControlledCities().size()
				+ theGame.getPlayer().getControlledArmies().size();
		String[] armiesToRelocate = new String[totalSize];
		for (int i = 0; i < theGame.getPlayer().getControlledCities().size(); i++)
		{
			armiesToRelocate[i] = "Defending Army of " + theGame.getPlayer().getControlledCities().get(i).getName();
		}
		for (int i = 0; i < theGame.getPlayer().getControlledArmies().size(); i++)
		{
			armiesToRelocate[i + theGame.getPlayer().getControlledCities().size()] = "Army " + (i + 1);
		}

		relocateUnitView = new RelocateUnitView(theGame, armiesToRelocate);
		relocateUnitView.setListener(this);
		relocateUnitView.getChooseLabel().setText("Choose an Army to relocate from :");
	}

	// InitiateArmyView listeners
	@Override
	public void onInitiateCity(String cityName)
	{
		armyInitiationCity = theGame.findCity(cityName);
		ArrayList<Unit> units = armyInitiationCity.getDefendingArmy().getUnits();
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
		initiateArmyView.getChooseLabel().setText("Choose a Unit to initiate Army with:");
		initiateArmyView.setIsChoosingUnit(true);
		initiateArmyView.setListener(this);
	}

	@Override
	public void onInitiateUnit(String unitToBeInitiated)
	{
		Unit actualUnitToInitiate;
		String nameSearch;
		int levelSearch;

		if (unitToBeInitiated.length() == 8) // archer
		{
			nameSearch = unitToBeInitiated.substring(0, 6);
			levelSearch = Integer.parseInt("" + unitToBeInitiated.charAt(7));
		}
		else if (unitToBeInitiated.length() == 10) // infantry
		{
			nameSearch = unitToBeInitiated.substring(0, 8);
			levelSearch = Integer.parseInt("" + unitToBeInitiated.charAt(9));
		}
		else // cavalry
		{
			nameSearch = unitToBeInitiated.substring(0, 7);
			levelSearch = Integer.parseInt("" + unitToBeInitiated.charAt(8));
		}
		actualUnitToInitiate = armyInitiationCity.getDefendingArmy().findUnit(nameSearch, levelSearch);
		theGame.getPlayer().initiateArmy(armyInitiationCity, actualUnitToInitiate);
		worldMapView.updateArmiesPanel(theGame);
		initiateArmyView.dispose();
	}

	// CityView listeners
	public void onBuild(String stringBuildling)
	{
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
					cityView.getFarmLvlButton().setText("Level: 1 Cost: 500");
					cityView.getFarmLvlButton().setEnabled(true);
					break;
				}
				case "Market":
				{
					cityView.getMarketButton().setEnabled(false);
					cityView.getMarketButton().setText("Market Built");
					cityView.getMarketLvlButton().setText("Level: 1 Cost: 700");
					cityView.getMarketLvlButton().setEnabled(true);
					break;
				}
				case "ArcheryRange":
				{
					cityView.getArcheryRangeButton().setEnabled(false);
					cityView.getArcheryRangeButton().setText("ArcheryRange Built");
					cityView.getArcheryRangeLvlButton().setEnabled(true);
					cityView.getArcheryRangeLvlButton().setText("Level: 1 Cost: 1500");

					cityView.getArcheryRangeRecruitButton().setEnabled(true);
					cityView.getArcheryRangeRecruitButton().setText("Archer, Cost: 400");
					break;
				}
				case "Barracks":
				{
					cityView.getBarracksButton().setEnabled(false);
					cityView.getBarracksButton().setText("Barracks Built");
					cityView.getBarracksLvlButton().setText("Level: 1 Cost: 1000");
					cityView.getBarracksLvlButton().setEnabled(true);

					cityView.getBarracksRecruitButton().setEnabled(true);
					cityView.getBarracksRecruitButton().setText("Infantry, Cost: 500");
					break;
				}
				case "Stable":
				{
					cityView.getStableButton().setEnabled(false);
					cityView.getStableButton().setText("Stable Built");
					cityView.getStableLvlButton().setText("Level: 1 Cost: 1500");
					cityView.getStableLvlButton().setEnabled(true);

					cityView.getStableRecruitButton().setEnabled(true);
					cityView.getStableRecruitButton().setText("Calvary, Cost: 600");
					break;
				}
			}
		}
		catch (NotEnoughGoldException e)
		{
			JOptionPane.showMessageDialog(null, "You don't have enough gold!", "Warning", JOptionPane.ERROR_MESSAGE);
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
							cityView.getFarmLvlButton().setText("Level: " + currentLevel + " Cost: 500");
							if (currentLevel == 3)
							{
								cityView.getFarmLvlButton().setText("Max Level");
							}
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
							cityView.getMarketLvlButton().setText("Level: " + currentLevel + " Cost: 700");
							if (currentLevel == 3)
							{
								cityView.getMarketLvlButton().setText("Max Level");
							}
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
							currentLevel = Integer
									.parseInt(cityView.getArcheryRangeLvlButton().getText().charAt(7) + "") + 1;
							cityView.getArcheryRangeLvlButton().setText("Level: " + currentLevel + " Cost: 1500");
							cityView.getArcheryRangeRecruitButton().setText("Archer, Cost: "
									+ (((ArcheryRange) (theGame.findCity(currentCityName).findBuilding("ArcheryRange")))
											.getRecruitmentCost()));
							if (currentLevel == 3)
							{
								cityView.getArcheryRangeLvlButton().setText("Max Level");
							}
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
							theGame.getPlayer().upgradeBuilding(militaryBuildings.get(i));
							;
							currentLevel = Integer.parseInt(cityView.getBarracksLvlButton().getText().charAt(7) + "")
									+ 1;
							cityView.getBarracksLvlButton().setText("Level: " + currentLevel + " Cost: 1000");
							cityView.getBarracksRecruitButton()
									.setText("Infantry, Cost: "
											+ (((Barracks) (theGame.findCity(currentCityName).findBuilding("Barracks")))
													.getRecruitmentCost()));

							if (currentLevel == 3)
							{
								cityView.getBarracksLvlButton().setText("Max Level");
							}
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
							theGame.getPlayer().upgradeBuilding(militaryBuildings.get(i));
							;
							currentLevel = Integer.parseInt(cityView.getStableLvlButton().getText().charAt(7) + "") + 1;
							cityView.getStableLvlButton().setText("Level: " + currentLevel + " Cost: 1500");
							cityView.getStableRecruitButton()
									.setText("Calvary, Cost: "
											+ (((Stable) (theGame.findCity(currentCityName).findBuilding("Stable")))
													.getRecruitmentCost()));
							if (currentLevel == 3)
							{
								cityView.getStableLvlButton().setText("Max Level");
							}
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
			switch (stringBuildling)
			{
				case "Farm":
				{
					cityView.getFarmLvlButton().setEnabled(false);
					break;
				}
				case "Market":
				{
					cityView.getMarketLvlButton().setEnabled(false);
					break;
				}
				case "Stable":
				{
					cityView.getStableLvlButton().setEnabled(false);
					break;
				}
				case "Barracks":
				{
					cityView.getBarracksLvlButton().setEnabled(false);
					break;
				}
				case "ArcheryRange":
				{
					cityView.getArcheryRangeLvlButton().setEnabled(false);
					break;
				}
			}

			JOptionPane.showMessageDialog(null, "You have reached the max level. You can't upgrade.", "Warning",
					JOptionPane.ERROR_MESSAGE);
		}
		catch (BuildingInCoolDownException bInCoolDownException)
		{
			JOptionPane.showMessageDialog(null, "Building is in cool down.", "Warning", JOptionPane.ERROR_MESSAGE);
		}
		catch (NotEnoughGoldException notEnoughGoldException)
		{
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "You don't have enough gold.", "Warning", JOptionPane.ERROR_MESSAGE);
		}
		cityView.updateStats(theGame);
		worldMapView.updateStats(theGame);
	}

	public void onRecruit(String unit)
	{
		try
		{
			theGame.getPlayer().recruitUnit(unit, cityView.getCurrentCity().getName());
			cityView.drawDefendingArmy();
			worldMapView.updateStats(theGame);
			cityView.updateStats(theGame);
			cityView.repaint();
		}
		catch (BuildingInCoolDownException buildingInCoolDownException)
		{
			JOptionPane.showMessageDialog(null, "Building is in cool down.", "Warning", JOptionPane.ERROR_MESSAGE);
		}
		catch (MaxRecruitedException maxRecruitedException)
		{
			switch (unit)
			{
				case "Archer":
				{
					cityView.getArcheryRangeRecruitButton().setEnabled(false);
					break;
				}
				case "Infantry":
				{
					cityView.getBarracksRecruitButton().setEnabled(false);
					break;
				}
				case "Cavalry":
				{
					cityView.getStableRecruitButton().setEnabled(false);
					break;
				}
			}

			JOptionPane.showMessageDialog(null, "You have reached max recruited count for this turn.", "Warning",
					JOptionPane.ERROR_MESSAGE);
		}
		catch (NotEnoughGoldException notEnoughGoldException)
		{

			JOptionPane.showMessageDialog(null, "You don't have enough gold.", "Warning", JOptionPane.ERROR_MESSAGE);
		}

	}

	@Override

	public void onShowDefendingArmy()
	{

		showArmyView = new ShowArmyView(theGame, cityView.getCurrentCity());

	}

	// RelocateUnitView listeners
	@Override
	public void onRelocateArmyFrom(int armyFrom)
	{
		armyRelocateFrom = allArmies.get(armyFrom);
		String[] unitsToRelocateFrom = new String[armyRelocateFrom.getUnits().size()];
		for (int i = 0; i < unitsToRelocateFrom.length; i++)
		{
			unitsToRelocateFrom[i] = armyRelocateFrom.getUnits().get(i).toString();
		}
		relocateUnitView.dispose();
		relocateUnitView = new RelocateUnitView(theGame, unitsToRelocateFrom);
		relocateUnitView.setStageOfRelocation(1);
		relocateUnitView.setListener(this);
		relocateUnitView.getChooseLabel().setText("Choose a Unit to relocate :");
	}

	@Override
	public void onRelocateUnitChosen(int unitToBeInitiated)
	{
		unitRelocate = armyRelocateFrom.getUnits().get(unitToBeInitiated);

		int totalSize = theGame.getPlayer().getControlledCities().size()
				+ theGame.getPlayer().getControlledArmies().size();
		String[] armiesToRelocate = new String[totalSize];
		for (int i = 0; i < theGame.getPlayer().getControlledCities().size(); i++)
		{
			armiesToRelocate[i] = "Defending Army of " + theGame.getPlayer().getControlledCities().get(i).getName();
		}
		for (int i = 0; i < theGame.getPlayer().getControlledArmies().size(); i++)
		{
			armiesToRelocate[i + theGame.getPlayer().getControlledCities().size()] = "Army " + (i + 1);
		}

		relocateUnitView.dispose();
		relocateUnitView = new RelocateUnitView(theGame, armiesToRelocate);
		relocateUnitView.setStageOfRelocation(2);
		relocateUnitView.setListener(this);
		relocateUnitView.getChooseLabel().setText("Choose an Army to Send the Unit to :");
	}

	@Override
	public void onRelocateArmyTo(int armyTo)
	{
		armyRelocateTo = allArmies.get(armyTo);
		try
		{
			armyRelocateTo.relocateUnit(unitRelocate);
			worldMapView.updateArmiesPanel(theGame);
			relocateUnitView.dispose();
		}
		catch (MaxCapacityException e)
		{
			JOptionPane.showMessageDialog(null, "Controlled Armies can't have more than 10 units", "Warning",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// ShowAllArmiesView Listeners
	@Override
	public void onArmySelected(Army armyToBeViewed)
	{
		showArmyView = new ShowArmyView(theGame, armyToBeViewed);
	}

	@Override
	public void onDefendingArmySelected(City city)
	{

		showArmyView = new ShowArmyView(theGame, city);

	}

	// TargetACityView Listener
	@Override
	public void onCityTargeted(String targetedCity)
	{
		targetACityView.dispose();
		this.targetedCity = targetedCity;
		String[] attackingArmies = new String[theGame.getPlayer().getControlledArmies().size()];
		for (int i = 0; i < theGame.getPlayer().getControlledArmies().size(); i++)
		{
			attackingArmies[i] = "Army " + (i + 1);
		}
		targetACityView = new TargetACityView(theGame, attackingArmies);
		targetACityView.setListener(this);
		targetACityView.setIsChoosingUnit(true);
		targetACityView.getChooseLabel().setText("Choose an Army to March with");
		targetACityView.setTitle("Pick an Army");
	}

	@Override
	public void onArmyTargeting(int marchingArmyIndx)
	{
		Army marchingArmy = theGame.getPlayer().getControlledArmies().get(marchingArmyIndx);
		theGame.targetCity(marchingArmy, targetedCity);
		targetACityView.dispose();
		worldMapView.updateArmiesPanel(theGame);

	}

	// BattleView Listener

	@Override
	public void onAttack()
	{
		try
		{
			int initialSoldierCount;
			int finalSoldierCount;
			Army playerArmy = null;
			Unit attackingUnit = null;
			Unit targetUnit = null;
			JToggleButton attackingUnitButton = null;
			JToggleButton targetUnitButton = null;

			// getting the target city then using it to get the enemy army (defending army
			// of said city)
			String targetCityName = battleView.getEnemyLabel().getText().split(" ")[3];
			City targetCity = theGame.findCity(targetCityName);
			Army enemyArmy = targetCity.getDefendingArmy();

			// getting the player's army
			for (int i = 0; i < theGame.getPlayer().getControlledArmies().size(); i++)
			{
				if (theGame.getPlayer().getControlledArmies().get(i).getCurrentLocation().equals(targetCityName))
				{
					playerArmy = theGame.getPlayer().getControlledArmies().get(i);
				}
			}
			// getting the attacking unit from the player's army
			for (int i = 0; i < battleView.getPlayerUnitsButtons().size(); i++)
			{
				if (battleView.getPlayerUnitsButtons().get(i).isSelected())
				{
					attackingUnit = playerArmy.getUnits().get(i);
					attackingUnitButton = battleView.getPlayerUnitsButtons().get(i);
				}
			}

			// getting the attacking unit from the player's army
			for (int i = 0; i < battleView.getEnemyUnitsButtons().size(); i++)
			{
				if (battleView.getEnemyUnitsButtons().get(i).isSelected())
				{
					targetUnit = enemyArmy.getUnits().get(i);
					targetUnitButton = battleView.getEnemyUnitsButtons().get(i);
				}
			}
			
			initialSoldierCount=targetUnit.getCurrentSoldierCount();
			attackingUnit.attack(targetUnit);
			finalSoldierCount=targetUnit.getCurrentSoldierCount();
			String  attackerUnitText =  attackingUnit.getCurrentSoldierCount() + " lvl " + attackingUnit.getLevel() +" "+ attackingUnit.toString().split(" ")[0];
			String  finalTargetUnitText =  finalSoldierCount + " lvl " + targetUnit.getLevel() +" "+ targetUnit.toString().split(" ")[0];
			String  initialTargetUnitText =  initialSoldierCount + " lvl " + targetUnit.getLevel() +" "+ targetUnit.toString().split(" ")[0];
			targetUnitButton.setText(finalTargetUnitText);
			
			battleView.getBattleLog().append(attackerUnitText + " unit from the player's army attacked "+ initialTargetUnitText + " unit from the target's army. The target's unit lost " + (initialSoldierCount-finalSoldierCount) + " troops !! \n");
			
			if(targetUnit.getCurrentSoldierCount()==0) {
				targetUnitButton.setEnabled(false);
				battleView.getEnemyUnitsButtons().remove(targetUnitButton);
			}
			
			
			// COUNTERATTACK
			Random rand = new Random();
			int enemyRandomIndex = rand.nextInt(enemyArmy.getUnits().size());
		    attackingUnit = enemyArmy.getUnits().get(enemyRandomIndex);
		    int playerRandomIndex = rand.nextInt(playerArmy.getUnits().size());
		    targetUnit = playerArmy.getUnits().get(playerRandomIndex);
		    targetUnitButton = battleView.getPlayerUnitsButtons().get(playerRandomIndex);
		    
		    initialSoldierCount=targetUnit.getCurrentSoldierCount();
			attackingUnit.attack(targetUnit);
			finalSoldierCount = targetUnit.getCurrentSoldierCount();
			attackerUnitText =  attackingUnit.getCurrentSoldierCount() + " lvl " + attackingUnit.getLevel() +" "+ attackingUnit.toString().split(" ")[0];
			finalTargetUnitText =  finalSoldierCount + " lvl " + targetUnit.getLevel() +" "+ targetUnit.toString().split(" ")[0];
			initialTargetUnitText =  initialSoldierCount + " lvl " + targetUnit.getLevel() +" "+ targetUnit.toString().split(" ")[0];
			targetUnitButton.setText(finalTargetUnitText);
			
			battleView.getBattleLog().append(attackerUnitText + " unit from the target's army attacked "+ initialTargetUnitText + " unit from the player's army. The player's unit lost " + (initialSoldierCount-finalSoldierCount) + " troops !! \n");
			
			if(targetUnit.getCurrentSoldierCount()==0) {
				targetUnitButton.setEnabled(false);
				battleView.getPlayerUnitsButtons().remove(targetUnitButton);
			}
			
			if (playerArmy.getUnits().size() == 0)
			{
				theGame.getPlayer().getControlledArmies().remove(playerArmy);
				JOptionPane.showMessageDialog(null, "YOU LOST,The target's defending army killed your army...", "better luck next time",JOptionPane.INFORMATION_MESSAGE); 
				battleView.dispose();
			}
			else if(enemyArmy.getUnits().size() == 0)
			{
				theGame.occupy(playerArmy, playerArmy.getCurrentLocation());
				JOptionPane.showMessageDialog(null, "YOU WON! " + targetCityName + " is yours :)!", "congrats!!!!",JOptionPane.INFORMATION_MESSAGE); 
				battleView.dispose();
			}
			
			
			battleView.repaint();
			

		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "the target unit is already dead,choose another target!!", "Warning",
					JOptionPane.ERROR_MESSAGE); 
		}

	}

	@Override
	public void onAutoResolve()
	{
		Army playerArmy = null;
		String targetCityName = battleView.getEnemyLabel().getText().split(" ")[3];
		City targetCity = theGame.findCity(targetCityName);
		Army enemyArmy = targetCity.getDefendingArmy();
		
		for (int i = 0; i < theGame.getPlayer().getControlledArmies().size(); i++)
		{
			if (theGame.getPlayer().getControlledArmies().get(i).getCurrentLocation().equals(targetCityName))
			{
				playerArmy = theGame.getPlayer().getControlledArmies().get(i);
			}
		}
		
		try {
			theGame.autoResolve(playerArmy, enemyArmy);
		} catch (FriendlyFireException e) {
			e.printStackTrace();
		}
		
		if (playerArmy.getUnits().size() == 0)
		{
			JOptionPane.showMessageDialog(null, "YOU LOST,The target's defending army killed your army...", "better luck next time",JOptionPane.INFORMATION_MESSAGE); 
			battleView.dispose();
		}
		else if(enemyArmy.getUnits().size() == 0)
		{
			JOptionPane.showMessageDialog(null, "YOU WON! " + targetCityName + " is yours :)!", "congrats!!!!",JOptionPane.INFORMATION_MESSAGE); 
			battleView.dispose();
		}
		
	}

	@Override
	public void onRetreat()
	{

	}

}
