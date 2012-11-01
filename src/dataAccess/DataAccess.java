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
import java.util.List;

public class DataAccess implements DataAccessInterface {
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
	private String mydatabase = "Timeshares";
	/**
	 * Stores the password to connect to the database with
	 */
	private String password = "R78933jGtCKAWczM";
	/**
	 * Stores the server name
	 */
	private String serverName = "localhost";
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
	private String username = "TimeshareUser";

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

	@Override
	public List<Account> searchUser(String username, String fname,
			String lname, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Character> searchCharacter(String name, Race race,
			String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> searchItem(String name, Rarity rarity, String ability) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Skill> searchSkill(String name, String level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ability> searchAbility(String name, int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUser(String username, Account user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCharacter(String name, Character Character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addItem(String name, Item item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addSkill(String name, Skill skill) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAbility(String name, Ability ability) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editUser(String username, Account user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editCharacter(String name, Character Character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editItem(String name, Item item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editSkill(String name, Skill skill) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editAbility(String name, Ability ability) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCharacter(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteItem(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSkill(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAbility(String name) {
		// TODO Auto-generated method stub
		return false;
	}
}