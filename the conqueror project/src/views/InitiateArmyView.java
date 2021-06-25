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
	
	private JComboBox comboBox;

	private JPanel mainPanel = new JPanel();
	private InitiateArmyViewListener listener;
	
	public InitiateArmyView (Game theGame, String [] forComboBox)
	{
		comboBox = new JComboBox(forComboBox);
		comboBox.addActionListener(this);
		//theGame.findCity(getName());
		add(mainPanel);
		mainPanel.add(comboBox);
		setSize(new Dimension(400, 200));
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == comboBox)
		{
			listener.onInitiateCity((String)comboBox.getSelectedItem());
		}
		
	}
	public void setListener(InitiateArmyViewListener listener)
	{
		this.listener = listener;
	}
}
