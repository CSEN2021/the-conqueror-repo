package controller;

import java.io.IOException;

import engine.Game;
import engine.Player;
import listeners.HomeViewListener;
import views.HomeView;
import views.WorldMapView;

public class Controller implements HomeViewListener
{
	HomeView homeScreen;
	WorldMapView worldMapView;
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
//			worldMapView.setGold(theGame.getPlayer().getTreasury());
//			worldMapView.setPlayerName(playerName);
//			worldMapView.setTurnCount(theGame.getCurrentTurnCount());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
