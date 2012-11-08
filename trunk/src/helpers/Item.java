package helpers;

import gui.GUI;
import gui.GUI.ObjectType;

public class Item {
	
	public enum Rarity{
		Common, Uncommon, Rare, Mythic, Legendary, Unique;

		public static Rarity getRRarity(String string) {
			switch(string){
			case "Common":
				return Common;
			case "Uncommon":
				return Uncommon;
			case "Rare":
				return Rare;
			case "Mythic":
				return Mythic;
			case "Legendary":
				return Legendary;
			case "Unique":
				return Unique;
			default:
				return null;
			}
		}
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
	private String ability;
	
	/**
	 * private constructor
	 */
	public Item(String name, int damage, int armor, int level, Rarity rarity, int value, String model, String ability){
		this.name = name;
		this.damage = damage;
		this.armor = armor;
		this.level_requirement = level;
		this.rarity = rarity;
		this.value = value;
		this.model = model;
		this.ability = ability;
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
	
	
	public String getAbility(){
		return this.ability;
	}

	public ObjectType getType() {
		return GUI.ObjectType.ITEM;
	}
}