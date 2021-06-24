package views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.*;
import units.*;

public class InitiateArmyView extends JFrame implements ActionListener
{
	String cities[] = {"Cairo","Sparta","Rome"};
	JComboBox cityComboBox = new JComboBox(cities);
	JComboBox unitsComboBox;
	JPanel mainPanel = new JPanel();
	public InitiateArmyView ()
	{
		
		add(mainPanel);
		mainPanel.add(cityComboBox);
		setSize(new Dimension(400, 200));
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}
}
