package controller;

import units.*;
import java.io.IOException;

import javax.swing.JButton;

import buildings.Building;
import buildings.Farm;
import engine.City;
import engine.Game;
import engine.Player;
import exceptions.NotEnoughGoldException;
import listeners.*;
import views.*;

public class Controller implements HomeViewListener, WorldMapViewListener, CityViewListener
{
	HomeView homeScreen;
	WorldMapView worldMapView;
	CityView cityView;
	Game theGame;

	public Controller()
	{
		this.homeScreen = new HomeView();
		homeScreen.setListener(this);
	}
	
	public static void main(String[] args)
	{
		new Controller();
		//new Game(null, null);
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
		cityView = new CityView(theGame);
		cityView.setCurrentCity(theGame.findCity(openedButton.getText()));
		cityView.setListener(this);
		System.out.println(cityView.getCurrentCity().getName());
	}

	@Override
	public void onEndTurn()
	{
		theGame.endTurn();
		worldMapView.updateStats(theGame);
	}

	@Override
	public void onTargetCity(JButton openedButton)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInitiate()
	{
		InitiateArmyView initiateArmyView = new InitiateArmyView();
		//theGame.getPlayer().initiateArmy(city, unit);
	}

	@Override
	public void onRelocateUnit(JButton openedButton)
	{
		// TODO Auto-generated method stub
		
	}

	
	public void onBuild(String s) {
		// TODO Auto-generated method stub
		String currentCityName = cityView.getCurrentCity().getName();
		try {
			theGame.getPlayer().build(s, currentCityName);
		} catch (NotEnoughGoldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		
	}

	@Override
	public void onUpgrade() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRecruit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSelectBuilding() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSelectLevel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSelectUnit() {
		// TODO Auto-generated method stub
		
	}



	
}


