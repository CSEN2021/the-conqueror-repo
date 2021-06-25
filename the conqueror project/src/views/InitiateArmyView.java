package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import engine.*;
import listeners.InitiateArmyViewListener;
import units.*;

public class InitiateArmyView extends JFrame implements ActionListener
{
	
	private JComboBox comboBox;
	private Boolean isChoosingUnit = false;
	private String choosenCity = null;
	private JPanel mainPanel = new JPanel();
	private JLabel chooseLabel = new JLabel("Choose a City to initiate Army from:");
	private InitiateArmyViewListener listener;
	
	public InitiateArmyView (Game theGame, String [] forComboBox)
	{
		comboBox = new JComboBox(forComboBox);
		comboBox.addActionListener(this);
		comboBox.setPreferredSize(new Dimension(300,20));
		//theGame.findCity(getName());
		
		add(chooseLabel,BorderLayout.NORTH);
		add(mainPanel,BorderLayout.CENTER);
		mainPanel.add(comboBox);
		setIconImage(new ImageIcon("resources/icon.png").getImage());
		setTitle("Initiate An Army");
		pack();
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		revalidate();
	}
	public void setChoosenCity(String choosenCity)
	{
		this.choosenCity = choosenCity;
	}
	public Boolean getIsChoosingUnit()
	{
		return isChoosingUnit;
	}
	public void setIsChoosingUnit(Boolean isChoosingUnit)
	{
		this.isChoosingUnit = isChoosingUnit;
	}
	public JLabel getChooseLabel()
	{
		return chooseLabel;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == comboBox && isChoosingUnit == false)
		{
			listener.onInitiateCity((String)comboBox.getSelectedItem());
		}
		else {
			listener.onInitiateUnit((String)comboBox.getSelectedItem());
		}
		
	}
	public void setListener(InitiateArmyViewListener listener)
	{
		this.listener = listener;
	}
}
