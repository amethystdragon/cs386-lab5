package gui.panels;

import gui.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dataAccess.DataAccess;

public class SearchPanel {

	private static JPanel searchPanel;
	
	
	
	public static JPanel getSearchPanel(){
		if(searchPanel == null){
			searchPanel = new JPanel();
			searchPanel.add(new JLabel("TEST"));
		}
		return searchPanel;
	}
	
	public static void setSearchPanel(GUI.ObjectType obj){
		searchPanel = new JPanel(new BorderLayout());
		searchPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel panel = new JPanel();

		switch (obj){
		case ACCOUNT:
			//add header
			panel.add(new JLabel("Search Accounts"));
			searchPanel.add(panel, BorderLayout.NORTH);

			//set search panels
			panel = new JPanel(new GridLayout(1,2));
			JLabel label = new JLabel("Search All");
			panel.add(label);
			JButton button = new JButton("Search All");
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					try{
						ResultsPanel.setResultsPanelAcc(DataAccess.getInstance().searchUser("", "", "", ""));
						GUI.getGUI().updateMainPanel();
					}catch(Exception e){}
				}
			});
			panel.add(button);
			
			searchPanel.add(panel);
			break;
			
		case CHARACTER:
			panel.add(new JLabel("Search Characters"));
			searchPanel.add(panel, BorderLayout.NORTH);
			
			//set search panels
			panel = new JPanel(new GridLayout(1,2));
			label = new JLabel("Search All");
			panel.add(label);
			button = new JButton("Search All"); //TODO
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					try{
						ResultsPanel.setResultsPanelChar(DataAccess.getInstance().searchCharacter("", null, ""));
						GUI.getGUI().updateMainPanel();
					}catch(Exception e){}
				}
			});
			panel.add(button);
			
			searchPanel.add(panel);
			break;

		case ITEM:
			panel.add(new JLabel("Search Items"));
			searchPanel.add(panel, BorderLayout.NORTH);
			
			//set search panels
			panel = new JPanel(new GridLayout(1,2));
			label = new JLabel("Search All");
			panel.add(label);
			button = new JButton("Search All"); //TODO
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					try{
						ResultsPanel.setResultsPanelItem(DataAccess.getInstance().searchItem("", null, ""));
						GUI.getGUI().updateMainPanel();
					}catch(Exception e){}
				}
			});
			panel.add(button);
			
			searchPanel.add(panel);
			break;
			
		case SKILL:
			panel.add(new JLabel("Search Skills"));
			searchPanel.add(panel, BorderLayout.NORTH);
			
			//set search panels
			panel = new JPanel(new GridLayout(1,2));
			label = new JLabel("Search All");
			panel.add(label);
			button = new JButton("Search All"); //TODO
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					try{
						ResultsPanel.setResultsPanelSkill(DataAccess.getInstance().searchSkill("", ""));
						GUI.getGUI().updateMainPanel();
					}catch(Exception e){}
				}
			});
			panel.add(button);
			
			searchPanel.add(panel);
			break;
			
		case ABILITY:
			panel.add(new JLabel("Search Abilities"));
			searchPanel.add(panel, BorderLayout.NORTH);
			
			//set search panels
			panel = new JPanel(new GridLayout(1,2));
			label = new JLabel("Search All");
			panel.add(label);
			button = new JButton("Search All"); //TODO
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					try{
						ResultsPanel.setResultsPanelAbility(DataAccess.getInstance().searchAbility("", ""));
						GUI.getGUI().updateMainPanel();
					}catch(Exception e){}
				}
			});
			panel.add(button);
			
			searchPanel.add(panel);
			break;
			
		default:
			searchPanel.add(new JLabel("SEARCHTEST"));
			break;
		}
		
	} 
}
