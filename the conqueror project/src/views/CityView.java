package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import buildings.ArcheryRange;
import engine.City;
import engine.Game;
import listeners.CityViewListener;

public class CityView extends TemplateView implements ActionListener
{
	private City currentCity;
	private CityViewListener listener;
	
	
	public City getCurrentCity() {
		return currentCity;
	}
	
	
	public void setCurrentCity(City currentCity) {
		this.currentCity = currentCity;
	}



	private JButton buildButton = new JButton("Build");
	private JButton upgradeButton = new JButton("Upgrade");
	private JButton recruitButton = new JButton("Recruit Button");
	private JButton barracksLabel = new JButton("No Barracks");
	private JButton baracksLvlLabel = new JButton("Level : 0");
	private JButton baracksRecruitLabel = new JButton("No unit");
	private JButton archeryRangeLabel = new JButton("No ArcheryRange");
	private JButton archeryRangeLvllLabel = new JButton("Level : 0");
	private JButton archeryRangeRecruitLabel = new JButton("No unit");
	private JButton stableLabel = new JButton("No Stable");
	private JButton stableLvlLabel = new JButton("Level : 0");
	private JButton stableRecruitLabel = new JButton("No unit");
	private JButton marketLabel = new JButton("No Market");
	private JButton marketLvlLabel = new JButton("Level : 0");
	private JButton farmLabel = new JButton("No Farm");
	private JButton farmLvlLabel = new JButton("Level : 0");
	private JLabel fillerLabel = new JLabel("");
	private JPanel midPanel = new JPanel();
	private JPanel bottomPanel = new JPanel(new BorderLayout());
	private JPanel fillerPanel = new JPanel();
	
	
	
	
	
	public void setUpButton2(JButton theButton, String path )
	{
		theButton.setIcon(new ImageIcon("resources/" + path));
		theButton.setForeground(Color.WHITE);
		theButton.setBackground(Color.DARK_GRAY);
		theButton.setFocusable(false);
		theButton.repaint();
	}
	
	public CityView(Game theGame)
	{
		super(theGame);
		
		// components
		setUpButton(upgradeButton);
		setUpButton(buildButton);
		setUpButton(recruitButton);

		setUpButton(archeryRangeLabel);
		setUpButton(archeryRangeLvllLabel);
		setUpButton(archeryRangeRecruitLabel);
		setUpButton(barracksLabel);
		setUpButton(baracksLvlLabel);
		setUpButton(baracksRecruitLabel);
		setUpButton(stableLabel);
		setUpButton(stableLvlLabel);
		setUpButton(stableRecruitLabel);
		setUpButton(marketLabel);
		setUpButton(marketLvlLabel);
		setUpButton(farmLabel);
		setUpButton(farmLvlLabel);
		
		//Icons
		
		
		
		
		
		// panels
		midPanel.setLayout(new GridLayout(0, 3));
		midPanel.setPreferredSize(new Dimension(0, 200));
		
		// add
		
		add(midPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		midPanel.add(buildButton);
		midPanel.add(upgradeButton);
		midPanel.add(recruitButton);
		
		midPanel.add(archeryRangeLabel);
		midPanel.add(archeryRangeLvllLabel);
		midPanel.add(archeryRangeRecruitLabel);
		midPanel.add(barracksLabel);
		midPanel.add(baracksLvlLabel);
		midPanel.add(baracksRecruitLabel);
		midPanel.add(stableLabel);
		midPanel.add(stableLvlLabel);
		midPanel.add(stableRecruitLabel);
		
		midPanel.add(marketLabel);
		midPanel.add(marketLvlLabel);
		midPanel.add(fillerLabel);
		midPanel.add(farmLabel);
		midPanel.add(farmLvlLabel);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == archeryRangeLabel)
		{
			listener.onBuild("ArcheryRange");
		}
		else if (e.getSource() == barracksLabel)
		{
			listener.onBuild("Barracks");
		}
		else if (e.getSource() == stableLabel)
		{
			listener.onBuild("Stable");
		}
		else if (e.getSource() == farmLabel)
		{
			listener.onBuild("Farm");
		}
		else if (e.getSource() == marketLabel)
		{
			listener.onBuild("Market");
				
		}
		
		
	}

	public CityViewListener getListener() {
		return listener;
	}

	public void setListener(CityViewListener listener) {
		this.listener = listener;
	}
	
}