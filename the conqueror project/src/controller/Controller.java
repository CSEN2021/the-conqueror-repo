package controller;

import engine.Game;
import engine.Player;
import listeners.HomeViewListener;
import views.HomeView;

public class Controller implements HomeViewListener
{
	HomeView homeScreen;
	Player thePlayer;

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
	public void onStartGame(String playerName)
	{
		thePlayer = new Player(playerName);
		System.out.println(playerName);
	}
}
