package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import helpers.Ability;
import helpers.Account;
import helpers.Character;
import helpers.Item;
import helpers.Skill;

public abstract class AdminButtonPanel{
	
	private static JPanel adminButtonPanel = null; 
	
	protected static JPanel getAdminButtonPanel(){
		JPanel adminTopPanel;
		if(adminButtonPanel == null){
			synchronized (GUI.getParentFrame()){
				if(adminButtonPanel == null){
					//create admin panel
					adminTopPanel = new JPanel(new GridLayout(1,5));
					
					//create buttons for top panel
					//account button
					JButton account = new JButton("Account");
					account.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent ae) {
							JPanel newPanel = new JPanel(new BorderLayout());
							newPanel.add(new JTextField("TEXT"));
						}
					});
					
					//character button
					JButton character = new JButton("Character");
					character.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent ae) {
							//TODO actionListener
						}
					});
					
					//item button
					JButton item = new JButton("Item");
					item.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent ae) {
							//TODO actionListener
						}
					});
					
					JButton skill = new JButton("Skill");
					skill.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent ae) {
							//TODO actionListener
						}
					});
					
					JButton ability = new JButton("Ability");
					ability.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent ae) {
							//TODO actionListener
						}
					});
					
					adminTopPanel.add(account);
					adminTopPanel.add(character);
					adminTopPanel.add(item);
					adminTopPanel.add(skill);
					adminTopPanel.add(ability);
				}
				else{
					adminTopPanel = adminButtonPanel;
				}
			}
		}
		else{
			adminTopPanel = adminButtonPanel;
		}
		return adminTopPanel;
	}
}