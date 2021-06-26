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
import listeners.InitiateArmyViewListener;

public class TemplateComboBoxView extends JFrame implements ActionListener
{
	JComboBox comboBox;
	JPanel mainPanel = new JPanel();
	JLabel chooseLabel = new JLabel("something to fix size");
	
	public TemplateComboBoxView(Game theGame, String [] forComboBox)
	{
		comboBox = new JComboBox(forComboBox);
		comboBox.addActionListener(this);
		comboBox.setPreferredSize(new Dimension(300,20));
		
		add(chooseLabel,BorderLayout.NORTH);
		add(mainPanel,BorderLayout.CENTER);
		mainPanel.add(comboBox);
		setIconImage(new ImageIcon("resources/icon.png").getImage());
		setVisible(true);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		revalidate();
		repaint();
	}
	public JLabel getChooseLabel()
	{
		return chooseLabel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// implement in subclass
	}
}
