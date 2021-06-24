package controller;

import units.*;
import java.io.IOException;

import javax.swing.JButton;

import engine.City;
import engine.Game;
import engine.Player;
import listeners.*;
import views.*;

public class Controller implements HomeViewListener, WorldMapViewListener, InitiateArmyViewListener
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
		initiateArmyView = new InitiateArmyView(theGame);
		initiateArmyView.setListener(this);
		//theGame.getPlayer().initiateArmy(city, unit);
	}

	@Override
	public void onRelocateUnit(JButton openedButton)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInitiateCity(String cityName)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInitiateUnit()
	{
		// TODO Auto-generated method stub
		
	}
}
