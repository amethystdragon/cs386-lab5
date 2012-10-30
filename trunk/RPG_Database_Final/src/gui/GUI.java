package gui;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;

import gui.panels.AdminButtonPanel;
import gui.panels.MainPanel;
import helpers.Ability;
import helpers.Account;
import helpers.Character;
import helpers.Item;
import helpers.Skill;



public class GUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private static JPanel mainPanel;
	private static JFrame myFrame;
	
	public GUI() {

		myFrame = new JFrame("RPGame Database Prototype");		
		myFrame.setLayout(new BorderLayout());
		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		myFrame.setSize(1200,800);
		
		mainPanel = new JPanel(new BorderLayout());
		ImageIcon icon = new ImageIcon("rpgame.jpg");
		JLabel image = new JLabel(icon);
		mainPanel.add(image);
		
		myFrame.add(mainPanel, BorderLayout.CENTER);
		
		//menubar
		JMenuBar menuBar = new JMenuBar();
		JMenu menu; 
		JMenuItem menuItem;
		
		//Import menu
		menu = new JMenu("Import");
		menuBar.add(menu);
		
		menuItem = new JMenuItem("Select Import File");
		menuItem.setToolTipText("Choose the file you would like to import database data from.");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
						File file;
						JFileChooser chooser = new JFileChooser();
						chooser.setFileFilter(new FileNameExtensionFilter("TXT", "txt"));
						int returnVal = chooser.showOpenDialog(null);

						JTextArea text = new JTextArea();

						if (returnVal == JFileChooser.APPROVE_OPTION) {
							//TODO import parse code
						}
					}
			});
		menu.add(menuItem);
		
		//Mode menu
		menu = new JMenu("Mode");
		menuBar.add(menu);
		
		//Admin mode
		menuItem = new JMenuItem("Admin Mode");
		menuItem.setToolTipText("Enter System Administrator Mode.");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JPanel image = new JPanel();
				image.add(new JLabel(new ImageIcon("RPgame.jpg")));
				
				setMainPanel(MainPanel.getMainPanel());
			}
		});
		menu.add(menuItem);
		
		/*//User mode
		menuItem = new JMenuItem("User Mode");
		menuItem.setToolTipText("Enter System User Mode.");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//TODO user mode
			}
		});
		menu.add(menuItem);
		*/
		
		//myFrame.pack();
		myFrame.setJMenuBar(menuBar);
		myFrame.setResizable(false);
		myFrame.setVisible(true);
	}
	
	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignored) {}
		new GUI();
	}
	
	/*private void setAdminPanel(JPanel panel){
		JFrame parent = getParentFrame();
		
		//reset mainPanel
		parent.remove(mainPanel);
		JPanel mainPanel = new JPanel(new BorderLayout());

		//create panels
		JPanel frontPanel = panel;
		
		//add panels
		mainPanel.add(MainPanel.getMainPanel());
		
		//add mainPanel to parent
		parent.add(mainPanel);
		parent.validate();
	}*/
	
	public static void setMainPanel(JPanel panel){
		myFrame.remove(mainPanel);
		mainPanel = panel;
		myFrame.add(mainPanel);
		validateFrame();
	}
	
	public static JFrame getParentFrame(){
		return myFrame;
	}
	
	public static void validateFrame(){
		myFrame.validate();
	}
}
