package main;

import gui.ability.AddAbility;
import gui.ability.DeleteAbility;
import gui.ability.EditAbility;
import gui.character.AddCharacter;
import gui.character.DeleteCharacter;
import gui.character.EditCharacter;
import gui.item.AddItem;
import gui.item.DeleteItem;
import gui.item.EditItem;
import gui.skill.AddSkill;
import gui.skill.DeleteSkill;
import gui.skill.EditSkill;
import gui.user.AddUser;
import gui.user.DeleteUser;
import gui.user.EditUser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Main user interface for the timeshare database.
 * 
 */
public class HerosWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel center = null;

	public HerosWindow() {
		//Setup the window
		super("Hero's Scape");
		this.setSize(1500, 1000);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//Adds the menu bar
		generateMenu();
		this.add(createSideBar(),BorderLayout.WEST);
		//Add the image
		ImageIcon image = new ImageIcon("opening.jpg");
		center = new JPanel(new GridLayout(1,1));
		center.setBackground(new Color(255,255,240));
		center.add(new JLabel(image));
		this.add(center, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	private void generateMenu(){
		JMenuBar menuBar = new JMenuBar();
		
		//Creates the file menu
		JMenu file = new JMenu("File");
		//Adds a import data option
		JMenuItem importFromFile = new JMenuItem("Import");
		importFromFile.setActionCommand("Import");
		importFromFile.addActionListener(new MenuListener());
		file.add(importFromFile);
		//Adds a save data option
		JMenuItem save = new JMenuItem("Save");
		save.setActionCommand("Save");
		save.addActionListener(new MenuListener());
		file.add(save);
		file.addSeparator(); //Gotta keep 'em seperated.
		//Adds an exit option
		JMenuItem exit = new JMenuItem("Exit");
		exit.setActionCommand("Exit");
		exit.addActionListener(new MenuListener());
		file.add(exit);
		menuBar.add(file);
		
		//Creates the tools menu
		JMenu tools = new JMenu("Tools");
		//Creates the user sub-menu
		tools.add(createMenus("User"));
		//Creates the character sub-menu
		tools.add(createMenus("Character"));
		//Creates the item sub-menu
		tools.add(createMenus("Item"));
		//Creates the skils sub-menu
		tools.add(createMenus("Skills"));
		//Creates the user sub-menu
		tools.add(createMenus("Ability"));
		//Add to the enubar
		menuBar.add(tools);
		
		//Adds the menubar to the window
		this.setJMenuBar(menuBar);
	}

	private JMenu createMenus(String name) {
		JMenu menu = new JMenu(name);
		//Creates the add button
		JMenuItem add = new JMenuItem("Add "+name);
		add.addActionListener(new MenuListener());
		add.setActionCommand("Add"+name);
		menu.add(add);
		//Creates the edit button
		JMenuItem edit = new JMenuItem("Edit "+name);
		edit.addActionListener(new MenuListener());
		edit.setActionCommand("Edit"+name);
		menu.add(edit);
		//Creates the delete button
		JMenuItem delete = new JMenuItem("Delete "+name);
		delete.addActionListener(new MenuListener());
		delete.setActionCommand("Delete"+name);
		menu.add(delete);
		
		return menu;
	}
	
	private JPanel createSideBar(){
		JPanel sideBar = new JPanel(new GridLayout(30, 1));
		sideBar.setBackground(new Color(255,255,124));
		sideBar.setSize(200, 500);
		
		//Adds user label
		JLabel user = new JLabel("User");
		sideBar.add(user);
		//Adds the user functions
		sideBar.add(functions("User"));
		
		
		//Adds character functions
		JLabel character = new JLabel("Character");
		sideBar.add(character);
		//Adds the user functions
		sideBar.add(functions("Character"));
		
		//Adds item functions
		JLabel item = new JLabel("Item");
		sideBar.add(item);
		//Adds the user functions
		sideBar.add(functions("Item"));		
		
		//Adds skill functions
		JLabel skill = new JLabel("Skill");
		sideBar.add(skill);
		//Adds the user functions
		sideBar.add(functions("Skill"));		
		
		//Adds ability functions
		JLabel ability = new JLabel("Ability");
		sideBar.add(ability);
		//Adds the user functions
		sideBar.add(functions("Ability"));		
		
		return sideBar;
	}
	
	private JPanel functions(String name){
		//Add function
		JPanel func = new JPanel(new GridLayout(1, 3));
		JButton add = new JButton("Add");
		add.addActionListener(new ButtonListener());
		add.setActionCommand("Add"+name);
		func.add(add);
		//Edit function
		JButton edit = new JButton("Edit");
		edit.addActionListener(new ButtonListener());
		edit.setActionCommand("Edit"+name);
		func.add(edit);
		//Delete function
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ButtonListener());
		delete.setActionCommand("Delete"+name);
		func.add(delete);
		return func;
	}
	
	private class MenuListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			switch(command){
			case "AddUser":
				center.removeAll();
				center.add(new AddUser(), BorderLayout.CENTER);
				break;
			case "EditUser":
				center.removeAll();
				center.add(new EditUser(), BorderLayout.CENTER);
				break;
			case "DeleteUser":
				center.removeAll();
				center.add(new DeleteUser(), BorderLayout.CENTER);
				break;
			case "AddCharacter":
				center.removeAll();
				center.add(new AddCharacter(), BorderLayout.CENTER);
				break;
			case "EditCharacter":
				center.removeAll();
				center.add(new EditCharacter(), BorderLayout.CENTER);
				break;
			case "DeleteCharacter":
				center.removeAll();
				center.add(new DeleteCharacter(), BorderLayout.CENTER);
				break;
			case "AddItem":
				center.removeAll();
				center.add(new AddItem(), BorderLayout.CENTER);
				break;
			case "EditItem":
				center.removeAll();
				center.add(new EditItem(), BorderLayout.CENTER);
				break;
			case "DeleteItem":
				center.removeAll();
				center.add(new DeleteItem(), BorderLayout.CENTER);
				break;
			case "AddAbility":
				center.removeAll();
				center.add(new AddAbility(), BorderLayout.CENTER);
				break;
			case "EditAbility":
				center.removeAll();
				center.add(new EditAbility(), BorderLayout.CENTER);
				break;
			case "DeleteAbility":
				center.removeAll();
				center.add(new DeleteAbility(), BorderLayout.CENTER);
				break;
			case "AddSkill":
				center.removeAll();
				center.add(new AddSkill(), BorderLayout.CENTER);
				break;
			case "EditSkill":
				center.removeAll();
				center.add(new EditSkill(), BorderLayout.CENTER);
				break;
			case "DeleteSkill":
				center.removeAll();
				center.add(new DeleteSkill(), BorderLayout.CENTER);
				break;
			case "Exit":
				System.exit(0);
				break;
			case "Save":
				JOptionPane.showMessageDialog(null, "This functionallity is not completed at this time");
				break;
			case "Import":
				JOptionPane.showMessageDialog(null, "This functionallity is not completed at this time");
				break;
			default:
				break;
			}
			validate();
		}
	}
	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			switch(command){
			case "AddUser":
				center.removeAll();
				center.add(new AddUser(), BorderLayout.CENTER);
				break;
			case "EditUser":
				center.removeAll();
				center.add(new EditUser(), BorderLayout.CENTER);
				break;
			case "DeleteUser":
				center.removeAll();
				center.add(new DeleteUser(), BorderLayout.CENTER);
				break;
			case "AddCharacter":
				center.removeAll();
				center.add(new AddCharacter(), BorderLayout.CENTER);
				break;
			case "EditCharacter":
				center.removeAll();
				center.add(new EditCharacter(), BorderLayout.CENTER);
				break;
			case "DeleteCharacter":
				center.removeAll();
				center.add(new DeleteCharacter(), BorderLayout.CENTER);
				break;
			case "AddItem":
				center.removeAll();
				center.add(new AddItem(), BorderLayout.CENTER);
				break;
			case "EditItem":
				center.removeAll();
				center.add(new EditItem(), BorderLayout.CENTER);
				break;
			case "DeleteItem":
				center.removeAll();
				center.add(new DeleteItem(), BorderLayout.CENTER);
				break;
			case "AddAbility":
				center.removeAll();
				center.add(new AddAbility(), BorderLayout.CENTER);
				break;
			case "EditAbility":
				center.removeAll();
				center.add(new EditAbility(), BorderLayout.CENTER);
				break;
			case "DeleteAbility":
				center.removeAll();
				center.add(new DeleteAbility(), BorderLayout.CENTER);
				break;
			case "AddSkill":
				center.removeAll();
				center.add(new AddSkill(), BorderLayout.CENTER);
				break;
			case "EditSkill":
				center.removeAll();
				center.add(new EditSkill(), BorderLayout.CENTER);
				break;
			case "DeleteSkill":
				center.removeAll();
				center.add(new DeleteSkill(), BorderLayout.CENTER);
				break;
			default:
				break;
			}
			validate();
		}
	}
}
