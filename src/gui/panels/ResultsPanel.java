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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ResultsPanel {
	private static JPanel resultsPanel;
	
	public static JPanel getResultsPanel(){
		if(resultsPanel == null){
			resultsPanel = new JPanel();
			resultsPanel.add(new JLabel("TEST"));
		}
		return resultsPanel;
	}
	
	/**
	 * Sets the result panel to contain the searched values
	 * 
	 * @param list list of objects from the helpers package
	 */
	public static void setResultsPanel(final List<?> list){
		//Sets the holding panel
		resultsPanel = new JPanel(new BorderLayout());
		JPanel panel = new JPanel();
		resultsPanel.add(panel, BorderLayout.NORTH);
		//Creates the new list 
		DefaultListModel<String> listModel = new DefaultListModel<String>();

		//Determines the type of helper and iterates through the list
		//Also adds a JLabel
		if(list.size()==0) return;
		final Object listItem = list.get(0);
		if(listItem instanceof Ability){
			panel.add(new JLabel("ABILITY SEARCH RESULTS"));
			for(Object o : list) 
				listModel.addElement(((Ability)o).getName());
		}else if(listItem instanceof Account){
			panel.add(new JLabel("ACCOUNT SEARCH RESULTS"));
			for(Object o : list) 
				listModel.addElement(((Account)o).getAccountName());
		}else if(listItem instanceof Character){
			panel.add(new JLabel("CHARACTER SEARCH RESULTS"));
			for(Object o : list) 
				listModel.addElement(((Character)o).getName());
		}else if(listItem instanceof Item){
			panel.add(new JLabel("ITEM SEARCH RESULTS"));
			for(Object o : list) 
				listModel.addElement(((Item)o).getName());
		}else if(listItem instanceof Skill){
			panel.add(new JLabel("SKILL SEARCH RESULTS"));
			for(Object o : list) 
				listModel.addElement(((Skill)o).getName());
		}
		
		//Adds the list model to the JList
		final JList<String> resultsList = new JList<String>(listModel);
		resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//Sets up the listener for which element is selected 
		resultsList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(listItem instanceof Ability){
					DisplayPanel.setDisplayPanel((Ability)list.get(resultsList.getSelectedIndex()));
				}else if(listItem instanceof Account){
					DisplayPanel.setDisplayPanel((Account)list.get(resultsList.getSelectedIndex()));
				}else if(listItem instanceof Character){
					DisplayPanel.setDisplayPanel((Character)list.get(resultsList.getSelectedIndex()));
				}else if(listItem instanceof Item){
					DisplayPanel.setDisplayPanel((Item)list.get(resultsList.getSelectedIndex()));
				}else if(listItem instanceof Skill){
					DisplayPanel.setDisplayPanel((Skill)list.get(resultsList.getSelectedIndex()));
				}
			}
		});
		//Wraps in a scroll pane
		JScrollPane scrollPane = new JScrollPane(resultsList);
		//Adds to the panel
		resultsPanel.add(scrollPane, BorderLayout.CENTER);
	}
}
