package gui.panels;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import helpers.Account;
import helpers.GameObject;


public class ResultsPanel {
	private static JPanel resultsPanel;
	private List<GameObject> objects;
	
	protected static JPanel getResultsPanel(){
		if(resultsPanel == null){
			resultsPanel = new JPanel();
			resultsPanel.add(new JLabel("TEST"));
		}
		return resultsPanel;
	}
	
	protected static void setResultsPanel(){
		resultsPanel = new JPanel();
		resultsPanel.add(new JLabel("TEST"));
	}
	
	protected static void setResultsPanel(List<Account> accountList){
		resultsPanel = new JPanel(new BorderLayout());
		JPanel panel = new JPanel();
		panel.add(new JLabel("ACCOUNT SEARCH RESULTS"));
		resultsPanel.add(panel, BorderLayout.NORTH);
		
		JButton displayButton = new JButton("Display Account");
		//TODO implement
		panel.add(displayButton, BorderLayout.SOUTH);
		
		JScrollBar resultsBar = new JScrollBar();
		panel.add(resultsBar, BorderLayout.CENTER);
		
	}
}
