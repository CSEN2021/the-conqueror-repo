package views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.*;
import listeners.InitiateArmyViewListener;
import units.*;

public class InitiateArmyView extends JFrame implements ActionListener
{
	private String cities[] = {"Cairo","Sparta","Rome"};
	private JComboBox cityComboBox = new JComboBox(cities);
	private JComboBox unitsComboBox;
	private JPanel mainPanel = new JPanel();
	private InitiateArmyViewListener listener;
	private City theCity;
	
	public InitiateArmyView (Game theGame)
	{
		//theGame.findCity(getName());
		add(mainPanel);
		mainPanel.add(cityComboBox);
		setSize(new Dimension(400, 200));
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == cityComboBox)
		{
			listener.onInitiateCity((String)cityComboBox.getSelectedItem());
		}
		
	}
	public void setListener(InitiateArmyViewListener listener)
	{
		this.listener = listener;
	}
}
