package controller;

import views.HomeView;

public class Controller
{
	HomeView homeScreen;

	public Controller()
	{
		this.homeScreen = new HomeView();
	}
	public static void main(String[] args)
	{
		new Controller();
	}
}
