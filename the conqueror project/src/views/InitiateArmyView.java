package views;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import engine.*;
import units.*;

public class InitiateArmyView extends JFrame
{
	public InitiateArmyView ()
	{
		String string[] = {"Cairo","Sparta","Rome"};
		JComboBox<String> thecomboBox = new JComboBox<String>();
		setSize(new Dimension(400, 200));
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
