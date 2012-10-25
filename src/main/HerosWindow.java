package main;

import gui.user.AddUser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Main user interface for the timeshare database.
 * 
 */
public class HerosWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public HerosWindow() {
		//Setup the window
		super("Hero's Scape");
		this.setSize(1500, 1000);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//Adds the menu bar
		generateMenu();
		this.add(createSideBar(),BorderLayout.WEST);
		this.add(new AddUser(), BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	private void generateMenu(){
		JMenuBar menuBar = new JMenuBar();
		
		//Creates the file menu
		JMenu file = new JMenu("File");
		JMenuItem save = new JMenuItem("Save");
		file.add(save);
		file.addSeparator(); //Gotta keep 'em seperated.
		JMenuItem exit = new JMenuItem("Exit");
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
		menu.add(add);
		//Creates the edit button
		JMenuItem edit = new JMenuItem("Edit "+name);
		menu.add(edit);
		//Creates the delete button
		JMenuItem delete = new JMenuItem("Delete "+name);
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
		JPanel func = new JPanel(new GridLayout(1, 3));
		JButton add = new JButton("Add");
		func.add(add);
		JButton edit = new JButton("Edit");
		func.add(edit);
		JButton delete = new JButton("Delete");
		func.add(delete);
		return func;
	}
}
