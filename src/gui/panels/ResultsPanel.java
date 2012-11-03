package gui.panels;

import helpers.Account;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;


public class ResultsPanel {
	private static JPanel resultsPanel;
	private List<Object> objects;
	
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
		
		resultsPanel.add(displayButton, BorderLayout.SOUTH);
		
		JScrollBar resultsBar = new JScrollBar();
		resultsPanel.add(resultsBar, BorderLayout.CENTER);
	}
}
