package main;

import java.sql.SQLException;

import dataAccess.DataAccess;
import gui.GUI;

public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DataAccess.getInstance();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		new GUI();
		
	}

}
