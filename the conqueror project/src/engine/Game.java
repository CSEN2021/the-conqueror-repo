package engine;

import java.util.*;
import buildings.*;
import units.*;
import java.io.IOException;    //new import for me

public class Game {
	
	//instance variables
	private Player player;
	private ArrayList<City> availableCities;	//read only
	private ArrayList<Distance> distances;	//read only
	final private int maxTurnCount = 30;	//read only
	private int currentTurnCount = 1;
	
	//constructor
	public Game(String playerName,String playerCity) throws IOException{
		this.player = new Player(playerName);
		availableCities.add(new City(playerCity));
		
		//incomplete, check milestone description
		//"Carefully think about how will you initialize the army of the defending cities"
	}

	//getters and setters
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
