package views;

import java.awt.Label;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class HomeView extends JFrame{

	public HomeView()
	{
		JFrame window = new JFrame();
		Label l = new Label("Welcome");
		window.add(l);
		window.setTitle("Homepage");
		window.setSize(1280,720);
		window.setResizable(false);
		window.setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new HomeView();
	}
}
