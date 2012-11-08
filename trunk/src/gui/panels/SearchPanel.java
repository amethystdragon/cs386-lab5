package gui.panels;

import gui.GUI;
import gui.GUI.ObjectType;
import helpers.Character.Race;
import helpers.Item.Rarity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataAccess.DataAccess;

public class SearchPanel {

	private static JPanel searchPanel;
	private static JPanel basePanel;
	
	
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
		basePanel = new JPanel(new GridLayout(8,2));
		JButton button = new JButton("Search All");

		//Fills in all the case specific information
		switch (obj){
		case ACCOUNT:
			//add header
			JLabel label = new JLabel("Search Accounts");
			basePanel.add(label);
			basePanel.add(new JLabel(""));
			//set search panels
			button.setActionCommand("Account");
			
			//User, first, last, email
			JLabel userLabel = new JLabel("Username");
			basePanel.add(userLabel);
			final JTextField user = new JTextField();
			basePanel.add(user);
			
			JLabel fNameLabel = new JLabel("First Name");
			basePanel.add(fNameLabel);
			final JTextField fName = new JTextField();
			basePanel.add(fName);
			
			JLabel lNameLabel = new JLabel("Last Name");
			basePanel.add(lNameLabel);
			final JTextField lName = new JTextField();
			basePanel.add(lName);
			
			JLabel emailLabel = new JLabel("Email");
			basePanel.add(emailLabel);
			final JTextField email = new JTextField();
			basePanel.add(email);
			
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						ResultsPanel.setResultsPanel(DataAccess.getInstance().searchUser(user.getText(), fName.getText(), lName.getText(), email.getText()));
						GUI.getGUI().updateMainPanel();
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			break;
		case CHARACTER:
			//add header
			label = new JLabel("Search Character");
			basePanel.add(label);
			basePanel.add(new JLabel(""));
			//set search panels
			button.setActionCommand("Character");
			//Character name field
			userLabel = new JLabel("Character Name");
			basePanel.add(userLabel);
			final JTextField cName = new JTextField();
			basePanel.add(cName);
			//Adds the race field
			JLabel nameLabel = new JLabel("Race");
			basePanel.add(nameLabel);
			final JTextField race = new JTextField();
			basePanel.add(race);
			//Adds the username field
			JLabel usernameLabel = new JLabel("Username");
			basePanel.add(usernameLabel);
			final JTextField username = new JTextField();
			basePanel.add(username);
			//Adds the specific button listener
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						ResultsPanel.setResultsPanel(DataAccess.getInstance().searchCharacter(cName.getText(), Race.getRace(race.getText()), username.getText()));
						GUI.getGUI().updateMainPanel();
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			break;
		case ITEM:
			//add header
			label = new JLabel("Search Item");
			basePanel.add(label);
			basePanel.add(new JLabel(""));
			//set search panels
			button.setActionCommand("Item");
			
			//User, first, last, email
			JLabel itemLabel = new JLabel("Item Name");
			basePanel.add(itemLabel);
			final JTextField iName = new JTextField();
			basePanel.add(iName);
			
			JLabel rarityLabel = new JLabel("Rarity");
			basePanel.add(rarityLabel);
			final JTextField rarity = new JTextField();
			basePanel.add(rarity);
			
			JLabel iabilityLabel = new JLabel("Ability");
			basePanel.add(iabilityLabel);
			final JTextField iability = new JTextField();
			basePanel.add(iability);
			//Adds the specific button listener
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						ResultsPanel.setResultsPanel(DataAccess.getInstance().searchItem(iName.getText(), Rarity.getRRarity(rarity.getText()), iability.getText()));
						GUI.getGUI().updateMainPanel();
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			break;
		case SKILL:
			//add header
			label = new JLabel("Search Skill");
			basePanel.add(label);
			basePanel.add(new JLabel(""));
			//set search panels
			button.setActionCommand("Skill");
			
			//User, first, last, email
			JLabel skillLabel = new JLabel("Skill Name");
			basePanel.add(skillLabel);
			final JTextField skill = new JTextField();
			basePanel.add(skill);
			
			JLabel levelReqLabel = new JLabel("Level Requirement");
			basePanel.add(levelReqLabel);
			
			JPanel temp = new JPanel(new BorderLayout());
			String[] choices = {">","<","="};
			final JComboBox<String> overUnder = new JComboBox<String>(choices);
			temp.add(overUnder, BorderLayout.WEST);
			final JTextField levelReq = new JTextField();
			temp.add(levelReq, BorderLayout.CENTER);
			basePanel.add(temp);
			basePanel.add(new JLabel(""));
			basePanel.add(new JLabel(""));
			//Adds the specific button listener
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						ResultsPanel.setResultsPanel(DataAccess.getInstance().searchSkill(
								skill.getText(), 
								(levelReq.getText().isEmpty())?-1:Integer.parseInt(levelReq.getText()), 
								((String)overUnder.getSelectedItem())));
						GUI.getGUI().updateMainPanel();
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			break;
		case ABILITY:
			//add header
			label = new JLabel("Search Ability");
			basePanel.add(label);
			basePanel.add(new JLabel(""));
			//set search panels
			button.setActionCommand("Ability");
			
			//User, first, last, email
			JLabel abilityLabel = new JLabel("Avility Name");
			basePanel.add(abilityLabel);
			final JTextField ability = new JTextField();
			basePanel.add(ability);
			
			JLabel alevelReqLabel = new JLabel("Level Requirement");
			basePanel.add(alevelReqLabel);
			
			JPanel atemp = new JPanel(new BorderLayout());
			String[] achoices = {">","<","="};
			final JComboBox<String> aoverUnder = new JComboBox<String>(achoices);
			atemp.add(aoverUnder, BorderLayout.WEST);
			final JTextField alevelReq = new JTextField();
			atemp.add(alevelReq, BorderLayout.CENTER);
			basePanel.add(atemp);
			basePanel.add(new JLabel(""));
			basePanel.add(new JLabel(""));
			//Adds the specific button listener
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						ResultsPanel.setResultsPanel(DataAccess.getInstance().searchAbility(
								ability.getText(), 
								(alevelReq.getText().isEmpty())?-1:Integer.parseInt(alevelReq.getText()), 
								((String)aoverUnder.getSelectedItem())));
						GUI.getGUI().updateMainPanel();
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			break;
		default: //Who knows how you got here but, eh whatever. Fine, you did.
			searchPanel.add(new JLabel("SEARCHTEST"));
			break;
		}
		//Adds everything together
		basePanel.add(button);
		searchPanel.add(basePanel, BorderLayout.NORTH);
		
	} 
}
