package helpers;

import java.util.List;

import dataAccess.DataAccess;
import gui.GUI;
import gui.GUI.ObjectType;

public class Ability {
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
	
	public static Ability findAbility(String name){
		Ability result = null;
		try{
			List<Ability> abilityList =DataAccess.getInstance().searchAbility(name, -1, "");
			result = abilityList.get(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	//***Public Accessors***//
	
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
