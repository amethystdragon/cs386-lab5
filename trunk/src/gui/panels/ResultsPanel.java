package gui.panels;

import helpers.Account;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class ResultsPanel {
	private static JPanel resultsPanel;
	
	public static JPanel getResultsPanel(){
		if(resultsPanel == null){
			resultsPanel = new JPanel();
			resultsPanel.add(new JLabel("TEST"));
		}
		return resultsPanel;
	}
	
	public static void setResultsPanel(){
		resultsPanel = new JPanel();
		resultsPanel.add(new JLabel("TEST"));
	}
	
	public static void setResultsPanel(List<Account> accountList){
		resultsPanel = new JPanel(new BorderLayout());
		JPanel panel = new JPanel();
		panel.add(new JLabel("ACCOUNT SEARCH RESULTS"));
		resultsPanel.add(panel, BorderLayout.NORTH);
		
		JButton displayButton = new JButton("Display Account");
		//TODO
		resultsPanel.add(displayButton, BorderLayout.SOUTH);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for(Account account : accountList){
			listModel.addElement(account.getAccountName());
		}
		JList<String> resultsList = new JList<String>(listModel);
		resultsList.setSelectedIndex(0);
		resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(resultsList);
		//panel.add(new JScrollBar(), BorderLayout.EAST);
		
		resultsPanel.add(scrollPane, BorderLayout.CENTER);
	}
}
