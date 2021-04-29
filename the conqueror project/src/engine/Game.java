package engine;

import java.util.*;
import buildings.*;
import units.*;
import java.io.IOException; // new import for me

public class Game {

	// instance variables
	private Player player;
	private ArrayList<City> availableCities = new ArrayList<>(); // read only
	private ArrayList<Distance> distances = new ArrayList<>(); // read only
	final private int maxTurnCount = 30; // read only
	private int currentTurnCount = 1;

	// constructor
	public Game(String playerName, String playerCity) throws IOException {
		
		//add to playerCity controlled cities and controlled armies
		this.player = new Player(playerName);
		this.player.getControlledCities().add(new City(playerCity));
		this.player.getControlledArmies().add(new Army(playerCity));
		
		// fill the lists
		loadCitiesAndDistances();
		
		//initialize all defending armies
		/*
		for(int i = 0; i < availableCities.size(); i++)
		{
			if(availableCities.get(i).getName() != playerCity)
				loadArmy(availableCities.get(i).getName(), availableCities.get(i).getName() + "_city");
		}*/
	}

	// methods

	// initializes the defending army of the defending cities
	public void loadArmy(String cityName, String path) throws IOException {
		for(int i = 0; i < availableCities.size(); i++)
		{
			if(availableCities.get(i).getName() == cityName)
				ReadCSV.readFile(availableCities.get(i), path + "_army.csv");
		}
	}

	private void loadCitiesAndDistances() throws IOException {
		ReadCSV.readFile("distances.csv", availableCities, distances);
	}

	// getters and setters
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getCurrentTurnCount() {
		return currentTurnCount;
	}

	public void setCurrentTurnCount(int currentTurnCount) {
		this.currentTurnCount = currentTurnCount;
	}

	public ArrayList<City> getAvailableCities() {
		return availableCities;
	}

	public ArrayList<Distance> getDistances() {
		return distances;
	}

	public int getMaxTurnCount() {
		return maxTurnCount;
	}

}
