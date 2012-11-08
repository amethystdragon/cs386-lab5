package gui.panels;

import gui.GUI;
import gui.GUI.ObjectType;
import helpers.Ability;
import helpers.Account;
import helpers.Character;
import helpers.Item;
import helpers.Skill;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

import dataAccess.DataAccess;

public class DisplayPanel {
	private static JPanel displayPanel;

	public static JPanel getDisplayPanel(){
		if(displayPanel == null){
			displayPanel = new JPanel();
			displayPanel.add(new JLabel("TEST"));
		}
		return displayPanel;
	}

	//TODO REDO THIS CALLING THE LOWER METHODS WITH NULLS OR BLANKS
	public static void setDisplayPanel(GUI.ObjectType type){
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
			add.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					try{
						JTextField userNameField = new JTextField(30);
						JTextField passwordField = new JTextField(30);
						JTextField emailField = new JTextField(30);
						JTextField fNameField = new JTextField(30);
						JTextField lNameField = new JTextField(30);
						JTextField currencyField = new JTextField(30);

						JPanel myPanel = new JPanel(new GridLayout(6,2));
						myPanel.add(new JLabel("UserName:"));
						myPanel.add(userNameField);
						myPanel.add(new JLabel("Password:"));
						myPanel.add(passwordField);
						myPanel.add(new JLabel("Email:"));
						myPanel.add(emailField);
						myPanel.add(new JLabel("First Name:"));
						myPanel.add(fNameField);
						myPanel.add(new JLabel("Last Name:"));
						myPanel.add(lNameField);
						myPanel.add(new JLabel("Currency:"));
						myPanel.add(currencyField);

						int result = JOptionPane.showConfirmDialog(null, myPanel, 
								"Please Enter Account to Create", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION && !userNameField.getText().isEmpty() && !passwordField.getText().isEmpty() &&
								!emailField.getText().isEmpty() && !fNameField.getText().isEmpty() &&
								!lNameField.getText().isEmpty() && !currencyField.getText().isEmpty()) {
							Account addAccount = new Account(
									userNameField.getText(), passwordField.getText(),
									emailField.getText(), fNameField.getText(),
									lNameField.getText(), Integer.parseInt(currencyField.getText()));
							if(DataAccess.getInstance().addUser(addAccount)){
								DisplayPanel.setDisplayPanel(addAccount);
								ResultsPanel.setResultsPanel(DataAccess.getInstance().searchUser("", "", "", ""));
							}
							else{
								JOptionPane.showMessageDialog(null, "ERROR: Account not added!");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "ERROR: Account not added!");
						}
						GUI.getGUI().updateMainPanel();
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "ERROR: Account not added!");
					}
				}
			});
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
			bottomPanel.add(new JPanel(),BorderLayout.CENTER);
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
			JLabel image = new JLabel(new ImageIcon("images/defaultchar.jpg"));
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
			itemsPanel.add(new JPanel(), BorderLayout.CENTER);
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
			skillsPanel.add(new JPanel(), BorderLayout.CENTER);
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
			createAbility.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					try{
						JTextField abilityNameField = new JTextField(30);
						JTextField abilityDescriptionField = new JTextField(45);
						JTextField levelReqField = new JTextField(30);

						JPanel myPanel = new JPanel(new GridLayout(3,2));
						myPanel.add(new JLabel("Ability Name:"));
						myPanel.add(abilityNameField);
						myPanel.add(new JLabel("Description:"));
						myPanel.add(abilityDescriptionField);
						myPanel.add(new JLabel("Level Requirement:"));
						myPanel.add(levelReqField);

						int result = JOptionPane.showConfirmDialog(null, myPanel, 
								"Please Enter Ability to Create", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							Ability addAbility = new Ability(
									abilityNameField.getText(), abilityDescriptionField.getText(),
									Integer.parseInt(levelReqField.getText()));
							if(DataAccess.getInstance().addAbility(addAbility)){
								DisplayPanel.setDisplayPanel(addAbility);
								ResultsPanel.setResultsPanel(DataAccess.getInstance().searchAbility("", -1, ""));
							}
							else{
								JOptionPane.showMessageDialog(null, "ERROR: Ability not created!");
							}
						}
						GUI.getGUI().updateMainPanel();
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "ERROR: Ability not created!");
					}
				}
			});
			subPanel.add(createAbility);
			edit = new JButton("EDIT ABILITY");
			subPanel.add(edit);
			delete = new JButton("DELETE ABILITY");
			subPanel.add(delete);
			panel.add(subPanel, BorderLayout.SOUTH);
			topPanel.add(panel);

			bottomPanel = new JPanel(new BorderLayout());
			bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));

			panel = new JPanel();
			panel.add(new JLabel("ITEMS WITH THIS ABILITY"));
			bottomPanel.add(panel, BorderLayout.NORTH);
			bottomPanel.add(new JPanel(),BorderLayout.CENTER);

			displayCharacter = new JButton("Display Item");
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
			image = new JLabel(new ImageIcon("images/defaultitem.jpg"));
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
			createItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					try{
						JTextField itemNameField = new JTextField(30);
						JTextField damageField = new JTextField(30);
						JTextField armorField = new JTextField(30);
						JTextField levelReqField = new JTextField(30);
						JComboBox<Item.Rarity> rarityBox = new JComboBox<Item.Rarity>();
						for(Item.Rarity rarity: Item.Rarity.values()){
							rarityBox.addItem(rarity);
						}
						JTextField valueField = new JTextField(30);
						JTextField modelField = new JTextField(30);
						
						JComboBox<String> abilityBox = new JComboBox<String>();
						List<Ability> abillist = DataAccess.getInstance().searchAbility("", -1, "");
						for(Ability ability : abillist){
							abilityBox.addItem(ability.getName());
						}
						
						JPanel myPanel = new JPanel(new GridLayout(8,2));
						myPanel.add(new JLabel("Item Name:"));
						myPanel.add(itemNameField);
						myPanel.add(new JLabel("Damage:"));
						myPanel.add(damageField);
						myPanel.add(new JLabel("Armor:"));
						myPanel.add(armorField);
						myPanel.add(new JLabel("Level Requirement:"));
						myPanel.add(levelReqField);
						myPanel.add(new JLabel("Rarity:"));
						myPanel.add(rarityBox);
						myPanel.add(new JLabel("Value:"));
						myPanel.add(valueField);
						myPanel.add(new JLabel("Model:"));
						myPanel.add(modelField);
						myPanel.add(new JLabel("Ability"));
						myPanel.add(abilityBox);

						int result = JOptionPane.showConfirmDialog(null, myPanel, 
								"Please Enter Item to Create", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION && !itemNameField.getText().isEmpty() &&
								!damageField.getText().isEmpty() && !armorField.getText().isEmpty() &&
								!levelReqField.getText().isEmpty() && rarityBox.getSelectedItem() != null
								&& !valueField.getText().isEmpty() && !modelField.getText().isEmpty() &&
								abilityBox.getSelectedItem() != null) {
							Item addItem = new Item(
									itemNameField.getText(), Integer.parseInt(damageField.getText()),
									Integer.parseInt(armorField.getText()), Integer.parseInt(levelReqField.getText()),
									Item.Rarity.class.cast(rarityBox.getSelectedItem()), Integer.parseInt(valueField.getText()),
									modelField.getText(), abilityBox.getSelectedItem().toString());
							if(DataAccess.getInstance().addItem(addItem)){
								DisplayPanel.setDisplayPanel(addItem);
								ResultsPanel.setResultsPanel(DataAccess.getInstance().searchItem("", null, ""));
							}
							else{
								JOptionPane.showMessageDialog(null, "ERROR: Item not created!");
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "ERROR: Item not created!");
						}
						GUI.getGUI().updateMainPanel();
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "ERROR: Item not created!");
					}
				}
			});
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
			bottomPanel.add(new JPanel(),BorderLayout.CENTER);

			displayCharacter = new JButton("Display Character"); 
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
			createSkill.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					try{
						JTextField skillNameField = new JTextField(30);
						JTextField skillDescriptionField = new JTextField(45);
						JTextField levelReqField = new JTextField(30);

						JPanel myPanel = new JPanel(new GridLayout(3,2));
						myPanel.add(new JLabel("Skill Name:"));
						myPanel.add(skillNameField);
						myPanel.add(new JLabel("Description:"));
						myPanel.add(skillDescriptionField);
						myPanel.add(new JLabel("Level Requirement:"));
						myPanel.add(levelReqField);

						int result = JOptionPane.showConfirmDialog(null, myPanel, 
								"Please Enter Skill to Create", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							Skill addSkill = new Skill(
									skillNameField.getText(), skillDescriptionField.getText(),
									Integer.parseInt(levelReqField.getText()));
							if(DataAccess.getInstance().addSkill(addSkill)){
								DisplayPanel.setDisplayPanel(addSkill);
								ResultsPanel.setResultsPanel(DataAccess.getInstance().searchSkill("", -1, ""));
							}
							else{
								JOptionPane.showMessageDialog(null, "ERROR: Skill not created!");
							}
						}
						GUI.getGUI().updateMainPanel();
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "ERROR: Skill not created!");
					}
				}
			});
			subPanel.add(createSkill);
			edit = new JButton("EDIT SKILL");
			subPanel.add(edit);
			delete = new JButton("DELETE SKILL");
			subPanel.add(delete);
			panel.add(subPanel, BorderLayout.SOUTH);
			topPanel.add(panel);

			bottomPanel = new JPanel(new BorderLayout());
			bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));

			panel = new JPanel();
			panel.add(new JLabel("CHARACTERS WITH THIS SKILL"));
			bottomPanel.add(panel, BorderLayout.NORTH);
			bottomPanel.add(new JPanel(),BorderLayout.CENTER);

			displayCharacter = new JButton("Display Character");
			bottomPanel.add(displayCharacter,BorderLayout.SOUTH);

			displayPanel.add(topPanel);
			displayPanel.add(bottomPanel);
			break;
		}
	}

	protected static void setDisplayPanel(Account account){
		final Account thisAccount = account;
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
		field = new JTextField(""+((account.getCurrency()<0)?"":account.getCurrency()));
		field.setEditable(false);
		panel.add(field);
		topPanel.add(panel);

		panel = new JPanel(new GridLayout(1,3));
		//add, edit & delete buttons
		JButton add = new JButton("CREATE ACCOUNT");
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try{
					JTextField userNameField = new JTextField(30);
					JTextField passwordField = new JTextField(30);
					JTextField emailField = new JTextField(30);
					JTextField fNameField = new JTextField(30);
					JTextField lNameField = new JTextField(30);
					JTextField currencyField = new JTextField(30);

					JPanel myPanel = new JPanel(new GridLayout(6,2));
					myPanel.add(new JLabel("UserName:"));
					myPanel.add(userNameField);
					myPanel.add(new JLabel("Password:"));
					myPanel.add(passwordField);
					myPanel.add(new JLabel("Email:"));
					myPanel.add(emailField);
					myPanel.add(new JLabel("First Name:"));
					myPanel.add(fNameField);
					myPanel.add(new JLabel("Last Name:"));
					myPanel.add(lNameField);
					myPanel.add(new JLabel("Currency:"));
					myPanel.add(currencyField);

					int result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Please Enter Account to Create", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION  && !userNameField.getText().isEmpty() && !passwordField.getText().isEmpty() &&
							!emailField.getText().isEmpty() && !fNameField.getText().isEmpty() &&
							!lNameField.getText().isEmpty() && !currencyField.getText().isEmpty()) {
						Account addAccount = new Account(
								userNameField.getText(), passwordField.getText(),
								emailField.getText(), fNameField.getText(),
								lNameField.getText(), Integer.parseInt(currencyField.getText()));
						if(DataAccess.getInstance().addUser(addAccount)){
							DisplayPanel.setDisplayPanel(addAccount);
							ResultsPanel.setResultsPanel(DataAccess.getInstance().searchUser("", "", "", ""));
						}
						else{
							JOptionPane.showMessageDialog(null, "ERROR: Account not created!");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "ERROR: Account not created!");
					}
					GUI.getGUI().updateMainPanel();
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "ERROR: Account not created!");
				}
			}
		});
		panel.add(add);
		JButton edit = new JButton("EDIT ACCOUNT");
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try{
					JTextField userNameField = new JTextField(30);
					userNameField.setEditable(false);
					userNameField.setText(thisAccount.getAccountName());
					JTextField passwordField = new JTextField(30);
					passwordField.setText(thisAccount.getPassword());
					JTextField emailField = new JTextField(30);
					emailField.setText(thisAccount.getEmail());
					JTextField fNameField = new JTextField(30);
					fNameField.setText(thisAccount.getFirstName());
					JTextField lNameField = new JTextField(30);
					lNameField.setText(thisAccount.getLastName());
					JTextField currencyField = new JTextField(30);
					currencyField.setText(Integer.toString(thisAccount.getCurrency()));

					JPanel myPanel = new JPanel(new GridLayout(6,2));
					myPanel.add(new JLabel("UserName:"));
					myPanel.add(userNameField);
					myPanel.add(new JLabel("Password:"));
					myPanel.add(passwordField);
					myPanel.add(new JLabel("Email:"));
					myPanel.add(emailField);
					myPanel.add(new JLabel("First Name:"));
					myPanel.add(fNameField);
					myPanel.add(new JLabel("Last Name:"));
					myPanel.add(lNameField);
					myPanel.add(new JLabel("Currency:"));
					myPanel.add(currencyField);

					int result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Please Enter Account Information to Edit", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION  && !userNameField.getText().isEmpty() && !passwordField.getText().isEmpty() &&
							!emailField.getText().isEmpty() && !fNameField.getText().isEmpty() &&
							!lNameField.getText().isEmpty() && !currencyField.getText().isEmpty()) {
						Account editAccount = new Account(
								userNameField.getText(), passwordField.getText(),
								emailField.getText(), fNameField.getText(),
								lNameField.getText(), Integer.parseInt(currencyField.getText()));
						if(DataAccess.getInstance().editUser(editAccount)){
							DisplayPanel.setDisplayPanel(editAccount);
							ResultsPanel.setResultsPanel(DataAccess.getInstance().searchUser("", "", "", ""));
						}
						else{
							JOptionPane.showMessageDialog(null, "ERROR: Account not edited!");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "ERROR: Account not created!");
					}
					GUI.getGUI().updateMainPanel();
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "ERROR: Account not edited!");
				}
			}
		});
		panel.add(edit);
		JButton delete = new JButton("DELETE ACCOUNT");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try{
					if(DataAccess.getInstance().deleteUser(thisAccount.getAccountName())){
						DisplayPanel.setDisplayPanel(ObjectType.ACCOUNT);
						ResultsPanel.setResultsPanel(DataAccess.getInstance().searchUser("", "", "", ""));
						GUI.getGUI().updateMainPanel();
					}
					else{
						JOptionPane.showMessageDialog(null, "ERROR: Account not deleted!");
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "ERROR: Account not deleted!");
				}
			}
		});
		panel.add(delete);
		topPanel.add(panel);

		//add to bottom panel
		panel = new JPanel();
		panel.add(new JLabel("CHARACTERS OWNED BY ACCOUNT"));
		bottomPanel.add(panel, BorderLayout.NORTH);
		bottomPanel.add(new JScrollBar(),BorderLayout.CENTER);
		//TODO character owned by account bar
		
		panel = new JPanel(new GridLayout(1,3));
		JButton addCharacter = new JButton("Create Character");
		addCharacter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try{
					JTextField charNameField = new JTextField(30);
					
					JTextField passwordField = new JTextField(30);
					JTextField emailField = new JTextField(30);
					JTextField fNameField = new JTextField(30);
					JTextField lNameField = new JTextField(30);
					JTextField currencyField = new JTextField(30);

					JPanel myPanel = new JPanel(new GridLayout(6,2));
					myPanel.add(new JLabel("Character Name:"));
					myPanel.add(charNameField);
					myPanel.add(new JLabel("Password:"));
					myPanel.add(passwordField);
					myPanel.add(new JLabel("Email:"));
					myPanel.add(emailField);
					myPanel.add(new JLabel("First Name:"));
					myPanel.add(fNameField);
					myPanel.add(new JLabel("Last Name:"));
					myPanel.add(lNameField);
					myPanel.add(new JLabel("Currency:"));
					myPanel.add(currencyField);

					int result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Please Enter Account to Create", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						Account addAccount = new Account(
								charNameField.getText(), passwordField.getText(),
								emailField.getText(), fNameField.getText(),
								lNameField.getText(), Integer.parseInt(currencyField.getText()));
						if(DataAccess.getInstance().addUser(addAccount)){
							DisplayPanel.setDisplayPanel(addAccount);
							ResultsPanel.setResultsPanel(DataAccess.getInstance().searchUser("", "", "", ""));
						}
						else{
							JOptionPane.showMessageDialog(null, "ERROR: Account not created!");
						}
					}
					GUI.getGUI().updateMainPanel();
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "ERROR: Account not created!");
				}
			}
		});
		panel.add(addCharacter);
		JButton displayCharacter = new JButton("Display Character");
		panel.add(displayCharacter);
		JButton deleteCharacter = new JButton("Delete Character");
		panel.add(deleteCharacter);
		bottomPanel.add(panel,BorderLayout.SOUTH);

		//add panels to display
		displayPanel.add(topPanel);
		displayPanel.add(bottomPanel);
		GUI.getGUI().updateMainPanel();
	}


	protected static void setDisplayPanel(Character character){
		final Character thisCharacter = character;
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		JPanel temp = new JPanel();
		JLabel label = new JLabel("CHARACTER");
		temp.add(label);
		topPanel.add(temp, BorderLayout.NORTH);



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

		JPanel panel = new JPanel(new GridLayout(5,1));

		JPanel subPanel = new JPanel(new GridLayout(1,2));

		//attributes
		subPanel = new JPanel(new GridLayout(1,4));
		subPanel.add(new JLabel("  STR"));
		field = new JTextField(""+character.getStrength());
		field.setEditable(false);
		subPanel.add(field);
		subPanel.add(new JLabel("  CON"));
		field = new JTextField(""+character.getConstitution());
		field.setEditable(false);
		subPanel.add(field);
		panel.add(subPanel);

		subPanel = new JPanel(new GridLayout(1,4));
		subPanel.add(new JLabel("  AGI"));
		field = new JTextField(""+character.getAgility());
		field.setEditable(false);
		subPanel.add(field);
		subPanel.add(new JLabel("  DEX"));
		field = new JTextField(""+character.getDexterity());
		field.setEditable(false);
		subPanel.add(field);
		panel.add(subPanel);

		subPanel = new JPanel(new GridLayout(1,4));
		subPanel.add(new JLabel("  INT"));
		field = new JTextField(""+character.getIntelligence());
		field.setEditable(false);
		subPanel.add(field);
		subPanel.add(new JLabel("  WIS"));
		field = new JTextField(""+character.getWisdom());
		field.setEditable(false);
		subPanel.add(field);
		panel.add(subPanel);

		//account
		subPanel = new JPanel(new GridLayout(1,3));
		subPanel.add(new JLabel("  Account"));
		field = new JTextField(character.getAccountUsername());
		field.setEditable(false);
		subPanel.add(field);
		JButton displayAccount = new JButton("Display Account");
		//TODO
		subPanel.add(displayAccount);
		panel.add(subPanel);

		subPanel = new JPanel(new GridLayout(1,2));
		//edit & delete buttons
		JButton edit = new JButton("EDIT");
		//TODO edit.addActionListener()
		subPanel.add(edit);
		JButton delete = new JButton("DELETE");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try{
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		subPanel.add(delete);
		panel.add(subPanel);

		topSub.add(panel);

		topPanel.add(topSub);

		JPanel bottomPanel = new JPanel(new GridLayout(1,2));
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		JPanel itemsPanel = new JPanel(new BorderLayout());
		JPanel temp1 = new JPanel();
		label = new JLabel("ITEMS OWNED BY CHARACTER");
		temp1.add(label);
		itemsPanel.add(temp1, BorderLayout.NORTH);
		itemsPanel.add(new JScrollBar(), BorderLayout.CENTER);
		//TODO display items
		JPanel panel2 = new JPanel(new GridLayout(3,1));
		JButton addItem = new JButton("Add Item");
		//TODO add item
		panel2.add(addItem);
		JButton displayItem = new JButton("Display Item");
		//TODO display Item
		panel2.add(displayItem);
		JButton removeItem = new JButton("Remove Item");
		//TODO remove Item
		panel2.add(removeItem);
		itemsPanel.add(panel2, BorderLayout.SOUTH);

		JPanel skillsPanel = new JPanel(new BorderLayout());
		JPanel temp2 = new JPanel();
		label = new JLabel("CHARACTER SKILLS");
		temp2.add(label);
		skillsPanel.add(temp2, BorderLayout.NORTH);
		skillsPanel.add(new JScrollBar(), BorderLayout.CENTER);
		//TODO display skills
		panel2 = new JPanel(new GridLayout(3,1));
		JButton addSkill = new JButton("Add Skill");
		//TODO add skill
		panel2.add(addSkill);
		JButton displaySkill = new JButton("Display Skill");
		//TODO display skill
		panel2.add(displaySkill);
		JButton removeSkill = new JButton("Remove Skill");
		//TODO remove skill
		panel2.add(removeSkill);
		skillsPanel.add(panel2, BorderLayout.SOUTH);

		bottomPanel.add(itemsPanel);
		bottomPanel.add(skillsPanel);

		displayPanel = new JPanel(new GridLayout(2,1));
		displayPanel.add(topPanel, 0);
		displayPanel.add(bottomPanel, 1);
		GUI.getGUI().updateMainPanel();
	}

	protected static void setDisplayPanel(Item item){
		final Item thisItem = item;
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
		field = new JTextField(item.getAbility());
		field.setEditable(false);
		subPanel.add(field);
		JButton displayAbility = new JButton("Display Ability");
		//TODO display ability
		subPanel.add(displayAbility);
		panel.add(subPanel);

		subPanel = new JPanel(new GridLayout(1,3));
		//add edit & delete buttons
		JButton createItem = new JButton("CREATE ITEM");
		createItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try{
					JTextField itemNameField = new JTextField(30);
					JTextField damageField = new JTextField(30);
					JTextField armorField = new JTextField(30);
					JTextField levelReqField = new JTextField(30);
					JComboBox<Item.Rarity> rarityBox = new JComboBox<Item.Rarity>();
					for(Item.Rarity rarity: Item.Rarity.values()){
						rarityBox.addItem(rarity);
					}
					JTextField valueField = new JTextField(30);
					JTextField modelField = new JTextField(30);

					JPanel myPanel = new JPanel(new GridLayout(7,2));
					myPanel.add(new JLabel("Item Name:"));
					myPanel.add(itemNameField);
					myPanel.add(new JLabel("Damage:"));
					myPanel.add(damageField);
					myPanel.add(new JLabel("Armor:"));
					myPanel.add(armorField);
					myPanel.add(new JLabel("Level Requirement:"));
					myPanel.add(levelReqField);
					myPanel.add(new JLabel("Rarity:"));
					myPanel.add(rarityBox);
					myPanel.add(new JLabel("Value:"));
					myPanel.add(valueField);
					myPanel.add(new JLabel("Model:"));
					myPanel.add(modelField);

					int result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Please Enter Item to Create", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						Item addItem = new Item(
								itemNameField.getText(), Integer.parseInt(damageField.getText()),
								Integer.parseInt(armorField.getText()), Integer.parseInt(levelReqField.getText()),
								Item.Rarity.class.cast(rarityBox.getSelectedItem()), Integer.parseInt(valueField.getText()),
								modelField.getText(), null);
						if(DataAccess.getInstance().addItem(addItem)){
							DisplayPanel.setDisplayPanel(addItem);
						}
					}
					GUI.getGUI().updateMainPanel();
				}catch(Exception e){}
			}
		});
		subPanel.add(createItem);
		JButton edit = new JButton("EDIT ITEM");
		//TODO edit.addActionListener()
		subPanel.add(edit);
		JButton delete = new JButton("DELETE ITEM");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try{
					DataAccess.getInstance().deleteItem(thisItem.getName());
					DisplayPanel.setDisplayPanel(GUI.ObjectType.ITEM);
					//ResultsPanel.setResultsPanel(); //TODO
					GUI.getGUI().updateMainPanel();
				}catch(Exception e){}
			}
		});
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
		GUI.getGUI().updateMainPanel();
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
		field = new JTextField(""+skill.getLevelRequirement());
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
		createSkill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try{
					JTextField skillNameField = new JTextField(30);
					JTextField skillDescriptionField = new JTextField(45);
					JTextField levelReqField = new JTextField(30);

					JPanel myPanel = new JPanel(new GridLayout(3,2));
					myPanel.add(new JLabel("Skill Name:"));
					myPanel.add(skillNameField);
					myPanel.add(new JLabel("Description:"));
					myPanel.add(skillDescriptionField);
					myPanel.add(new JLabel("Level Requirement:"));
					myPanel.add(levelReqField);

					int result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Please Enter Skill to Create", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						Skill addSkill = new Skill(
								skillNameField.getText(), skillDescriptionField.getText(),
								Integer.parseInt(levelReqField.getText()));
						if(DataAccess.getInstance().addSkill(addSkill)){
							DisplayPanel.setDisplayPanel(addSkill);
						}
					}
					GUI.getGUI().updateMainPanel();
				}catch(Exception e){}
			}
		});
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
		GUI.getGUI().updateMainPanel();
	}

	protected static void setDisplayPanel(final Ability ability){
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
		field = new JTextField(""+ability.getLevelRequirement());
		field.setEditable(false);
		temp.add(field);
		panel.add(temp);
		topPanel.add(panel);

		panel = new JPanel(new BorderLayout());
		temp = new JPanel();
		label = new JLabel("Description");
		temp.add(label);
		panel.add(temp, BorderLayout.NORTH);
		//TODO CHANGE ALL TO TEXT AREAS
		field = new JTextField(ability.getDescription());
		field.setEditable(false);
		panel.add(field, BorderLayout.CENTER);
		JPanel subPanel = new JPanel(new GridLayout(1,3));
		//add edit & delete buttons
		JButton createAbility = new JButton("CREATE ABILITY");
		createAbility.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try{
					JTextField abilityNameField = new JTextField(30);
					JTextField abilityDescriptionField = new JTextField(45);
					JTextField levelReqField = new JTextField(30);

					JPanel myPanel = new JPanel(new GridLayout(3,2));
					myPanel.add(new JLabel("Ability Name:"));
					myPanel.add(abilityNameField);
					myPanel.add(new JLabel("Description:"));
					myPanel.add(abilityDescriptionField);
					myPanel.add(new JLabel("Level Requirement:"));
					myPanel.add(levelReqField);

					int result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Please Enter Ability to Create", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						Ability addAbility = new Ability(
								abilityNameField.getText(), abilityDescriptionField.getText(),
								Integer.parseInt(levelReqField.getText()));
						if(DataAccess.getInstance().addAbility(addAbility)){
							DisplayPanel.setDisplayPanel(addAbility);
						}
					}
					GUI.getGUI().updateMainPanel();
				}catch(Exception e){}
			}
		});
		subPanel.add(createAbility);
		//Adds the edit ability button
		JButton edit = new JButton("EDIT ABILITY");
		createAbility.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try{
					JTextField abilityNameField = new JTextField(30);
					abilityNameField.setEditable(false);
					abilityNameField.setText(ability.getName());
					JTextField abilityDescriptionField = new JTextField(45);
					abilityDescriptionField.setText(ability.getDescription());
					JTextField levelReqField = new JTextField(30);
					levelReqField.setText(ability.getLevelRequirement()+"");

					JPanel myPanel = new JPanel(new GridLayout(3,2));
					myPanel.add(new JLabel("Ability Name:"));
					myPanel.add(abilityNameField);
					myPanel.add(new JLabel("Description:"));
					myPanel.add(abilityDescriptionField);
					myPanel.add(new JLabel("Level Requirement:"));
					myPanel.add(levelReqField);

					int result = JOptionPane.showConfirmDialog(null, myPanel, 
							"Please Enter Ability to Create", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						Ability addAbility = new Ability(
								abilityNameField.getText(), abilityDescriptionField.getText(),
								Integer.parseInt(levelReqField.getText()));
						if(DataAccess.getInstance().editAbility(ability.getName(),addAbility)){
							DisplayPanel.setDisplayPanel(addAbility);
						}
					}
					GUI.getGUI().updateMainPanel();
				}catch(Exception e){}
			}
		});
		subPanel.add(edit);
		//Adds the delete ability button
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
		GUI.getGUI().updateMainPanel();
	}
}
