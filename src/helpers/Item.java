package helpers;

import gui.GUI;
import gui.GUI.ObjectType;

public class Item {
	
	public enum Rarity{
		Common, Uncommon, Rare, Mythic, Legendary, Unique
	}
	
	//name of item - unique
	private String name;
	
	//damage bonus of item
	private int damage;
	
	//armor bonus of item
	private int armor;
	
	//level requirement of item
	private int level_requirement;
	
	//rarity of item
	private Rarity rarity;
	
	//ingame currency value of item when sold in game
	private int value;
	
	//model used to display item
	private String model;
	
	
	//id of associated ability
	private Ability ability;
	
	/**
	 * private constructor
	 */
	public Item(String name, int damage, int armor, int level, Rarity rarity, int value, String model, Ability ability){
		this.name = name;
		this.damage = damage;
		this.armor = armor;
		this.level_requirement = level;
		this.rarity = rarity;
		this.value = value;
		this.model = model;
		this.ability = ability;
	}
	
	public static boolean addItem(String name, int damage, int armor, int level, Rarity rarity, int value, String model, int weight, int refinement, int abilityID){
		boolean added = false;
		//TODO sql command to add new item to db
		//if added set added = true
		return added;
	}
	
	/**
	 * method to delete item by id
	 * @param ID
	 * @return - true if deleted else false
	 */
	public static boolean deleteItem(int ID){
		boolean deleted = false;
		//TODO sql command to delete item by id
		return deleted;
	}
	
	/**
	 * method to delete item by name
	 * @param name
	 * @return - true if deleted else false
	 */
	public static boolean deleteItem(String name){
		boolean deleted = false;
		//TODO sql command to delete item by name
		return deleted;
	}
	
	/**
	 * method to delete item by object
	 * @param item
	 * @return - true if deleted else false
	 */
	public static boolean deleteItem(Item item){
		//TODO
		return false;
	}
	
	/**
	 * method to find item by id
	 * @param ID
	 * @return - item if found else null
	 */
	public static Item findItem(int ID){
		Item result = null;
		//TODO sql command to get item by id
		return result;
	}
	
	/**
	 * method to find item by name
	 * @param name
	 * @return - item if found else null
	 */
	public static Item findItem(String name){
		Item result = null;
		//TODO sql command to get item by name
		return result;
	}
	
	/**
	 * set refinement
	 * @param refinement
	 * @return - true if set else false
	 */
	public boolean setRefinement(int refinement){
		boolean set = false;
		//TODO sql command to set refinement of this item in db
		//if success set this.refinement = refinement set = true
		return set;
	}
	
	/**
	 * finds next unused ID for primary key
	 * @return - next valid new id key
	 */
	private static int getNewID(){
		int id = 1;
		//TODO sql command to get collection of all used IDs
		//while collection contains(id){ id++}
		return id;
	}
	
	///***Public Accessors***///
	public String getName(){
		return this.name;
	}
	
	public int getDamage(){
		return this.damage;
	}
	
	public int getArmor(){
		return this.armor;
	}
	
	public int getLevel(){
		return this.level_requirement;
	}
	
	public String getRarity(){
		return this.rarity.toString();
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getModel(){
		return this.model;
	}
	
	
	public Ability getAbility(){
		Ability ability = null;
		Ability.findAbility(this.ability.getName());
		return ability;
	}

	public ObjectType getType() {
		return GUI.ObjectType.ITEM;
	}
}