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
			boolean first = true;
			query += " WHERE";
			if(!username.isEmpty()){
				query += " `account_name`='" + username + "'";
				first = false;
			}
			if(!fname.isEmpty()){
				if(first) first = false;
				else query += " AND";
				query += " `first_name`='" + fname + "'";
			}
			if(!lname.isEmpty()){
				if(first) first = false;
				else query += " AND";
				query += " `last_name`='" + lname + "'";
			}
			if(!email.isEmpty()){
				if(!first) query += " AND";
				query += " `email`='" + email + "'";
			}
		}
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
		return accounts;
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
		String query = "SELECT c.`name` , c.`race` , c.`model` , c.`strength` , " +
				"c.`constitution` , c.`intelligence` , c.`wisdom` , c.`agility` , " +
				"c.`dexterity` , c.`level` , c.`experience` , a.`account_name` " +
				"FROM `character` c INNER JOIN `account` a ON c.`account_ID` = a.`account_ID`";
		if(!name.isEmpty() || race!=null || !username.isEmpty()){
			boolean first = true;
			query += " WHERE ";
			if(!name.isEmpty()){
				query += "c.`name` LIKE '%"+name+"%'";
				if(first) first = false;
			}
			if(race!=null){
				if(first) first = false;
				else query += " AND ";
				query += "`race`='"+name+"'";
			}
			if(!username.isEmpty()){
				if(!first) query += " AND ";
				query += "a.`account_ID` IN (SELECT `account_ID` FROM `account` WHERE `account_name` LIKE '%"+username+"%')";
			}
		}
		List<Character> characters = null;
		try {
			ResultSet result = query(query);
			characters = new ArrayList<Character>();
			while(result.next()){
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

	public List<Character> searchCharacter(String skill) {
		if(skill==null || skill.isEmpty()) return null;
		String query = "SELECT c.`name` , c.`race` , c.`model` , c.`strength` , c.`constitution` " +
				", c.`intelligence` , c.`wisdom` , c.`agility` , c.`dexterity` , c.`level` , c.`experience` " +
				", a.`account_name` FROM `character` c INNER JOIN `account` a ON c.`account_ID` = a.`account_ID` WHERE character_ID IN " +
				"(SELECT `character_character_ID` FROM `character_has_skill` WHERE skill_skill_ID=" +
				"(SELECT skill_ID FROM skill WHERE name='"+skill+"'));";
		List<Character> characters = null;
		try {
			ResultSet result = query(query);
			characters = new ArrayList<Character>();
			while(result.next()){
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
		String query = "SELECT i.`name`,i.`damage`,i.`armor`,i.`level_requirement`,i.`rarity`,i.`value`,i.`model`,a.`name` FROM `items` i INNER JOIN `ability` a ON a.ability_ID=i.ability_ID";
		if(!name.isEmpty() || rarity!=null || !ability.isEmpty()){
			boolean first = true;
			query += " WHERE ";
			if(!name.isEmpty()){
				query += "i.`name`='"+name+"'";
				if(first) first = false;
			}
			if(rarity!=null){
				if(first) first = false;
				else query += " AND ";
				query += "i.`rarity`='"+name+"'";
			}
			if(!ability.isEmpty()){
				if(!first) query += " AND ";
				query += "i.`ability_ID`=(SELECT `ability_ID` FROM `ability` WHERE `name` LIKE '%"+ability+"%')";
			}
		}
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
						Rarity.getRarity(result.getString(5)), 
						Integer.parseInt(result.getString(6)), 
						result.getString(7), 
						result.getString(8)));
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
	public List<Skill> searchSkill(String name, int level, String value){
		List<Skill> skillList = null;
		ResultSet result = null;
		String query = "SELECT * FROM `skill`";
		// Add to the query string based on what information is available
		if(name.isEmpty() && level<0);
		else {
			query += " WHERE ";
			if(!name.isEmpty())
				query += "`name`='" + name + "'";
			if(!name.isEmpty() && !(level<0))
				query += " AND ";
			if(!name.isEmpty())
				query += "`level_requirement`"+value+"'" + level + "'";
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
						Integer.parseInt(result.getString(4))));
			}
		} catch (SQLException e) {
			System.err.println("Error in Skill Search: " + e.getMessage());
			e.printStackTrace();
			return null;
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
	public List<Ability> searchAbility(String name, int level, String value){
		ResultSet result = null;
		List<Ability> abilities = new ArrayList<Ability>();
		String query = "SELECT * FROM `ability`";
		//1st see if there is anything in either variables
		if(name.isEmpty() && level<0);
		else query += " WHERE ";
		
		//2nd case is when there are both variables present
		if(!name.isEmpty() && level>=0)
			query += "`name` = '" + name + "' AND `level_requirement`"+value + level;
		else if(!name.isEmpty())
			query += "`name` = '" + name + "'";
		else if(level>=0)
				query += "`level_requirement`"+value+level;
		//End of query
		query += ";";
		try{
			result = query(query);
			while(result.next()){
				abilities.add(new Ability(result.getString(2),
						result.getString(3),
						Integer.parseInt(result.getString(4))));
			}
		}catch (SQLException e){
			System.err.println("Error in Ability Search: " + e.getMessage());
			return null;
		}
		return abilities;
	}

	//ADD

	/**
	 * @author Will
	 * @param username
	 * @param user
	 * @return
	 */
	public boolean addUser(Account user){
		try{
			return execute("INSERT INTO `account` " +
					"(`account_name`, `password`, `email`, `first_name`, `last_name`, " +
					"`ingame_currency`) VALUES ('" + 
					user.getAccountName() + "', '" + 
					user.getPassword() + "', '" +
					user.getEmail() + "', '" + 
					user.getFirstName() + "', '" + 
					user.getLastName() + "', '" + 
					user.getCurrency() + "');");
		}catch(Exception e){
			System.err.println("Error in adding user: " + e.getMessage());
			return false;
		}
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
		"`dexterity`, `level`, `experience`, `account_ID`) VALUES (" +
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
		"(SELECT `account_ID` FROM `account` WHERE `account_name`='"+character.getAccountUsername()+"'))";
		
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
			"`ability_ID`) VALUES (" +
			"'"+item.getName()+"', " +
			"'"+item.getDamage()+"', " +
			"'"+item.getArmor()+"', " +
			"'"+item.getLevel()+"', " +
			"'"+item.getRarity()+"', " +
			"'"+item.getValue()+"', " +
			"'"+item.getModel()+"', " +
			"(SELECT `ability_ID` FROM `ability` WHERE `name`='"+
				((item.getAbility()==null)?"None":item.getAbility())
				+"'))";
		
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
			execute = execute("INSERT INTO `skill` (`name`, `description`, `level_requirement`) " +
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
		boolean execute = false;
		try{
			execute = execute("INSERT INTO `ability` (`name`, `description`, `level_requirement`) " +
					"VALUES('" + ability.getName() + "', '" + 
					ability.getDescription() + "', " + 
					ability.getLevelRequirement()+ ");");
		} catch (SQLException e) {
			System.err.println("Error in Ability Add: " + e.getMessage());
		}
		return execute;
	}

	//EDIT
	/**
	 * @author Will
	 * @param username
	 * @param user
	 * @return
	 */
	public boolean editUser(Account user){
		boolean edited = false;
		String query = "UPDATE `account` SET " +
		"`account_name`='"+user.getAccountName() +
		"', `password`='"+ user.getPassword() +
		"', `email`='" + user.getEmail() +
		"', `first_name`='" + user.getFirstName() +
		"', `last_name`='" + user.getLastName() +
		"', `ingame_currency`='" + user.getCurrency()+
		"' WHERE `account_name`='"+user.getAccountName()+"'";
		try {
			edited = execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return edited;
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
			"`name`= '" +item.getName()+
			"', `damage`= " +item.getDamage()+
			", `armor`= " +item.getArmor()+
			", `level_requirement`= " +item.getLevel()+
			", `rarity` ='" +item.getRarity()+
			"', `value` =" +item.getValue()+
			", `model`='" +item.getModel()+
			"', `ability_ID`= (SELECT `ability_ID` FROM `ability` WHERE `name`='"+item.getAbility()+"')"+
			" WHERE `name` = '" +name+"'";
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
			execute = execute("UPDATE `skill` SET `name`='" + skill.getName()+
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
		try{
			return execute("UPDATE `skill` SET `name`='" + ability.getName()+
					"', `description`='" + ability.getDescription() +
					"', `level_requirement`='" + ability.getLevelRequirement() +
					"' WHERE `name`='" + name + "';");
		} catch (SQLException e) {
			System.err.println("Error in Skill Update: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}



	//DELETE
	/**
	 * @author Will
	 * @param username
	 * @param user
	 * @return
	 */
	public boolean deleteUser(String username){
		boolean done = true;
		try{
			//Checks to see if the user has characters still
			ResultSet rs  = query("SELECT * FROM `account` WHERE `account_name`='"+username+"'");
			rs.next();
			rs = query("SELECT COUNT(`name`) FROM `character` WHERE `account_ID`="+rs.getString(1));
			rs.next();
			//If so the account will not be deleted
			if(Integer.parseInt(rs.getString(1))>0){
				//Deletes any items relations the user had
				done &= execute("DELETE FROM `character_has_items` WHERE `character_character_ID` IN (SELECT `character_ID` FROM `character` WHERE `account_name`='"+username+"')");
				//Deletes any skills relations that the user had
				done &= execute("DELETE FROM `character_has_skills` WHERE `character_character_ID` IN (SELECT `character_ID` FROM `character` WHERE `account_name`='"+username+"')");
				//Removes any characters tha tthe user had
				done &= execute("DELETE FROM `character` WHERE `account_ID` IN (SELECT `account_ID` FROM `account` WHERE `account_name`='"+username+"')");
			}
			//Removes the user
			done &= execute("DELETE FROM `account` WHERE `account_name` = '" + username + "'");
		}catch(Exception e){
			e.printStackTrace();
			done = false;
		}
		return done;
	}


	/**
	 * @author Karl
	 * @param name
	 * @param Character
	 * @return
	 */
	public boolean deleteCharacter(String name){
		boolean execute = true;
		try {
			ResultSet rs = query("SELECT * FROM `character` WHERE `name`='"+name+"'");
			rs.next();
			execute &= execute("DELETE FROM `character_has_skill` WHERE `character_character_ID`='"+rs.getString(1)+"'");
			execute &= execute("DELETE FROM `character_has_items` WHERE `character_character_ID`='"+rs.getString(1)+"'");
			execute &= execute("DELETE FROM `character` WHERE `name`='"+name+"'");
		} catch (SQLException e) {
			e.printStackTrace();
			execute = false;
		}
		return execute;
	}


	/**
	 * @author Karl
	 * @param name
	 * @param item
	 * @return
	 */
	public boolean deleteItem(String name){
		boolean done = true;
		try{
			//Checks to see if the user has characters still
			ResultSet rs  = query("SELECT * FROM `items` WHERE `name`='"+name+"'");
			rs.next();
			rs = query("SELECT COUNT(`name`) FROM `character_has_items` WHERE `items_item_ID`="+rs.getString(1));
			rs.next();
			//If so the account will not be deleted
			if(Integer.parseInt(rs.getString(1))>0){
				done &= execute("DELETE FROM `character_has_items` WHERE `account_ID` IN (SELECT `item_ID` FROM `items` WHERE `name`='"+name+"')");
			}
			done &= execute("DELETE FROM `items` WHERE `name`='"+name+"'");
		}catch(Exception e){
			e.printStackTrace();
			done = false;
		}
		return done;
	}

	/**
	 * @author Joe
	 * Deletes a skill using a string name
	 * @param name
	 * @return true if successful
	 */
	public boolean deleteSkill(String name) {
		if(name.equalsIgnoreCase("none")) return false;
		boolean execute = false;
		try{
			ResultSet rs = query("SELECT * FROM `skill` WHERE `name`='" + name + "';");
			rs.next();
			execute &= execute("DELETE FROM `character_has_skill` WHERE `skill_skill_ID`='" + rs.getString(1)+ "';");
			execute &= execute("DELETE FROM `skill` WHERE `name`='" + name + "';");
		} catch (SQLException e) {
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
		if(name.equalsIgnoreCase("NONE")){
			return false;
		}
		boolean execute = false;
		try{
			List<Item> itemlist = this.searchItem("", null, "");
			for(Item item : itemlist){
				if(item.getAbility() == name){
					execute("UPDATE item SET `ability_ID`= 0 WHERE `name` = '" + item.getName() + "';");
				}
			}
			execute("DELETE FROM `ability` WHERE `name`='" + name + "';");
			execute = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return execute;
	}
	
	//Character has item
	public List<Item> searchItem(Character character){
	
		return null;
	}
	//Character has skill
	public List<Skill> searchSkill(Character character){
		
		return null;
	}
	
	public boolean addItemToCharacter(String item, String name){
		try {
			return execute("INSERT INTO `character_has_itemsl` (`character_character_ID`, `items_item_ID`) VALUES " +
					"((SELECT character_ID FROM item WHERE `name`='"+name+"')," +
					"(SELECT item_ID FROM skill WHERE `name`='"+item+"')),");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean addSkillToCharacter(String skill, String name){
		try {
			return execute("INSERT INTO `character_has_skill` (`character_character_ID`, `items_item_ID`) VALUES " +
					"((SELECT character_ID FROM character WHERE `name`='"+name+"')," +
					"(SELECT skill_ID FROM skill WHERE `name`='"+skill+"')),");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
