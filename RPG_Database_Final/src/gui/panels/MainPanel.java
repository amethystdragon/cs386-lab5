package gui.panels;

import gui.GUI;
import helpers.Account;
import helpers.GameObject;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.util.*;

import javax.swing.JPanel;

public class MainPanel {
	
	private static JPanel mainPanel;
	private static JPanel subPanel;
	private static JPanel searchPanel;
	private static JPanel resultsPanel;
	private static JPanel displayPanel;
	private static JPanel adminButtonPanel;
	
	public static void setResultsPanel(JPanel results){
		
	}
	
	
	
	public static JPanel getMainPanel(){
//		if(mainPanel == null){
			mainPanel = new JPanel(new BorderLayout());
			adminButtonPanel = AdminButtonPanel.getAdminButtonPanel();
			mainPanel.add(adminButtonPanel, BorderLayout.NORTH);
			subPanel = new JPanel(new GridLayout(1,3));
			searchPanel = SearchPanel.getSearchPanel();
			resultsPanel = ResultsPanel.getResultsPanel();
			displayPanel = DisplayPanel.getDisplayPanel();
			subPanel.add(searchPanel);
			subPanel.add(resultsPanel);
			subPanel.add(displayPanel);
			mainPanel.add(subPanel);
			mainPanel.validate();
//		}
		
		return mainPanel;
	}
	
	public static void setSearchPanel(GameObject.ObjectType type){
		SearchPanel.setSearchPanel(type);
	}
	
	public static void setResultsPanel(List<Account> accountList){
		ResultsPanel.setResultsPanel(accountList);
	}
	
	public static void setResultsPanel(){
		ResultsPanel.setResultsPanel();
	}
	
	public static void setDisplayPanel(GameObject.ObjectType type){
		DisplayPanel.setDisplayPanel(type);
		displayPanel = DisplayPanel.getDisplayPanel();
		//GUI.validateFrame();
	}
	
	private static void updatePanel(){
		
	}
	
}
