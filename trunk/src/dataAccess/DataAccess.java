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
import java.util.LinkedList;
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
		List<Account> accounts = new LinkedList<Account>();
		String query = new String("SELECT * FROM `account`");
		if(!username.isEmpty() || !fname.isEmpty() || !lname.isEmpty() || !email.isEmpty()){
			boolean first = false;
			query += " WHERE";
			if(!username.isEmpty()){
				query += " `account_name`='" + username + "'";
				first = true;
			}
			if(!fname.isEmpty()){
				if(!first){
					query += " AND";
					first = true;
				}	
				query += " `first_name`='" + fname + "'";
			}
			if(!lname.isEmpty()){
				if(first){
					query += " AND";
					first = true;
				}
				query += " `last_name`='" + lname + "'";
			}
			if(!email.isEmpty()){
				if(first){
					query += " AND";
					first = true;
				}
				query += " `email`='" + email + "'";
			}
		}
		query += ";";
		try{
			ResultSet result = query(query);
			while(result.next()){
				accounts.add(new Account(
					result.getString(2), 
					result.getString(3), 
					result.getString(4), 
					result.getString(5), 
					result.getString(6), 
					Integer.parseInt(result.getString(7))));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
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
		String query = "SELECT `name`,`race`,`model`,`strength`,`constitution`,`intelligence`,`wisdom`,`agility`,`dexterity`,`level`,`experience`,`account_ID` FROM `character`";
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
				query += "`account_account_ID`=(SELECT `account_ID` FROM `Account` WHERE `account_name`='"+username+"')";
			}
		}
		//TODO FIX THE ACCOUNT ID RETURN
		List<Character> characters = null;
		try {
			System.out.println(1);
			ResultSet result = query(query);
			System.out.println(2);
			characters = new ArrayList<Character>();
			while(result.next()){
				System.out.println(3);
				Character newChar = new Character(result.getString(1), 
						Race.getRace(result.getString(2)), 
						result.getString(3), 
						Integer.parseInt(result.getString(4)), 
						Integer.parseInt(result.getString(5)),
						Integer.parseInt(result.getString(6)),
						Integer.parseInt(result.getString(7)),
						Integer.parseInt(result.getString(8)),
						Integer.parseInt(result.getString(9)),
						Integer.parseInt(result.getString(10)),
						Integer.parseInt(result.getString(11)),
						result.getString(12));
				System.out.println(4);
				characters.add(newChar);
				System.out.println(newChar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		System.out.println(characters.size());
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
		String query = "SELECT `name`,`damage`,`armor`,`level`,`rarity`,`value`,`model`,`refinement`,`abilityID` FROM `Item`";
		if(!name.isEmpty() && rarity!=null && !ability.isEmpty()){
			boolean first = true;
			query += " WHERE ";
			if(!name.isEmpty()){
				query += "`name`='"+name+"'";
				if(first) first = false;
			}
			if(rarity!=null){
				if(first) first = false;
				else query += " AND ";
				query += "`rarity`='"+name+"'";
			}
			if(!username.isEmpty()){
				if(!first) query += " AND ";
				query += "`ability_ID`=(SELECT `ability_ID` FROM `Ability` WHERE `name`='"+ability+"')";
			}
		}
		//TODO FIX THE ABILITY ID RETURN
		List<Item> items = null;
		try {
			ResultSet result = query(query);
			items = new ArrayList<Item>();
			while(result.next()){
				items.add(new Item(
						result.getString(1), 
						Integer.parseInt(result.getString(2)), 
						Integer.parseInt(result.getString(3)),
						Integer.parseInt(result.getString(4)),
						Rarity.valueOf(result.getString(5)), 
						Integer.parseInt(result.getString(6)), 
						result.getString(7), 
						null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return items;
	}

	/**
	 * @author Joe
	 * Search for a skill which matches the given criteria
	 * Query is built depending on the non-empty paramerters 
	 * @param ID
	 * @param name
	 * @param level
	 * @return the list of matching skills
	 */
	public List<Skill> searchSkill(String name, int level){
		List<Skill> skillList = null;
		ResultSet result = null;
		String query = "SELECT * FROM `skill`";
		// Add to the query string based on what information is available
		if(name.isEmpty() && level<0) {
			query += ";";
		} else {
			query += " WHERE ";
		}
		if(!name.isEmpty()) {
			query += "`name`='" + name + "'";
		}
		if(!name.isEmpty() && !(level<0)) {
			query += " AND ";
		}
		if(!name.isEmpty()) {
			query += "`level_requirement`='" + level + "'";
		}
		// End of query construction
		query += ";";

		// Create a list of the returned skills from the query
		try {
			result = query(query);
			skillList = new ArrayList<Skill>();
			// While there is a next entry, create the skills and add skills to the list
			while(result.next()) {
				skillList.add(new Skill(result.getString(2),
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
	 * @author Malcolm
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
	 * @author Will
	 * @param username
	 * @param user
	 * @return
	 */
	public boolean addUser(Account user){
		boolean added = false;
		try{
			added = this.execute("INSERT INTO `account` (`account_name, `password`, `email`, `first_name`, `last_name`, " +
					"`ingame_currency`) VALUES ('" + user.getAccountName() + "\', \'" + user.getPassword() + "\', \'" +
					user.getEmail() + "\', \'" + user.getFirstName() + "\', \'" + user.getLastName() + "\', \'" + user.getCurrency() + "\');");
		}catch(Exception e){
		}
		return added;
	}


	/**
	 * @author Karl
	 * @param Character
	 * @return
	 */
	public boolean addCharacter(Character character){
		String query = "INSERT INTO `mydb`.`character` " +
		"(`name`, `race`, `model`, `strength`, " +
		"`constitution`, `intelligence`, `wisdom`, `agility`, " +
		"`dexterity`, `level`, `experience`, `account_account_ID`) VALUES (" +
		"'"+character.getName()+"', " +
		"'"+character.getRace()+"', " +
		"'"+character.getModel()+"', " +
		"'"+character.getStrength()+"', " +
		"'"+character.getConstitution()+"', " +
		"'"+character.getIntelligence()+"', " +
		"'"+character.getWisdom()+"', " +
		"'"+character.getAgility()+"', " +
		"'"+character.getDexterity()+"', " +
		"'"+character.getLevel()+"', " +
		"'"+character.getExperience()+"', " +
		"(SELECT `account_ID` FROM `Account` WHERE `account_name`='"+character.getAccountUsername()+"'))";
		
		try {
			return execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * @author Karl
	 * @param item
	 * @return
	 */
	public boolean addItem(Item item){
		String query = "INSERT INTO `items` " +
			"(`name`, `damage`, `armor`, " +
			"`level_requirement`, `rarity`, `value`, `model`, " +
			"`abilitiy_ability_ID`) VALUES (" +
		"'"+item.getName()+"', " +
		"'"+item.getDamage()+"', " +
		"'"+item.getArmor()+"', " +
		"'"+item.getLevel()+"', " +
		"'"+item.getRarity()+"', " +
		"'"+item.getValue()+"', " +
		"'"+item.getModel()+"', " +
		"(SELECT `ability_ID` FROM `Account` WHERE `name`='"+item.getAbility().getName()+"'))";
		
		try {
			return execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Joe
	 * Adds the skill into the database using the insert command
	 * Unique IDs auto-increment
	 * @param name
	 * @param description
	 * @param level
	 * @return true if successful
	 */
	public boolean addSkill(Skill skill) {
		boolean execute = false;
		try{
			execute = execute("INSERT INTO SKILL(name, description, level_requirement) " +
					"VALUES('" + skill.getName() + "', '" + skill.getDescription() + "', " + skill.getLevelRequirement()+ ");");
		} catch (SQLException e) {
			System.err.println("Error in Skill Add: " + e.getMessage());
			e.printStackTrace();
		}
		return execute;
	}

	/**
	 * @author Malcolm
	 * @param name
	 * @param ability
	 * @return
	 */
	public boolean addAbility(Ability ability){
		//TODO
		return false;
	}


	//EDIT
	/**
	 * @author Will
	 * @param username
	 * @param user
	 * @return
	 */
	public boolean editUser(Account user){
		//TODO
		return false;
	}


	/**
	 * @author Karl
	 * @param name
	 * @param Character
	 * @return
	 */
	public boolean editCharacter(String name, Character character){
		String query = "UPDATE `character` SET " +
		"`name`='"+character.getName()+
		"', `race`='"+character.getRace()+
		"', `model`='" +character.getModel()+
		"', `strength`='" +character.getStrength()+
		"', `constitution`='" +character.getConstitution()+
		"', `intelligence`='" +character.getIntelligence()+
		"', `wisdom`='" +character.getWisdom()+
		"', `agility`='" +character.getAgility()+
		"', `dexterity`='" +character.getDexterity()+
		"', `level`='" +character.getLevel()+
		"', `experience`='" +character.getExperience()+
		"', `account_account_ID`=(SELECT `account_ID` FROM `Account` WHERE `account_name`='"+character.getAccountUsername()+"'))"+
		"' WHERE `name`='"+name+"'";
		try {
			return execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * @author Karl
	 * @param name
	 * @param item
	 * @return
	 */
	public boolean editItem(String name, Item item){
		String query = "UPDATE `items` SET " +
			"`name`='" +item.getName()+
			"', `damage`='" +item.getDamage()+
			"', `armor`='" +item.getArmor()+
			"', `level_requirement`='" +item.getLevel()+
			"', `rarity`='" +item.getRarity()+
			"', `value`='" +item.getValue()+
			"', `model`='" +item.getModel()+
			"', `abilitiy_ability_ID`='(SELECT `ability_ID` FROM `Account` WHERE `name`='"+item.getAbility().getName()+"')"+
			"' WHERE `name`='" +name+"'";
		try {
			return execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Joe
	 * Updates the skill using the ID as the identifier
	 * @param skill_ID
	 * @param name
	 * @param description
	 * @param level
	 * @return true if successful
	 */
	public boolean editSkill(String name, Skill skill){
		boolean execute = false;
		try{
			execute = execute("UPDATE SKILL SET `name`='" + skill.getName()+
					"', `description`='" + skill.getDescription() +
					"', `level_requirement`='" + skill.getLevelRequirement() +
					"' WHERE `name`='" + name + "';");
		} catch (SQLException e) {
			System.err.println("Error in Skill Update: " + e.getMessage());
			e.printStackTrace();
		}
		return execute;
	}

	/**
	 * @author Malcolm
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
	 * @author Will
	 * @param username
	 * @param user
	 * @return
	 */
	public boolean deleteUser(String username){
		//TODO
		return false;
	}


	/**
	 * @author Karl
	 * @param name
	 * @param Character
	 * @return
	 */
	public boolean deleteCharacter(String name){
		try {
			return execute("DELETE FROM `character` WHERE `name`='"+name+"'");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * @author Karl
	 * @param name
	 * @param item
	 * @return
	 */
	public boolean deleteItem(String name){
		try {
			return execute("DELETE FROM `items` WHERE `name`='"+name+"'");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author Joe
	 * Deletes a skill using a string name
	 * @param name
	 * @return true if successful
	 */
	public boolean deleteSkill(String name) {
		boolean execute = false;
		try{
			execute = execute("DELETE FROM `skills` WHERE `name`='" + name + "';");
		} catch (SQLException e) {
			System.err.println("Error in Skill Delete By Name: " + e.getMessage());
			e.printStackTrace();
		}
		return execute;
	}

	/**
	 * @author Malcolm
	 * @param name
	 * @param ability
	 * @return
	 */
	public boolean deleteAbility(String name){
		//TODO
		return false;
	}
}
