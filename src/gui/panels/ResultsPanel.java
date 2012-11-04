package gui.panels;

import helpers.Ability;
import helpers.Account;
import helpers.Character;
import helpers.Item;
import helpers.Skill;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class ResultsPanel {
	private static JPanel resultsPanel;
	
	public static JPanel getResultsPanel(){
		if(resultsPanel == null){
			resultsPanel = new JPanel();
			resultsPanel.add(new JLabel("TEST"));
		}
		return resultsPanel;
	}
	
	public static void setResultsPanel(){
		resultsPanel = new JPanel();
		resultsPanel.add(new JLabel("TEST"));
	}
	
	/**
	 * Sets the result panel to contain the searched values
	 * 
	 * @param list list of objects from the helpers package
	 */
	public static void setResultsPanel(List<?> list){
		//Sets the holding panel
		resultsPanel = new JPanel(new BorderLayout());
		JPanel panel = new JPanel();
		resultsPanel.add(panel, BorderLayout.NORTH);
		//Creates the new list 
		DefaultListModel<String> listModel = new DefaultListModel<String>();

		//Determines the type of helper and iterates through the list
		//Also adds a JLabel
		Object listItem = list.get(0);
		if(listItem instanceof Ability){
				for(Object o : list) 
					listModel.addElement(((Ability)o).getName());
				panel.add(new JLabel("ABILITY SEARCH RESULTS"));
		}else if(listItem instanceof Account){
				for(Object o : list) 
					listModel.addElement(((Account)o).getAccountName());
				panel.add(new JLabel("ACCOUNT SEARCH RESULTS"));
		}else if(listItem instanceof Character){
				for(Object o : list) 
					listModel.addElement(((Character)o).getName());
				panel.add(new JLabel("CHARACTER SEARCH RESULTS"));
		}else if(listItem instanceof Item){
				for(Object o : list) 
					listModel.addElement(((Item)o).getName());
				panel.add(new JLabel("ITEM SEARCH RESULTS"));
		}else if(listItem instanceof Skill){
				for(Object o : list) 
					listModel.addElement(((Skill)o).getName());
				panel.add(new JLabel("SKILL SEARCH RESULTS"));
		}
		
		//Adds the list model to the JList
		JList<String> resultsList = new JList<String>(listModel);
		resultsList.setSelectedIndex(0);
		resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//Wraps in a scroll pane
		JScrollPane scrollPane = new JScrollPane(resultsList);
		//Adds to the panel
		resultsPanel.add(scrollPane, BorderLayout.CENTER);
	}
}
