package engine;

import java.util.*;
import units.*;
import java.io.IOException;
import buildings.*;
import exceptions.*;

public class Game
{

	// instance variables
	private Player player;
	private ArrayList<City> availableCities = new ArrayList<>(); // read only
	private ArrayList<Distance> distances = new ArrayList<>(); // read only
	final private int maxTurnCount = 30; // read only
	private int currentTurnCount = 1;

	// constructor
	public Game(String playerName, String playerCity) throws IOException
	{

		this.player = new Player(playerName);

		// fill the lists
		loadCitiesAndDistances();

		// initialize all defending armies
		for (int i = 0; i < availableCities.size(); i++)
		{
			// if not player city
			if (!availableCities.get(i).getName().equals(playerCity))
			{
				loadArmy(availableCities.get(i).getName(),
						availableCities.get(i).getName().toLowerCase() + "_army.csv");
			}
			// add to playerCity controlled cities and its army should be null since no one
			// will attack it
			else
			{
				this.player.getControlledCities().add(availableCities.get(i));
			}
		}
	}

	// methods

	// initializes the defending army of the defending cities
	public void loadArmy(String cityName, String path) throws IOException
	{
		for (int i = 0; i < availableCities.size(); i++)
		{
			if (availableCities.get(i).getName() == cityName)
				ReadCSV.readFile(availableCities.get(i), path);
		}
	}

	private void loadCitiesAndDistances() throws IOException
	{
		ReadCSV.readFile("distances.csv", availableCities, distances);
	}

	public void targetCity(Army army, String targetName)
	{
		// check if already on the way somewhere
		if (army.getCurrentStatus().equals(Status.MARCHING))
			return;
		String start = army.getCurrentLocation();
		for (int i = 0; i < distances.size(); i++)
		{
			if (distances.get(i).getFrom().equals(start) && distances.get(i).getTo().equals(targetName))
				army.setDistancetoTarget(distances.get(i).getDistance());
			if (distances.get(i).getFrom().equals(targetName) && distances.get(i).getTo().equals(start))
				army.setDistancetoTarget(distances.get(i).getDistance());
		}
		army.setTarget(targetName);
		army.setCurrentStatus(Status.MARCHING);
	}

	public void endTurn()
	{
		currentTurnCount++;
		for (int i = 0; i < player.getControlledCities().size(); i++)
		{
			for (int j = 0; j < player.getControlledCities().get(i).getEconomicalBuildings().size(); j++)
			{
				player.getControlledCities().get(i).getEconomicalBuildings().get(j).setCoolDown(false);
				if (player.getControlledCities().get(i).getEconomicalBuildings().get(j) instanceof Market)
					player.setTreasury(player.getTreasury()
							+ player.getControlledCities().get(i).getEconomicalBuildings().get(j).harvest());
				else
					player.setFood(player.getFood()
							+ player.getControlledCities().get(i).getEconomicalBuildings().get(j).harvest());
			}
			for (int j = 0; j < player.getControlledCities().get(i).getMilitaryBuildings().size(); j++)
			{
				player.getControlledCities().get(i).getMilitaryBuildings().get(j).setCoolDown(false);
				player.getControlledCities().get(i).getMilitaryBuildings().get(j).setCurrentRecruit(0);
			}
		}
		// calculate food and remove it
		for (int i = 0; i < player.getControlledArmies().size(); i++)
		{
			player.setFood(player.getFood() - player.getControlledArmies().get(i).foodNeeded());
		}
		if (player.getFood() <= 0)
		{
			player.setFood(0);
		}
		// stuff related to armies
		for (int i = 0; i < player.getControlledArmies().size(); i++)
		{
			// for every unit
			for (int j = 0; j < player.getControlledArmies().get(i).getUnits().size(); j++)
			{
				if (player.getFood() == 0)
					player.getControlledArmies().get(i).getUnits().get(j).setCurrentSoldierCount((int) (0.9
							* player.getControlledArmies().get(i).getUnits().get(j).getCurrentSoldierCount()));
			}
			if (!player.getControlledArmies().get(i).getTarget().equals(""))
			{
				player.getControlledArmies().get(i)
						.setDistancetoTarget(player.getControlledArmies().get(i).getDistancetoTarget() - 1);
				if (player.getControlledArmies().get(i).getDistancetoTarget() == 0)
				{
					player.getControlledArmies().get(i)
							.setCurrentLocation(player.getControlledArmies().get(i).getTarget());
					player.getControlledArmies().get(i).setCurrentStatus(Status.IDLE);
					player.getControlledArmies().get(i).setDistancetoTarget(-1);
					player.getControlledArmies().get(i).setTarget("");
				}
			}
		}
		// stuff related to enemy cities
		for (int i = 0; i < availableCities.size(); i++)
		{
			if (availableCities.get(i).isUnderSiege() == true)
			{
				availableCities.get(i).setTurnsUnderSiege(availableCities.get(i).getTurnsUnderSiege() + 1);
				for (int j = 0; j < availableCities.get(i).getDefendingArmy().getUnits().size(); j++)
				{
					availableCities.get(i).getDefendingArmy().getUnits().get(j).setCurrentSoldierCount((int) (0.9
							* availableCities.get(i).getDefendingArmy().getUnits().get(j).getCurrentSoldierCount()));
					if (availableCities.get(i).getDefendingArmy().getUnits().get(j).getCurrentSoldierCount() <= 0)
					{
						availableCities.get(i).getDefendingArmy().getUnits().remove(j);
					}
				}
				if (availableCities.get(i).getDefendingArmy().getUnits().size() == 0)
				{
					Army TheArmy = null;
					for (int j = 0; j < player.getControlledArmies().size(); j++)
					{
						if (player.getControlledArmies().get(j).getCurrentLocation()
								.equals(availableCities.get(i).getName()))
							TheArmy = player.getControlledArmies().get(j);
					}
					occupy(TheArmy, availableCities.get(i).getName());
				}
			}
		}

	}

	public void occupy(Army a, String cityName)
	{
		for (int i = 0; i < availableCities.size(); i++)
		{
			if (availableCities.get(i).getName().equals(cityName))
			{
				player.getControlledCities().add(availableCities.get(i));
				availableCities.get(i).setDefendingArmy(a);
				availableCities.get(i).setTurnsUnderSiege(-1);
				availableCities.get(i).setUnderSiege(false);
				a.setCurrentStatus(Status.IDLE);
			}
		}
	}

	public void autoResolve(Army attacker, Army defender) throws FriendlyFireException
	{
		if (player.getControlledArmies().contains(attacker) && player.getControlledArmies().contains(defender))
			throw new FriendlyFireException();
		boolean attackerTurn = true;
		while (attacker.getUnits().size() != 0 && defender.getUnits().size() != 0)
		{
			if (attackerTurn)
			{
				attacker.getUnits().get((int) Math.random() * attacker.getUnits().size())
						.attack(defender.getUnits().get((int) Math.random() * defender.getUnits().size()));
			}
			else
			{
				defender.getUnits().get((int) Math.random() * defender.getUnits().size())
						.attack(attacker.getUnits().get((int) Math.random() * attacker.getUnits().size()));
			}
			attackerTurn = !attackerTurn;
			// should we endTurn() ?
		}
	}

	public boolean isGameOver()
	{
		if (player.getControlledCities().size() == availableCities.size() || getCurrentTurnCount() > getMaxTurnCount())
			return true;
		return false;
	}

	// getters and setters
	public Player getPlayer()
	{
		return player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}

	public int getCurrentTurnCount()
	{
		return currentTurnCount;
	}

	public void setCurrentTurnCount(int currentTurnCount)
	{
		this.currentTurnCount = currentTurnCount;
	}

	public ArrayList<City> getAvailableCities()
	{
		return availableCities;
	}

	public ArrayList<Distance> getDistances()
	{
		return distances;
	}

	public int getMaxTurnCount()
	{
		return maxTurnCount;
	}

}
