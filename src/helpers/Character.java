package helpers;

import gui.GUI;
import gui.GUI.ObjectType;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dataAccess.DataAccess;

public class Character {
	
	public enum Race{
		Human, Elf, Dwarf, Hobbit, Gnome;

		public static Race getRace(String string) {
			switch(string){
			case "Human":
				return Human;
			case "Elf":
				return Elf;
			case "Dwarf":
				return Dwarf;
			case "Hobbit":
				return Hobbit;
			case "Gnome":
				return Gnome;
			default:
				return null;
			}
		}
	}
	
	//character name - NON-unique
	private String name;
	
	//character race
	private Race race;
	
	//character model
	private String model;
	
	//character strength
	private int strength;
	
	//character constitution
	private int constitution;
	
	//character intelligence
	private int intelligence;
	
	//character wisdom
	private int wisdom;
	
	//character agility
	private int agility;
	
	//character dexterity
	private int dexterity;
	
	//character level
	private int level;
	
	//character experience
	private int experience;
	
	//owner account id
	private String accountUsername;
	
	/**
	 * private Constructor
	 */
	public Character(String name, Race race, String model, int strength,
			int constitution, int intelligence, int wisdom, int agility, int dexterity, int level, int experience, String accountID){
		this.name = name;
		this.race = race;
		this.model = model;
		this.strength = strength;
		this.constitution = constitution;
		this.intelligence = intelligence;
		this.wisdom = wisdom;
		this.agility = agility;
		this.dexterity = dexterity;
		this.level = level;
		this.experience = experience;
		this.accountUsername = accountID;
	}
	
	/**
	 * factory method to add character to database by account ID
	 * @param accountID
	 * @param name
	 * @param race
	 * @param age
	 * @param model
	 * @param strength
	 * @param constitution
	 * @param intelligence
	 * @param wisdom
	 * @param agility
	 * @param dexterity
	 * @param level
	 * @param experience
	 * @return - true if added else false
	 */
	public static boolean addCharacter(int accountID, String name, Race race, int age, String model, int strength,
			int constitution, int intelligence, int wisdom, int agility, int dexterity, int level, int experience){
		boolean added = false;
		//TODO sql command to add char to db set added = true if added
		//no duplicate character names on same account, enforced in code here
		return added;
	}
	
	/**
	 * delete character by ID
	 * @param ID
	 * @return - true if deleted else false
	 */
	public static boolean deleteCharacter(int ID){
		boolean deleted = false;
		//TODO sql to delete char by ID
		return deleted;
	}
	
	/**
	 * delete character by object
	 * @param name
	 * @return
	 */
	public static boolean deleteCharacter(Character character){
		//TODO
		return false;
//		return deleteCharacter(character.getID());
	}
	
	/**
	 * find character by id
	 * @param ID
	 * @return
	 */
	public static Character findCharacter(int ID){
		Character result = null;
		//TODO sql command to find char by ID
		return result;
	}
	
	/**
	 * find all character with name
	 * @param name
	 * @return
	 */
	public static Character findCharacters(String name){
		Character ch = null;
		List<Character> charList;
		try {
			charList = DataAccess.getInstance().searchCharacter(name, null, "");
			ch = charList.get(0);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ch;
	}
	
	/**
	 * method to alter stats of this character
	 * @param strength
	 * @param constitution
	 * @param intelligence
	 * @param wisdom
	 * @param agility
	 * @param dexterity
	 * @return
	 */
	public boolean setStats(int strength, int constitution, int intelligence, int wisdom, int agility, int dexterity){
		boolean changed = false;
		//TODO sql command to change this characters stats in db
		//if changed set changed = true, set this.stats = new values
		return changed;
	}
	
	/**
	 * method to set experience on this character
	 * @param experience
	 * @return - true if changed else false
	 */
	public boolean setExperience(int experience){
		boolean changed = false;
		//TODO sql command to change this characters experience in db
		//if changed set changed = true, set this.experience = new values		
		return changed;
	}
	
	/**
	 * method to increment this characters level
	 * @return
	 */
	public boolean incrementLevel(){
		boolean incremented = false;
		//TODO sql command to increment level by +1 in db
		//if changed in db this.level++
		return incremented;
	}
	
	/**
	 * method to get parent of character by character id
	 * @param ID - character ID
	 * @return - parent account if found else null
	 */
	public static Account getParent(String name){
		Account parent = null;
		//TODO sql command to find parent of character by character ID
		return parent;
	}
	
	public List<Item> getItems(){
		List<Item> items = new LinkedList<Item>();
		//TODO get items sql commands
		return items;
	}
	
	public List<Skill> getSkills(){
		List<Skill> skills = new LinkedList<Skill>();
		//TODO get skills sql commands
		return skills;
	}
	
	//***Public Accessors***//
	public String getName(){
		return this.name;
	}
	
	public String getRace(){
		return this.race.toString();
	}
	
	public String getModel(){
		return this.model;
	}
	
	public int getStrength(){
		return this.strength;
	}
	
	public int getConstitution(){
		return this.constitution;
	}
	
	public int getIntelligence(){
		return this.intelligence;
	}
	
	public int getWisdom(){
		return this.wisdom;
	}
	
	public int getAgility(){
		return this.agility;
	}
	
	public int getDexterity(){
		return this.dexterity;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public int getExperience(){
		return this.experience;
	}
	
	public String getAccountUsername(){
		return this.accountUsername;
	}

	public ObjectType getType() {
		return GUI.ObjectType.CHARACTER;
	}
	public String toString(){
		return name + ": " + race;
	}
}
