package helpers;

import gui.GUI;
import gui.GUI.ObjectType;

public class Ability {
	//ID of Ability - primary key - unique
	private int ID;
	
	//name of ability - unique
	private String name;
	
	//description of ability
	private String description;
	
	//required level of ability
	private int level;
	
	/**
	 * private Constructor
	 * @param name
	 * @param description
	 * @param level
	 */
	public Ability(String name, String description, int level)
	{
		this.name = name;
		this.description = description;
		this.level = level;
	}
	
	/**
	 * addAbility - factory method to add ability to database
	 * @param name
	 * @param description
	 * @param level
	 * @return - true if added else false
	 */
	public static boolean addAbility(String name, String description, int level){
		boolean added = false;
		if(findAbility(name) == null){
			//TODO sql command to add ability to database
			//set result = true if added
		}
		return added;
	}
	
	/**
	 * delete ability by name
	 * @param name
	 * @return - true if deleted else false
	 */
	public static boolean deleteAbility(String name){
		boolean result = false;
		//TODO query sql for ability name if found delete row if successful result = true 
		
		return result;
	}
	
	/**
	 * delete ability by ID
	 * @param ability_ID
	 * @return - true if deleted else false
	 */
	public static boolean deleteAbility(int ability_ID){
		boolean result = false;
		//TODO query sql for ability by ID if found delete row if successful result = true 
		
		return result;
	}	
	
	/**
	 * delete ability by Ability object
	 * @param Ability
	 * @return - true if deleted else false
	 */
	public static boolean deleteAbility(Ability ability){
		boolean result = false;
		//TODO query sql for ability by ID if found delete row if successful result = true (more or less simple copy paste from another delete command we can search by id or name of ability) 
		
		return result;
	}	
	
	/**
	 * findAbility by name
	 * @param name - name to search
	 * @return - Ability object if found else null if not
	 */
	public static Ability findAbility(String name){
		Ability result = null;
		//TODO sql command to find ability with given name
		//result = result of sql query
		return result;
	}
	
	/**
	 * findAbility by id
	 * @param id - id to search
	 * @return - Ability object if found else null if not
	 */
	public static Ability findAbility(int ID){
		Ability result = null;
		//TODO sql command to find ability with given ID
		//result = result of sql query
		return result;
	}
	
	//***Public Accessors***//
	
	public int getID(){
		return this.ID;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public int getLevelRequirement(){
		return this.level;
	}

	public ObjectType getType() {
		return GUI.ObjectType.ABILITY;
	}
}
