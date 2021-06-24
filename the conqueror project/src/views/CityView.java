package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Game;

public class CityView extends TemplateView implements ActionListener
{
	private JButton buildButton = new JButton("Build");
	private JButton upgradeButton = new JButton("Upgrade");
	private JButton recruitButton = new JButton("Recruit Button");
	private JLabel barracksLabel = new JLabel("No Barracks");
	private JLabel baracksLvlLabel = new JLabel("Level : 0");
	private JLabel baracksRecruitLabel = new JLabel("No unit");
	private JLabel archeryRangeLabel = new JLabel("No ArcheryRange");
	private JLabel archeryRangeLvllLabel = new JLabel("Level : 0");
	private JLabel archeryRangeRecruitLabel = new JLabel("No unit");
	private JLabel stableLabel = new JLabel("No Stable");
	private JLabel stableLvlLabel = new JLabel("Level : 0");
	private JLabel stableRecruitLabel = new JLabel("No unit");
	private JLabel marketLabel = new JLabel("No Market");
	private JLabel marketLvlLabel = new JLabel("Level : 0");
	private JLabel farmLabel = new JLabel("No Farm");
	private JLabel farmLvlLabel = new JLabel("Level : 0");
	private JLabel fillerLabel = new JLabel("");
	private JPanel midPanel = new JPanel();
	private JPanel bottomPanel = new JPanel(new BorderLayout());
	private JPanel fillerPanel = new JPanel();
	
	public CityView(Game theGame)
	{
		super(theGame);
		
		// components
		setUpButton(upgradeButton);
		setUpButton(buildButton);
		setUpButton(recruitButton);

		setUpLabel(archeryRangeLabel);
		setUpLabel(archeryRangeLvllLabel);
		setUpLabel(archeryRangeRecruitLabel);
		setUpLabel(barracksLabel);
		setUpLabel(baracksLvlLabel);
		setUpLabel(baracksRecruitLabel);
		setUpLabel(stableLabel);
		setUpLabel(stableLvlLabel);
		setUpLabel(stableRecruitLabel);
		setUpLabel(marketLabel);
		setUpLabel(marketLvlLabel);
		setUpLabel(farmLabel);
		setUpLabel(farmLvlLabel);
		
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
		
	}
	
}