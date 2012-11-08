package gui;


import gui.panels.DisplayPanel;
import gui.panels.ResultsPanel;
import gui.panels.SearchPanel;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import dataAccess.DataAccess;



public class GUI extends JFrame{
	/**
	 * Generated version ID
	 */
	private static final long serialVersionUID = 7518660021163117839L;
	private static GUI gui;

	/**
	 * Types
	 */
	public enum ObjectType{
		ABILITY, CHARACTER, ITEM, SKILL, ACCOUNT
	}

	public GUI() {
		//Sets up the window
		super("RPGame Database Prototype");

		gui = this;
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(1200,800);
		this.setResizable(false);

		//Adds the splash image
		ImageIcon icon = new ImageIcon("images/opening.jpg");
		JLabel image = new JLabel(icon);

		this.add(image, BorderLayout.CENTER);

		//Makes and set s a new menubar
		this.setJMenuBar(createMenuBar());

		//Good to go
		this.setVisible(true);
	}

	/**
	 * Helper to create the JMenuBar
	 * @return - the created JMenuBar
	 */
	public JMenuBar createMenuBar(){
		JMenuBar menuBar = new JMenuBar();

		//Creates the file menu
		JMenu file = new JMenu("File");
		//Adds a import data option
		file.add(createMenuItems("File Import"));
		//Adds a save data option
		file.addSeparator(); //Gotta keep 'em seperated.
		//Adds an exit option
		file.add(createMenuItems("Exit"));
		menuBar.add(file);

		//Creates the tools menu
		JMenu tools = new JMenu("Tools");
		//Creates the user sub-menu
		tools.add(createMenuItems("User"));
		//Creates the character sub-menu
		tools.add(createMenuItems("Character"));
		//Creates the item sub-menu
		tools.add(createMenuItems("Item"));
		//Creates the skils sub-menu
		tools.add(createMenuItems("Skill"));
		//Creates the user sub-menu
		tools.add(createMenuItems("Ability"));
		//Add to the enubar
		menuBar.add(tools);

		//Adds the menubar to the window
		return menuBar;
	}

	/**
	 * Creates each menu item and configures them
	 * @param name - name of the menu item
	 * @return the menu item that was created
	 */
	private JMenuItem createMenuItems(String name) {
		JMenuItem menu = new JMenuItem(name);
		menu.addActionListener(new MenuListener());
		menu.setActionCommand(name);
		menu.setAccelerator(KeyStroke.getKeyStroke(name.charAt(0), Event.CTRL_MASK));
		return menu;
	}

	public static GUI getGUI(){
		return gui;
	}

	/**
	 * Updates the main panel
	 */
	public void updateMainPanel(){
		//Removes all and adds the panels
		JPanel subPanel = new JPanel(new GridLayout(1,3));
		subPanel.add(SearchPanel.getSearchPanel());
		subPanel.add(ResultsPanel.getResultsPanel());
		subPanel.add(DisplayPanel.getDisplayPanel());
		//Add the sub panel
		this.getContentPane().removeAll();
		this.add(subPanel);
		this.validate();
	}

	/**
	 * Action listener for the Menu Bar items
	 * @author schmidbauerk@msoe.edu
	 *
	 */
	private class MenuListener implements ActionListener{
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				switch(arg0.getActionCommand()){
				case "User":
					SearchPanel.setSearchPanel(ObjectType.ACCOUNT);
					ResultsPanel.setResultsPanel(DataAccess.getInstance().searchUser("", "", "", ""));
					DisplayPanel.setDisplayPanel(ObjectType.ACCOUNT);
					updateMainPanel();
					break;
				case "Character":
					SearchPanel.setSearchPanel(ObjectType.CHARACTER);
					ResultsPanel.setResultsPanel(DataAccess.getInstance().searchCharacter("", null, ""));
					DisplayPanel.setDisplayPanel(ObjectType.CHARACTER);
					updateMainPanel();
					break;
				case "Item":
					SearchPanel.setSearchPanel(ObjectType.ITEM);
					ResultsPanel.setResultsPanel(DataAccess.getInstance().searchItem("", null, ""));
					DisplayPanel.setDisplayPanel(ObjectType.ITEM);
					updateMainPanel();
					break;
				case "Skill":
					SearchPanel.setSearchPanel(ObjectType.SKILL);
					ResultsPanel.setResultsPanel(DataAccess.getInstance().searchSkill("", -1, ""));
					DisplayPanel.setDisplayPanel(ObjectType.SKILL);
					updateMainPanel();
					break;
				case "Ability":
					SearchPanel.setSearchPanel(ObjectType.ABILITY);
					ResultsPanel.setResultsPanel(DataAccess.getInstance().searchAbility("", -1, ""));
					DisplayPanel.setDisplayPanel(ObjectType.ABILITY);
					updateMainPanel();
					break;
				case "Exit":
					System.exit(0);
					break;
				default:
					System.out.println(arg0.getActionCommand());
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
