package gui.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddUser extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6749854810853817143L;

	public AddUser(){
		this.setLayout(new BorderLayout());
		
		JLabel userLabel = new JLabel("Add User");
		Font header = new Font(Font.SERIF, Font.BOLD, 20);
		userLabel.setFont(header);
		this.add(userLabel, BorderLayout.NORTH);
		
		JPanel form = new JPanel(new GridLayout(9, 2));
		
		JLabel username = new JLabel("Username: ");
		form.add(username);
		JTextField usernameField = new JTextField();
		form.add(usernameField);
		
		JLabel password = new JLabel("Password: ");
		form.add(password);
		JTextField passwordField = new JTextField();
		form.add(passwordField);
		
		JLabel confirm = new JLabel("Confirm: ");
		form.add(confirm);
		JTextField confirmField = new JTextField();
		form.add(confirmField);
		
		form.add(new JPanel());
		form.add(new JPanel());
		
		JLabel email = new JLabel("Email: ");
		form.add(email);
		JTextField emailField = new JTextField();
		form.add(emailField);
		
		form.add(new JPanel());
		form.add(new JPanel());
		
		JLabel firstName = new JLabel("First Name: ");
		form.add(firstName);
		JTextField firstNameField = new JTextField();
		form.add(firstNameField);
		
		JLabel lastName = new JLabel("Last Name: ");
		form.add(lastName);
		JTextField lastNameField = new JTextField();
		form.add(lastNameField);
		
		this.add(form, BorderLayout.CENTER);
		this.setBackground(new Color(255,255,255));
		this.setVisible(true);
		System.out.println("Here");
	}
}
