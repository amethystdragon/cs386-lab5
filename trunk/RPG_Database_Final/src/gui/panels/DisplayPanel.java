package gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import helpers.Account;
import helpers.Character;
import helpers.Item;
import helpers.Skill;
import helpers.Ability;
import helpers.GameObject;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

public class DisplayPanel {
	private static JPanel displayPanel;
	
	protected static JPanel getDisplayPanel(){
		if(displayPanel == null){
			displayPanel = new JPanel();
			displayPanel.add(new JLabel("TEST"));
		}
		return displayPanel;
	}
	
	protected static void setDisplayPanel(GameObject.ObjectType type){
		switch (type) {
			case ACCOUNT:
				displayPanel = new JPanel(new GridLayout(2,1));
				JPanel topPanel = new JPanel(new GridLayout(8,1));
				topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				
				JPanel bottomPanel = new JPanel(new BorderLayout());
				bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				
				//add to toppanel
				JPanel panel = new JPanel();
				JTextField field;
				panel.add(new JLabel("ACCOUNT"));
				topPanel.add(panel);
				panel = new JPanel(new GridLayout(1,2));
				panel.add(new JLabel("  Account Name"));
				field = new JTextField("");
				field.setEditable(false);
				panel.add(field);
				topPanel.add(panel);
				panel = new JPanel(new GridLayout(1,2));
				panel.add(new JLabel("  Password"));
				field = new JTextField("");
				field.setEditable(false);
				panel.add(field);
				topPanel.add(panel);
				panel = new JPanel(new GridLayout(1,2));
				panel.add(new JLabel("  Email"));
				field = new JTextField("");
				field.setEditable(false);
				panel.add(field);
				topPanel.add(panel);
				panel = new JPanel(new GridLayout(1,2));
				panel.add(new JLabel("  First Name"));
				field = new JTextField("");
				field.setEditable(false);
				panel.add(field);
				topPanel.add(panel);
				panel = new JPanel(new GridLayout(1,2));
				panel.add(new JLabel("  Last Name"));
				field = new JTextField("");
				field.setEditable(false);
				panel.add(field);
				topPanel.add(panel);
				panel = new JPanel(new GridLayout(1,2));
				panel.add(new JLabel("  Ingame Currency"));
				field = new JTextField("");
				field.setEditable(false);
				panel.add(field);
				topPanel.add(panel);
				
				panel = new JPanel(new GridLayout(1,3));
				//add, edit & delete buttons
				JButton add = new JButton("CREATE ACCOUNT");
				//TODO edit.addActionListener()
				panel.add(add);
				JButton edit = new JButton("EDIT ACCOUNT");
				panel.add(edit);
				JButton delete = new JButton("DELETE ACCOUNT");
				panel.add(delete);
				topPanel.add(panel);
				
				//add to bottom panel
				panel = new JPanel();
				panel.add(new JLabel("CHARACTERS OWNED BY ACCOUNT"));
				bottomPanel.add(panel, BorderLayout.NORTH);
				bottomPanel.add(new JScrollBar(),BorderLayout.CENTER);
				
				panel = new JPanel(new GridLayout(1,3));
				JButton addCharacter = new JButton("Create Character");
				panel.add(addCharacter);
				JButton displayCharacter = new JButton("Display Character");
				panel.add(displayCharacter);
				JButton deleteCharacter = new JButton("Delete Character");
				panel.add(deleteCharacter);
				bottomPanel.add(panel,BorderLayout.SOUTH);
				
				//add panels to display
				displayPanel.add(topPanel);
				displayPanel.add(bottomPanel);
				break;
			
			case CHARACTER:
				displayPanel = new JPanel(new GridLayout(2,1));
				topPanel = new JPanel(new BorderLayout());
				topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				
				JPanel temp = new JPanel();
				JLabel label = new JLabel("CHARACTER");
				temp.add(label);
				topPanel.add(temp, BorderLayout.NORTH);
				
				panel = new JPanel(new GridLayout(1,2));
				
				
				JPanel topSub = new JPanel(new GridLayout(2,1));
				
				JPanel charSubPanel = new JPanel(new GridLayout(1,2));
				JPanel panel1 = new JPanel(new GridLayout(6,1));
				
				//name
				panel1.add(new JLabel("  Name"));
				field = new JTextField("");
				field.setEditable(false);
				panel1.add(field);
				//race
				panel1.add(new JLabel("  Race"));				
				field = new JTextField("");
				field.setEditable(false);
				panel1.add(field);
				//model
				panel1.add(new JLabel("  Model"));
				field = new JTextField("");
				field.setEditable(false);
				panel1.add(field);
				charSubPanel.add(panel1);
				//add image
				JLabel image = new JLabel(new ImageIcon("defaultchar.jpg"));
				image.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				charSubPanel.add(image);
				topSub.add(charSubPanel);
				
				panel = new JPanel(new GridLayout(5,1));
				
				JPanel subPanel = new JPanel(new GridLayout(1,2));
				
				//attributes
				subPanel = new JPanel(new GridLayout(1,4));
				subPanel.add(new JLabel("  STR"));
				field = new JTextField("");
				field.setEditable(false);
				subPanel.add(field);
				subPanel.add(new JLabel("  CON"));
				field = new JTextField("");
				field.setEditable(false);
				subPanel.add(field);
				panel.add(subPanel);
				
				subPanel = new JPanel(new GridLayout(1,4));
				subPanel.add(new JLabel("  AGI"));
				field = new JTextField("");
				field.setEditable(false);
				subPanel.add(field);
				subPanel.add(new JLabel("  DEX"));
				field = new JTextField("");
				field.setEditable(false);
				subPanel.add(field);
				panel.add(subPanel);
				
				subPanel = new JPanel(new GridLayout(1,4));
				subPanel.add(new JLabel("  INT"));
				field = new JTextField("");
				field.setEditable(false);
				subPanel.add(field);
				subPanel.add(new JLabel("  WIS"));
				field = new JTextField("");
				field.setEditable(false);
				subPanel.add(field);
				panel.add(subPanel);
				
				//account
				subPanel = new JPanel(new GridLayout(1,3));
				subPanel.add(new JLabel("  Account"));
				field = new JTextField("");
				field.setEditable(false);
				subPanel.add(field);
				JButton displayAccount = new JButton("Display Account");
				subPanel.add(displayAccount);
				panel.add(subPanel);

				subPanel = new JPanel(new GridLayout(1,2));
				//edit & delete buttons
				edit = new JButton("EDIT");
				subPanel.add(edit);
				delete = new JButton("DELETE");
				subPanel.add(delete);
				panel.add(subPanel);
				
				topSub.add(panel);
				
				topPanel.add(topSub);
				
				bottomPanel = new JPanel(new GridLayout(1,2));
				bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));

				JPanel itemsPanel = new JPanel(new BorderLayout());
				temp = new JPanel();
				label = new JLabel("ITEMS OWNED BY CHARACTER");
				temp.add(label);
				itemsPanel.add(temp, BorderLayout.NORTH);
				itemsPanel.add(new JScrollBar(), BorderLayout.CENTER);
				panel = new JPanel(new GridLayout(3,1));
				JButton addItem = new JButton("Add Item");
				panel.add(addItem);
				JButton displayItem = new JButton("Display Item");
				panel.add(displayItem);
				JButton removeItem = new JButton("Remove Item");
				panel.add(removeItem);
				itemsPanel.add(panel, BorderLayout.SOUTH);
				
				JPanel skillsPanel = new JPanel(new BorderLayout());
				temp = new JPanel();
				label = new JLabel("CHARACTER SKILLS");
				temp.add(label);
				skillsPanel.add(temp, BorderLayout.NORTH);
				skillsPanel.add(new JScrollBar(), BorderLayout.CENTER);
				panel = new JPanel(new GridLayout(3,1));
				JButton addSkill = new JButton("Add Skill");
				panel.add(addSkill);
				JButton displaySkill = new JButton("Display Skill");
				panel.add(displaySkill);
				JButton removeSkill = new JButton("Remove Skill");
				panel.add(removeSkill);
				skillsPanel.add(panel, BorderLayout.SOUTH);
				
				bottomPanel.add(itemsPanel);
				bottomPanel.add(skillsPanel);
				
				displayPanel.add(topPanel);
				displayPanel.add(bottomPanel);
				break;
				
			case ABILITY:

				displayPanel = new JPanel(new GridLayout(2,1));
				topPanel = new JPanel(new GridLayout(2,1));
				topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				panel = new JPanel(new GridLayout(3,1));
				temp = new JPanel();
				label = new JLabel("ABILITY");
				temp.add(label);
				panel.add(temp);
				temp = new JPanel(new GridLayout(1,2));
				temp.add(new JLabel("  Name"));
				field = new JTextField("");
				field.setEditable(false);
				temp.add(field);
				panel.add(temp);
				temp = new JPanel(new GridLayout(1,2));
				temp.add(new JLabel("  Level Requirement"));
				field = new JTextField("");
				field.setEditable(false);
				temp.add(field);
				panel.add(temp);
				topPanel.add(panel);
				
				panel = new JPanel(new BorderLayout());
				temp = new JPanel();
				label = new JLabel("Description");
				temp.add(label);
				panel.add(temp, BorderLayout.NORTH);
				field = new JTextField("");
				field.setEditable(false);
				panel.add(field, BorderLayout.CENTER);
				subPanel = new JPanel(new GridLayout(1,3));
				//add edit & delete buttons
				JButton createAbility = new JButton("CREATE ABILITY");
				//TODO 
				subPanel.add(createAbility);
				edit = new JButton("EDIT ABILITY");
				subPanel.add(edit);
				delete = new JButton("DELETE ABILITY"); //TODO
				subPanel.add(delete);
				panel.add(subPanel, BorderLayout.SOUTH);
				topPanel.add(panel);
				
				bottomPanel = new JPanel(new BorderLayout());
				bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				
				panel = new JPanel();
				panel.add(new JLabel("ITEMS WITH THIS ABILITY"));
				bottomPanel.add(panel, BorderLayout.NORTH);
				bottomPanel.add(new JScrollBar(),BorderLayout.CENTER);
				
				displayCharacter = new JButton("Display Item"); //TODO
				bottomPanel.add(displayCharacter,BorderLayout.SOUTH);
				
				displayPanel.add(topPanel);
				displayPanel.add(bottomPanel);
				break;
				
			case ITEM:

				displayPanel = new JPanel(new GridLayout(2,1));
				topPanel = new JPanel(new BorderLayout());
				topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				
				temp = new JPanel();
				label = new JLabel("ITEM");
				temp.add(label);
				topPanel.add(temp, BorderLayout.NORTH);
				
				panel = new JPanel(new GridLayout(1,2));
				
				
				topSub = new JPanel(new GridLayout(2,1));
				
				JPanel itemSubPanel = new JPanel(new GridLayout(1,2));
				panel1 = new JPanel(new GridLayout(6,1));
				
				//name
				panel1.add(new JLabel("  Name"));
				field = new JTextField("");
				field.setEditable(false);
				panel1.add(field);
				//race
				panel1.add(new JLabel("  Rarity"));				
				field = new JTextField("");
				field.setEditable(false);
				panel1.add(field);
				//model
				panel1.add(new JLabel("  Model"));
				field = new JTextField("");
				field.setEditable(false);
				panel1.add(field);
				itemSubPanel.add(panel1);
				//add image
				image = new JLabel(new ImageIcon("defaultitem.jpg"));
				image.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				itemSubPanel.add(image);
				topSub.add(itemSubPanel);
				
				panel = new JPanel(new GridLayout(6,1));
				
				subPanel = new JPanel(new GridLayout(1,2));
				
				//attributes
				subPanel = new JPanel(new GridLayout(1,2));
				subPanel.add(new JLabel("  Damage"));
				field = new JTextField("");
				field.setEditable(false);
				subPanel.add(field);
				panel.add(subPanel);
				
				subPanel = new JPanel(new GridLayout(1,2));
				subPanel.add(new JLabel("  Armor"));
				field = new JTextField("");
				field.setEditable(false);
				subPanel.add(field);
				panel.add(subPanel);
				
				subPanel = new JPanel(new GridLayout(1,2));
				subPanel.add(new JLabel("  Level Requirement"));
				field = new JTextField("");
				field.setEditable(false);
				subPanel.add(field);
				panel.add(subPanel);
				
				subPanel = new JPanel(new GridLayout(1,2));
				subPanel.add(new JLabel("  Value"));
				field = new JTextField("");
				field.setEditable(false);
				subPanel.add(field);
				panel.add(subPanel);
				
				subPanel = new JPanel(new GridLayout(1,3));
				subPanel.add(new JLabel("  Ability"));
				field = new JTextField("");
				field.setEditable(false);
				subPanel.add(field);
				JButton displayAbility = new JButton("Display Ability");
				subPanel.add(displayAbility);
				panel.add(subPanel);
				
				

				subPanel = new JPanel(new GridLayout(1,3));
				//add edit & delete buttons
				JButton createItem = new JButton("CREATE ITEM");
				//TODO
				subPanel.add(createItem);
				edit = new JButton("EDIT ITEM");
				subPanel.add(edit);
				delete = new JButton("DELETE ITEM");
				subPanel.add(delete);
				panel.add(subPanel);
				
				topSub.add(panel);
				
				topPanel.add(topSub);
				
				bottomPanel = new JPanel(new BorderLayout());
				bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				
				panel = new JPanel();
				panel.add(new JLabel("CHARACTERS WITH THIS ITEM"));
				bottomPanel.add(panel, BorderLayout.NORTH);
				bottomPanel.add(new JScrollBar(),BorderLayout.CENTER);
				
				displayCharacter = new JButton("Display Character"); //TODO
				bottomPanel.add(displayCharacter,BorderLayout.SOUTH);
				
				displayPanel.add(topPanel);
				displayPanel.add(bottomPanel);
				break;
				
			case SKILL:
				displayPanel = new JPanel(new GridLayout(2,1));
				topPanel = new JPanel(new GridLayout(2,1));
				topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				panel = new JPanel(new GridLayout(3,1));
				temp = new JPanel();
				label = new JLabel("SKILL");
				temp.add(label);
				panel.add(temp);
				temp = new JPanel(new GridLayout(1,2));
				temp.add(new JLabel("  Name"));
				field = new JTextField("");
				field.setEditable(false);
				temp.add(field);
				panel.add(temp);
				temp = new JPanel(new GridLayout(1,2));
				temp.add(new JLabel("  Level Requirement"));
				field = new JTextField("");
				field.setEditable(false);
				temp.add(field);
				panel.add(temp);
				topPanel.add(panel);
				
				panel = new JPanel(new BorderLayout());
				temp = new JPanel();
				label = new JLabel("Description");
				temp.add(label);
				panel.add(temp, BorderLayout.NORTH);
				field = new JTextField("");
				field.setEditable(false);
				panel.add(field, BorderLayout.CENTER);
				subPanel = new JPanel(new GridLayout(1,3));
				//create edit & delete buttons
				
				JButton createSkill = new JButton("CREATE SKILL");
				subPanel.add(createSkill);
				edit = new JButton("EDIT SKILL"); //TODO
				//TODO edit.addActionListener()
				subPanel.add(edit);
				delete = new JButton("DELETE SKILL"); //TODO
				//TODO add.addActionListener()
				subPanel.add(delete);
				panel.add(subPanel, BorderLayout.SOUTH);
				topPanel.add(panel);
				
				bottomPanel = new JPanel(new BorderLayout());
				bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				
				panel = new JPanel();
				panel.add(new JLabel("CHARACTERS WITH THIS SKILL"));
				bottomPanel.add(panel, BorderLayout.NORTH);
				bottomPanel.add(new JScrollBar(),BorderLayout.CENTER);
				
				displayCharacter = new JButton("Display Character"); //TODO
				bottomPanel.add(displayCharacter,BorderLayout.SOUTH);
				
				displayPanel.add(topPanel);
				displayPanel.add(bottomPanel);
				break;
		}
	}
	
	protected static void setDisplayPanel(Account account){
			displayPanel = new JPanel(new GridLayout(2,1));
			JPanel topPanel = new JPanel(new GridLayout(8,1));
			topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			
			JPanel bottomPanel = new JPanel(new BorderLayout());
			bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			
			//add to toppanel
			JPanel panel = new JPanel();
			JTextField field;
			panel.add(new JLabel("ACCOUNT"));
			topPanel.add(panel);
			panel = new JPanel(new GridLayout(1,2));
			panel.add(new JLabel("  Account Name"));
			field = new JTextField(account.getAccountName());
			field.setEditable(false);
			panel.add(field);
			topPanel.add(panel);
			panel = new JPanel(new GridLayout(1,2));
			panel.add(new JLabel("  Password"));
			field = new JTextField(account.getPassword());
			field.setEditable(false);
			panel.add(field);
			topPanel.add(panel);
			panel = new JPanel(new GridLayout(1,2));
			panel.add(new JLabel("  Email"));
			field = new JTextField(account.getEmail());
			field.setEditable(false);
			panel.add(field);
			topPanel.add(panel);
			panel = new JPanel(new GridLayout(1,2));
			panel.add(new JLabel("  First Name"));
			field = new JTextField(account.getFirstName());
			field.setEditable(false);
			panel.add(field);
			topPanel.add(panel);
			panel = new JPanel(new GridLayout(1,2));
			panel.add(new JLabel("  Last Name"));
			field = new JTextField(account.getLastName());
			field.setEditable(false);
			panel.add(field);
			topPanel.add(panel);
			panel = new JPanel(new GridLayout(1,2));
			panel.add(new JLabel("  Ingame Currency"));
			field = new JTextField(account.getCurrency());
			field.setEditable(false);
			panel.add(field);
			topPanel.add(panel);
			
			panel = new JPanel(new GridLayout(1,3));
			//add, edit & delete buttons
			JButton add = new JButton("CREATE ACCOUNT");
			//TODO edit.addActionListener()
			panel.add(add);
			JButton edit = new JButton("EDIT ACCOUNT");
			//TODO edit.addActionListener()
			panel.add(edit);
			JButton delete = new JButton("DELETE ACCOUNT");
			//TODO add.addActionListener()
			panel.add(delete);
			topPanel.add(panel);
			
			//add to bottom panel
			panel = new JPanel();
			panel.add(new JLabel("CHARACTERS OWNED BY ACCOUNT"));
			bottomPanel.add(panel, BorderLayout.NORTH);
			bottomPanel.add(new JScrollBar(),BorderLayout.CENTER);
			
			panel = new JPanel(new GridLayout(1,3));
			JButton addCharacter = new JButton("Create Character");
			panel.add(addCharacter);
			JButton displayCharacter = new JButton("Display Character");
			panel.add(displayCharacter);
			JButton deleteCharacter = new JButton("Delete Character");
			panel.add(deleteCharacter);
			bottomPanel.add(panel,BorderLayout.SOUTH);
			
			//add panels to display
			displayPanel.add(topPanel);
			displayPanel.add(bottomPanel);
	}
	
	protected static void setDisplayPanel(Character character){
		
		displayPanel = new JPanel(new GridLayout(2,1));
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel temp = new JPanel();
		JLabel label = new JLabel("CHARACTER");
		temp.add(label);
		topPanel.add(temp, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(1,2));
		
		
		JPanel topSub = new JPanel(new GridLayout(2,1));
		
		JPanel charSubPanel = new JPanel(new GridLayout(1,2));
		JPanel panel1 = new JPanel(new GridLayout(6,1));
		
		//name
		panel1.add(new JLabel("  Name"));
		JTextField field = new JTextField(character.getName());
		field.setEditable(false);
		panel1.add(field);
		//race
		panel1.add(new JLabel("  Race"));				
		field = new JTextField(character.getRace());
		field.setEditable(false);
		panel1.add(field);
		//model
		panel1.add(new JLabel("  Model"));
		field = new JTextField(character.getModel());
		field.setEditable(false);
		panel1.add(field);
		charSubPanel.add(panel1);
		//add image
		//TODO image
		JLabel image = new JLabel(new ImageIcon("defaultchar.jpg"));
		image.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		charSubPanel.add(image);
		topSub.add(charSubPanel);
		
		panel = new JPanel(new GridLayout(5,1));
		
		JPanel subPanel = new JPanel(new GridLayout(1,2));
		
		//attributes
		subPanel = new JPanel(new GridLayout(1,4));
		subPanel.add(new JLabel("  STR"));
		field = new JTextField(character.getStrength());
		field.setEditable(false);
		subPanel.add(field);
		subPanel.add(new JLabel("  CON"));
		field = new JTextField(character.getConstitution());
		field.setEditable(false);
		subPanel.add(field);
		panel.add(subPanel);
		
		subPanel = new JPanel(new GridLayout(1,4));
		subPanel.add(new JLabel("  AGI"));
		field = new JTextField(character.getAgility());
		field.setEditable(false);
		subPanel.add(field);
		subPanel.add(new JLabel("  DEX"));
		field = new JTextField(character.getDexterity());
		field.setEditable(false);
		subPanel.add(field);
		panel.add(subPanel);
		
		subPanel = new JPanel(new GridLayout(1,4));
		subPanel.add(new JLabel("  INT"));
		field = new JTextField(character.getIntelligence());
		field.setEditable(false);
		subPanel.add(field);
		subPanel.add(new JLabel("  WIS"));
		field = new JTextField(character.getWisdom());
		field.setEditable(false);
		subPanel.add(field);
		panel.add(subPanel);
		
		//account
		subPanel = new JPanel(new GridLayout(1,3));
		subPanel.add(new JLabel("  Account"));
		field = new JTextField(Character.getParent(character.getID()).getAccountName());
		field.setEditable(false);
		subPanel.add(field);
		JButton displayAccount = new JButton("Display Account");
		//TODO display Account
		subPanel.add(displayAccount);
		panel.add(subPanel);

		subPanel = new JPanel(new GridLayout(1,2));
		//edit & delete buttons
		JButton edit = new JButton("EDIT");
		//TODO edit.addActionListener()
		subPanel.add(edit);
		JButton delete = new JButton("DELETE");
		//TODO add.addActionListener()
		subPanel.add(delete);
		panel.add(subPanel);
		
		topSub.add(panel);
		
		topPanel.add(topSub);
		
		JPanel bottomPanel = new JPanel(new GridLayout(1,2));
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		JPanel itemsPanel = new JPanel(new BorderLayout());
		temp = new JPanel();
		label = new JLabel("ITEMS OWNED BY CHARACTER");
		temp.add(label);
		itemsPanel.add(temp, BorderLayout.NORTH);
		itemsPanel.add(new JScrollBar(), BorderLayout.CENTER);
		//TODO display items
		panel = new JPanel(new GridLayout(3,1));
		JButton addItem = new JButton("Add Item");
		//TODO add item
		panel.add(addItem);
		JButton displayItem = new JButton("Display Item");
		//TODO display Item
		panel.add(displayItem);
		JButton removeItem = new JButton("Remove Item");
		//TODO remove Item
		panel.add(removeItem);
		itemsPanel.add(panel, BorderLayout.SOUTH);
		
		JPanel skillsPanel = new JPanel(new BorderLayout());
		temp = new JPanel();
		label = new JLabel("CHARACTER SKILLS");
		temp.add(label);
		skillsPanel.add(temp, BorderLayout.NORTH);
		skillsPanel.add(new JScrollBar(), BorderLayout.CENTER);
		//TODO display skills
		panel = new JPanel(new GridLayout(3,1));
		JButton addSkill = new JButton("Add Skill");
		//TODO add skill
		panel.add(addSkill);
		JButton displaySkill = new JButton("Display Skill");
		//TODO display skill
		panel.add(displaySkill);
		JButton removeSkill = new JButton("Remove Skill");
		//TODO remove skill
		panel.add(removeSkill);
		skillsPanel.add(panel, BorderLayout.SOUTH);
		
		bottomPanel.add(itemsPanel);
		bottomPanel.add(skillsPanel);
		
		displayPanel.add(topPanel);
		displayPanel.add(bottomPanel);
	}
	
	protected static void setDisplayPanel(Item item){
		displayPanel = new JPanel(new GridLayout(2,1));
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel temp = new JPanel();
		JLabel label = new JLabel("ITEM");
		temp.add(label);
		topPanel.add(temp, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(1,2));
		
		
		JPanel topSub = new JPanel(new GridLayout(2,1));
		
		JPanel itemSubPanel = new JPanel(new GridLayout(1,2));
		JPanel panel1 = new JPanel(new GridLayout(6,1));
		
		//name
		panel1.add(new JLabel("  Name"));
		JTextField field = new JTextField(item.getName());
		field.setEditable(false);
		panel1.add(field);
		//race
		panel1.add(new JLabel("  Rarity"));				
		field = new JTextField(item.getRarity());
		field.setEditable(false);
		panel1.add(field);
		//model
		panel1.add(new JLabel("  Model"));
		field = new JTextField(item.getModel());
		field.setEditable(false);
		panel1.add(field);
		itemSubPanel.add(panel1);
		//add image
		JLabel image = new JLabel(new ImageIcon("defaultitem.jpg"));
		//TODO image
		image.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		itemSubPanel.add(image);
		topSub.add(itemSubPanel);
		
		panel = new JPanel(new GridLayout(6,1));
		
		JPanel subPanel = new JPanel(new GridLayout(1,2));
		
		//attributes
		subPanel = new JPanel(new GridLayout(1,2));
		subPanel.add(new JLabel("  Damage"));
		field = new JTextField(item.getDamage());
		field.setEditable(false);
		subPanel.add(field);
		panel.add(subPanel);
		
		subPanel = new JPanel(new GridLayout(1,2));
		subPanel.add(new JLabel("  Armor"));
		field = new JTextField(item.getArmor());
		field.setEditable(false);
		subPanel.add(field);
		panel.add(subPanel);
		
		subPanel = new JPanel(new GridLayout(1,2));
		subPanel.add(new JLabel("  Level Requirement"));
		field = new JTextField(item.getLevel());
		field.setEditable(false);
		subPanel.add(field);
		panel.add(subPanel);
		
		subPanel = new JPanel(new GridLayout(1,2));
		subPanel.add(new JLabel("  Value"));
		field = new JTextField(item.getValue());
		field.setEditable(false);
		subPanel.add(field);
		panel.add(subPanel);
		
		subPanel = new JPanel(new GridLayout(1,3));
		subPanel.add(new JLabel("  Ability"));
		field = new JTextField(item.getAbility().getName());
		field.setEditable(false);
		subPanel.add(field);
		JButton displayAbility = new JButton("Display Ability");
		//TODO display ability
		subPanel.add(displayAbility);
		panel.add(subPanel);
		
		subPanel = new JPanel(new GridLayout(1,3));
		//add edit & delete buttons
		JButton createItem = new JButton("CREATE ITEM");
		//TODO action
		subPanel.add(createItem);
		JButton edit = new JButton("EDIT ITEM");
		//TODO edit.addActionListener()
		subPanel.add(edit);
		JButton delete = new JButton("DELETE ITEM");
		//TODO add.addActionListener()
		subPanel.add(delete);
		panel.add(subPanel);
		
		topSub.add(panel);
		
		topPanel.add(topSub);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		panel = new JPanel();
		panel.add(new JLabel("CHARACTERS WITH THIS ITEM"));
		bottomPanel.add(panel, BorderLayout.NORTH);
		bottomPanel.add(new JScrollBar(),BorderLayout.CENTER);
		//TODO display chars
		JButton displayCharacter = new JButton("Display Character"); 
		//TODO actionListener
		bottomPanel.add(displayCharacter,BorderLayout.SOUTH);
		
		displayPanel.add(topPanel);
		displayPanel.add(bottomPanel);
	}
	
	protected static void setDisplayPanel(Skill skill){
		displayPanel = new JPanel(new GridLayout(2,1));
		JPanel topPanel = new JPanel(new GridLayout(2,1));
		topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel panel = new JPanel(new GridLayout(3,1));
		JPanel temp = new JPanel();
		JLabel label = new JLabel("SKILL");
		temp.add(label);
		panel.add(temp);
		temp = new JPanel(new GridLayout(1,2));
		temp.add(new JLabel("  Name"));
		JTextField field = new JTextField(skill.getName());
		field.setEditable(false);
		temp.add(field);
		panel.add(temp);
		temp = new JPanel(new GridLayout(1,2));
		temp.add(new JLabel("  Level Requirement"));
		field = new JTextField(skill.getLevelRequirement());
		field.setEditable(false);
		temp.add(field);
		panel.add(temp);
		topPanel.add(panel);
		
		panel = new JPanel(new BorderLayout());
		temp = new JPanel();
		label = new JLabel("Description");
		temp.add(label);
		panel.add(temp, BorderLayout.NORTH);
		field = new JTextField(skill.getDescription());
		field.setEditable(false);
		panel.add(field, BorderLayout.CENTER);
		JPanel subPanel = new JPanel(new GridLayout(1,3));
		//create edit & delete buttons
		
		JButton createSkill = new JButton("CREATE SKILL");
		subPanel.add(createSkill);
		JButton edit = new JButton("EDIT SKILL"); //TODO
		//TODO add.addActionListener()
		subPanel.add(edit);
		JButton delete = new JButton("DELETE SKILL"); //TODO
		//TODO add.addActionListener()
		subPanel.add(delete);
		panel.add(subPanel, BorderLayout.SOUTH);
		topPanel.add(panel);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		panel = new JPanel();
		panel.add(new JLabel("CHARACTERS WITH THIS SKILL"));
		bottomPanel.add(panel, BorderLayout.NORTH);
		bottomPanel.add(new JScrollBar(),BorderLayout.CENTER);
		//TODO display chars
		JButton displayCharacter = new JButton("Display Character"); 
		//TODO actionlistener
		bottomPanel.add(displayCharacter,BorderLayout.SOUTH);
		
		displayPanel.add(topPanel);
		displayPanel.add(bottomPanel);
	}
	
	protected static void setDisplayPanel(Ability ability){
		displayPanel = new JPanel(new GridLayout(2,1));
		JPanel topPanel = new JPanel(new GridLayout(2,1));
		topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel panel = new JPanel(new GridLayout(3,1));
		JPanel temp = new JPanel();
		JLabel label = new JLabel("ABILITY");
		temp.add(label);
		panel.add(temp);
		temp = new JPanel(new GridLayout(1,2));
		temp.add(new JLabel("  Name"));
		JTextField field = new JTextField(ability.getName());
		field.setEditable(false);
		temp.add(field);
		panel.add(temp);
		temp = new JPanel(new GridLayout(1,2));
		temp.add(new JLabel("  Level Requirement"));
		field = new JTextField(ability.getLevelRequirement());
		field.setEditable(false);
		temp.add(field);
		panel.add(temp);
		topPanel.add(panel);
		
		panel = new JPanel(new BorderLayout());
		temp = new JPanel();
		label = new JLabel("Description");
		temp.add(label);
		panel.add(temp, BorderLayout.NORTH);
		field = new JTextField(ability.getDescription());
		field.setEditable(false);
		panel.add(field, BorderLayout.CENTER);
		JPanel subPanel = new JPanel(new GridLayout(1,3));
		//add edit & delete buttons
		JButton createAbility = new JButton("CREATE ABILITY");
		//TODO actionlistener
		subPanel.add(createAbility);
		JButton edit = new JButton("EDIT ABILITY"); //TODO
		//TODO edit.addActionListener()
		subPanel.add(edit);
		JButton delete = new JButton("DELETE ABILITY"); //TODO
		//TODO add.addActionListener()
		subPanel.add(delete);
		panel.add(subPanel, BorderLayout.SOUTH);
		topPanel.add(panel);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		panel = new JPanel();
		panel.add(new JLabel("ITEMS WITH THIS ABILITY"));
		bottomPanel.add(panel, BorderLayout.NORTH);
		bottomPanel.add(new JScrollBar(),BorderLayout.CENTER);
		//TODO items with ability
		JButton displayCharacter = new JButton("Display Item"); 
		//TODO display item
		bottomPanel.add(displayCharacter,BorderLayout.SOUTH);
		
		displayPanel.add(topPanel);
		displayPanel.add(bottomPanel);
	}
}
