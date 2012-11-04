package dataAccess;

import helpers.Skill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

	/**
	 * Adds the skill into the database using the insert command
	 * Unique IDs auto-increment
	 * @param name
	 * @param description
	 * @param level
	 * @return true if successful
	 */
	public boolean addSkill(String name, String description, int level) {
		boolean execute = false;
		try{
			execute = execute("INSERT INTO SKILL(name, description, level_requirement) " +
					"VALUES(" + name + ", " + description + ", " + level + ");");
		} catch (SQLException e) {
			System.err.println("Error in Skill Add: " + e.getMessage());
			e.printStackTrace();
		}
		return execute;
	}

	/**
	 * Deletes a skill using a string name
	 * @param name
	 * @return true if successful
	 */
	public boolean deleteSkillByName(String name) {
		boolean execute = false;
		try{
			execute = execute("DELETE FROM SKILL WHERE name = " + name + ";");
		} catch (SQLException e) {
			System.err.println("Error in Skill Delete By Name: " + e.getMessage());
			e.printStackTrace();
		}
		return execute;
	}

	/**
	 * Deletes a skill from a unique skill ID integer
	 * @param skill_ID
	 * @return true if successful
	 */
	public boolean deleteSkillByID(int skill_ID) {
		boolean execute = false;
		try{
			execute = execute("DELETE FROM SKILL WHERE skill_ID = " + skill_ID + ";");
		} catch (SQLException e) {
			System.err.println("Error in Skill Delete By ID: " + e.getMessage());
			e.printStackTrace();
		}
		return execute;
	}

	/**
	 * Search for a skill which matches the given criteria
	 * Query is built depending on the non-empty paramerters 
	 * @param ID
	 * @param name
	 * @param level
	 * @return the list of matching skills
	 */
	public List<Skill> findSkill(String ID, String name, String level) {
		List<Skill> skillList = null;
		ResultSet result = null;
		String query = "SELECT * FROM SKILL";
		// Add to the query string based on what information is available
		if(ID.isEmpty() && name.isEmpty() && level.isEmpty()) {
			query += ";";
		} else {
			query += " WHERE ";
		}
		if(!ID.isEmpty()) {
			query += "skill_ID = " + ID;
		}
		if(!ID.isEmpty() && (!name.isEmpty() || !level.isEmpty())) {
			query += " AND ";
		}
		if(!name.isEmpty()) {
			query += "name = " + name;
		}
		if(!name.isEmpty() && !level.isEmpty()) {
			query += " AND ";
		}
		if(!name.isEmpty()) {
			query += "level_requirement = " + level;
		}
		// End of query construction
		query += ";";

		// Create a list of the returned skills from the query
		try {
			result = query(query);
			skillList = new ArrayList<Skill>();
			// While there is a next entry, create the skills and add skills to the list
			while(result.next()) {
				skillList.add(new Skill(result.getInt(1),
						result.getString(2),
						result.getString(3),
						result.getInt(4)));
			}
		} catch (SQLException e) {
			System.err.println("Error in Skill Search: " + e.getMessage());
			e.printStackTrace();
		}
		// Return the list of found skills
		return skillList;
	}

	/**
	 * Updates the skill using the ID as the identifier
	 * @param skill_ID
	 * @param name
	 * @param description
	 * @param level
	 * @return true if successful
	 */
	public boolean updateSkill(String skill_ID, String name, String description, String level) {
		boolean execute = false;
		try{
			execute = execute("UPDATE SKILL SET name = " + name +
					", description = " + description +
					", level_requirement = " + level +
					" WHERE skill_ID = " + skill_ID + ";");
		} catch (SQLException e) {
			System.err.println("Error in Skill Update: " + e.getMessage());
			e.printStackTrace();
		}
		return execute;
	}
}
