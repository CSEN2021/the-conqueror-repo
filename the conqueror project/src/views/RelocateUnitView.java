package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Game;
import listeners.RelocateUnitListener;

public class RelocateUnitView extends JFrame implements ActionListener
{
	private JComboBox comboBox;
	private int stageOfRelocation = 0;
	private JPanel mainPanel = new JPanel();
	private JLabel chooseLabel = new JLabel("Choose a City to initiate Army from:");
	private RelocateUnitListener listener;
	
	public RelocateUnitView (Game theGame, String [] forComboBox)
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

	public JLabel getChooseLabel()
	{
		return chooseLabel;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == comboBox && stageOfRelocation == 0)
		{
			listener.onRelocateArmyFrom(comboBox.getSelectedIndex());
		}
		else if (e.getSource() == comboBox && stageOfRelocation == 1)
		{
			listener.onRelocateUnitChosen(comboBox.getSelectedIndex());
		}
		else 
		{
			listener.onRelocateArmyTo(comboBox.getSelectedIndex());
		}
		
	}
	public void setStageOfRelocation(int stageOfRelocation)
	{
		this.stageOfRelocation = stageOfRelocation;
	}

	public void setListener(RelocateUnitListener listener)
	{
		this.listener = listener;
	}
}
