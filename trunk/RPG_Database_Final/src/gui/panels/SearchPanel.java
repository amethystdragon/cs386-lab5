package gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import helpers.GameObject;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchPanel {

	private static JPanel searchPanel;
	
	
	
	protected static JPanel getSearchPanel(){
		if(searchPanel == null){
			searchPanel = new JPanel();
			searchPanel.add(new JLabel("TEST"));
		}
		return searchPanel;
	}
	
	protected static void setSearchPanel(GameObject.ObjectType obj){

		switch (obj){
		case ACCOUNT:
			//add header
			searchPanel = new JPanel(new BorderLayout());
			searchPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			JPanel panel = new JPanel();
			panel.add(new JLabel("Search Accounts"));
			searchPanel.add(panel, BorderLayout.NORTH);
			
			//set main panel
			JPanel accountPanel = new JPanel();
			
			//set search panels
			panel = new JPanel(new GridLayout(1,2));
			JLabel label = new JLabel("Search All");
			panel.add(label);
			JButton button = new JButton("Search All"); //TODO
			panel.add(button);
			
			accountPanel.add(panel);
			
			searchPanel.add(accountPanel);
			break;
			
		case CHARACTER:
			searchPanel = new JPanel(new BorderLayout());
			searchPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			panel = new JPanel();
			panel.add(new JLabel("Search Characters"));
			searchPanel.add(panel, BorderLayout.NORTH);
			
			//set main panel
			JPanel charPanel = new JPanel();
			
			//set search panels
			panel = new JPanel(new GridLayout(1,2));
			label = new JLabel("Search All");
			panel.add(label);
			button = new JButton("Search All"); //TODO
			panel.add(button);
			
			charPanel.add(panel);
			
			searchPanel.add(charPanel);
			break;

		case ITEM:
			searchPanel = new JPanel(new BorderLayout());
			searchPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			panel = new JPanel();
			panel.add(new JLabel("Search Items"));
			searchPanel.add(panel, BorderLayout.NORTH);
			
			//set main panel
			JPanel itemPanel = new JPanel();
			
			//set search panels
			panel = new JPanel(new GridLayout(1,2));
			label = new JLabel("Search All");
			panel.add(label);
			button = new JButton("Search All"); //TODO
			panel.add(button);
			
			itemPanel.add(panel);
			
			searchPanel.add(itemPanel);
			break;
			
		case SKILL:
			searchPanel = new JPanel(new BorderLayout());
			searchPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			panel = new JPanel();
			panel.add(new JLabel("Search Skills"));
			searchPanel.add(panel, BorderLayout.NORTH);
			
			//set main panel
			JPanel skillPanel = new JPanel();
			
			//set search panels
			panel = new JPanel(new GridLayout(1,2));
			label = new JLabel("Search All");
			panel.add(label);
			button = new JButton("Search All"); //TODO
			panel.add(button);
			
			skillPanel.add(panel);
			
			searchPanel.add(skillPanel);
			break;
			
		case ABILITY:
			searchPanel = new JPanel(new BorderLayout());
			searchPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			panel = new JPanel();
			panel.add(new JLabel("Search Abilities"));
			searchPanel.add(panel, BorderLayout.NORTH);
			
			//set main panel
			JPanel abilityPanel = new JPanel();
			
			//set search panels
			panel = new JPanel(new GridLayout(1,2));
			label = new JLabel("Search All");
			panel.add(label);
			button = new JButton("Search All"); //TODO
			panel.add(button);
			
			abilityPanel.add(panel);
			
			searchPanel.add(abilityPanel);
			break;
			
		default:
			searchPanel = new JPanel();
			searchPanel.add(new JLabel("SEARCHTEST"));
			break;
		}
		
	} 
}
