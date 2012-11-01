package dataAccess;

import helpers.Ability;
import helpers.Account;
import helpers.Character;
import helpers.Character.Race;
import helpers.Item;
import helpers.Item.Rarity;
import helpers.Skill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {
	/**
	 * Stores the connection to the MySQL server
	 */
	private static Connection connection = null;
	/**
	 * MySQL MM JDBC driver
	 */
	private String driverName = "com.mysql.jdbc.Driver";
	/**
	 * Stores the database name to connect to.
	 */
	private String mydatabase = "mydb";
	/**
	 * Stores the password to connect to the database with
	 */
	private String password = "password";
	/**
	 * Stores the server name
	 */
	private String serverName = "155.92.107.132";
	/**
	 * Stores and executes MySQL statements
	 */
	private Statement stmt = null;
	/**
	 * Stores the URL for java.sql to use to connect to the server.
	 */
	private String url;
	/**
	 * Stores the username to connect to the database with
	 */
	private String username = "root";

	private static DataAccess instance;

	/**
	 * Main constructor. Makes a connection to the MySQL server and initializes
	 * the statement.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private DataAccess() throws ClassNotFoundException, SQLException {
		// Stores the credentials
		url = "jdbc:mysql://" + serverName + "/" + mydatabase;
		try {
			// Load the JDBC driver
			Class.forName(driverName);
			// Create a connection to the database
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ClassNotFoundException(
					"Was not able to load the MySQL DB Connector. Check the build path.",
					e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(
					"Was not able to connect to the database. Check network connections.",
					e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#finalize()
	 */
	@Override
	public void finalize() {
		try {
			connection.close();
			connection = null;
			url = "";
			url = null;
		} catch (SQLException e) {
		}
	}

	/**
	 * Executes a query on the statement passed in
	 * 
	 * @param query
	 *            - String statement of a MySQL query that is executed
	 * @return - If successful, ResultSet of the executes query, else returns
	 *         null.
	 * @throws SQLException
	 */
	private synchronized boolean execute(String query) throws SQLException {
		try {
			// Executes the passed in query
			stmt = connection.createStatement();
			stmt.execute(query);
			System.out.println(query);
			return true;
		} catch (SQLException e) {
			System.err.println("Was unable to execute query: " + query);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Executes a query on the statement passed in
	 * 
	 * @param query
	 *            - String statement of a MySQL query that is executed
	 * @return - If successful, ResultSet of the executes query, else returns
	 *         null.
	 * @throws SQLException
	 */
	private synchronized ResultSet query(String query) throws SQLException {
		try {
			// Executes the passed in query
			stmt = connection.createStatement();
			System.out.println(query);
			return stmt.executeQuery(query);
		} catch (SQLException e) {
			System.err.println("Was unable to query the database with query: "
					+ query);
			e.printStackTrace();
			return null;
		}
	}



	/**
	 * Singleton method to get the instance of the database access
	 * 
	 * @return
	 */
	public static synchronized DataAccess getInstance()
			throws ClassNotFoundException, SQLException {
		if (instance == null)
			instance = new DataAccess(); // Create the instance
		return instance;
	}

	//SEARCH 
	/**
	 * 
	 * @author Will
	 * 
	 * @param username
	 * @param fname
	 * @param lname
	 * @param email
	 * @return
	 */
	public List<Account> searchUser(String username, String fname, String lname, String email){
		//TODO
		return null;
	}

	/**
	 * 
	 * @author Karl
	 * 
	 * @param name
	 * @param race
	 * @param username
	 * @return
	 */
	public List<Character> searchCharacter(String name, Character.Race race, String username){
		String query = "SELECT `name`,`race`,`model`,`strength`,`constitution`,`intelligence`,`wisdom`,`agility`,`dexterity`,`level`,`experience`,`accountID` FROM `Character`";
		if(!name.isEmpty() && race!=null && !username.isEmpty()){
			boolean first = true;
			query += " WHERE ";
			if(!name.isEmpty()){
				query += "`name`='"+name+"'";
				if(first) first = false;
			}
			if(race!=null){
				if(first) first = false;
				else query += " AND ";
				query += "`race`='"+name+"'";
			}
			if(!username.isEmpty()){
				if(!first) query += " AND ";
				query += "`account_account_ID`=(SELECT `account_ID` FROM `Account` WHERE `account_name`='"+name+"')";
			}
		}
		
		List<Character> characters = null;
		try {
			ResultSet result = query(query);
			characters = new ArrayList<Character>();
			while(result.next()){
				characters.add(new Character(result.getString(2), 
						Race.valueOf(result.getString(3)), 
						result.getString(4), 
						Integer.parseInt(result.getString(5)), 
						Integer.parseInt(result.getString(6)),
						Integer.parseInt(result.getString(7)),
						Integer.parseInt(result.getString(8)),
						Integer.parseInt(result.getString(9)),
						Integer.parseInt(result.getString(10)),
						Integer.parseInt(result.getString(11)),
						Integer.parseInt(result.getString(12)),
						Integer.parseInt(result.getString(13))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return characters;
	}

	/**
	 * 
	 * @author Karl
	 * 
	 * @param name
	 * @return
	 */
	public List<Item> searchItem(String name, Item.Rarity rarity, String ability){
		//TODO
		return null;
	}

	/**
	 - Malcolm
	 * @param name
	 * @param level
	 * @return
	 */
	public List<Skill> searchSkill(String name, String level){
		//TODO
		return null;
	}


	/**
	 * Joe
	 * @param name
	 * @param level
	 * @return
	 */
	public List<Ability> searchAbility(String name, int level){
		//TODO
		return null;
	}

	//ADD

	/**
	 * Will
	 * @param username
	 * @param user
	 * @return
	 */
	public boolean addUser(String username, Account user){
		//TODO
		return false;
	}


	/**
	 * Karl
	 * @param name
	 * @param Character
	 * @return
	 */
	public boolean addCharacter(String name, Character Character){
		//TODO
		return false;
	}


	/**
	 * Karl
	 * @param name
	 * @param item
	 * @return
	 */
	public boolean addItem(String name, Item item){
		//TODO
		return false;
	}

	/**
	 * Malcolm
	 * @param name
	 * @param skill
	 * @return
	 */
	public boolean addSkill(String name, Skill skill){
		//TODO
		return false;
	}

	/**
	 * Joe
	 * @param name
	 * @param ability
	 * @return
	 */
	public boolean addAbility(String name, Ability ability){
		//TODO
		return false;
	}


	//EDIT
	/**
	 * Will
	 * @param username
	 * @param user
	 * @return
	 */
	public boolean editUser(String username, Account user){
		//TODO
		return false;
	}


	/**
	 * Karl
	 * @param name
	 * @param Character
	 * @return
	 */
	public boolean editCharacter(String name, Character Character){
		//TODO
		return false;
	}


	/**
	 * Karl
	 * @param name
	 * @param item
	 * @return
	 */
	public boolean editItem(String name, Item item){
		//TODO
		return false;
	}

	/**
	 * Malcolm
	 * @param name
	 * @param skill
	 * @return
	 */
	public boolean editSkill(String name, Skill skill){
		//TODO
		return false;
	}

	/**
	 * Joe
	 * @param name
	 * @param ability
	 * @return
	 */
	public boolean editAbility(String name, Ability ability){
		//TODO
		return false;
	}



	//DELETE
	/**
	 * Will
	 * @param username
	 * @param user
	 * @return
	 */
	public boolean deleteUser(String username){
		//TODO
		return false;
	}


	/**
	 * Karl
	 * @param name
	 * @param Character
	 * @return
	 */
	public boolean deleteCharacter(String name){
		//TODO
		return false;
	}


	/**
	 * Karl
	 * @param name
	 * @param item
	 * @return
	 */
	public boolean deleteItem(String name){
		//TODO
		return false;
	}

	/**
	 * Malcolm
	 * @param name
	 * @param skill
	 * @return
	 */
	public boolean deleteSkill(String name){
		//TODO
		return false;
	}

	/**
	 * Joe
	 * @param name
	 * @param ability
	 * @return
	 */
	public boolean deleteAbility(String name){
		//TODO
		return false;
	}
}
