package gui.panels;

import gui.GUI;
import gui.GUI.ObjectType;

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
	
	public static void setSearchPanel(ObjectType obj){
		searchPanel = new JPanel(new BorderLayout());
		searchPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//set search panels
		JPanel panel = new JPanel(new GridLayout(1,2));
		JLabel label = new JLabel("Search All");
		panel.add(label);
		JButton button = new JButton("Search All");

		//Fills in all the case specific information
		switch (obj){
		case ACCOUNT:
			//add header
			panel.add(new JLabel("Search Accounts"));
			//set search panels
			button.addActionListener((new SearchPanel()).new SearchListener());
			button.setActionCommand("Account");
			break;
		case CHARACTER:
			panel.add(new JLabel("Search Characters"));
			//set search panels
			button.addActionListener((new SearchPanel()).new SearchListener());
			button.setActionCommand("Character");
			break;
		case ITEM:
			panel.add(new JLabel("Search Items"));
			//set search panels
			button.addActionListener((new SearchPanel()).new SearchListener());
			button.setActionCommand("Item");
			break;
		case SKILL:
			panel.add(new JLabel("Search Skills"));
			//set search panels
			button.addActionListener((new SearchPanel()).new SearchListener());
			button.setActionCommand("Skill");
			break;
		case ABILITY:
			panel.add(new JLabel("Search Abilities"));
			//set search panels
			button.addActionListener((new SearchPanel()).new SearchListener());
			button.setActionCommand("Ability");
			break;
		default:
			searchPanel.add(new JLabel("SEARCHTEST"));
			break;
		}
		//Adds everything together
		panel.add(button);
		searchPanel.add(panel, BorderLayout.NORTH);
		
	} 
	private class SearchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				switch(e.getActionCommand()){
				case "User":
					ResultsPanel.setResultsPanel(DataAccess.getInstance().searchUser("", "", "", ""));
					GUI.getGUI().updateMainPanel();
					break;
				case "Character":
					ResultsPanel.setResultsPanel(DataAccess.getInstance().searchCharacter("", null, ""));
					GUI.getGUI().updateMainPanel();
					break;
				case "Item":
					ResultsPanel.setResultsPanel(DataAccess.getInstance().searchItem("", null, ""));
					GUI.getGUI().updateMainPanel();
					break;
				case "Skill":
					ResultsPanel.setResultsPanel(DataAccess.getInstance().searchSkill("", ""));
					GUI.getGUI().updateMainPanel();
					break;
				case "Ability":
					ResultsPanel.setResultsPanel(DataAccess.getInstance().searchAbility("", -1));
					GUI.getGUI().updateMainPanel();
					break;
				default:
					System.out.println(e.getActionCommand());
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}			
		}
		
	}
}
