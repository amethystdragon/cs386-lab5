package helpers;

import java.sql.SQLException;
import java.util.List;

import dataAccess.DataAccess;

public class Skill implements GameObject{

	//id of skill primary key
	private int ID;

	//name of skill
	private String name;

	//description of skill
	private String description;

	//level requirement of skill
	private int level;

	/**
	 * private constructor
	 * @param ID
	 * @param name
	 * @param description
	 * @param level
	 */
	public Skill(int ID, String name, String description, int level)
	{
		this.ID = ID;
		this.name = name;
		this.description = description;
		this.level = level;
	}

	public static boolean addSkill(String name, String description, int level){
		boolean added = false;
		if(findSkill("", name, "") == null){
			try {
				added = DataAccess.getInstance().addSkill(name, description, level);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return added;
	}

	/**
	 * delete skill by name
	 * @param name
	 * @return - true if deleted else false
	 */
	public static boolean deleteSkillByName(String name){
		boolean result = false;
		try {
			result = DataAccess.getInstance().deleteSkillByName(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * delete Skill by ID
	 * @param Skill_ID
	 * @return - true if deleted else false
	 */
	public static boolean deleteSkillByID(int skill_ID){
		boolean result = false; 
		try {
			result = DataAccess.getInstance().deleteSkillByID(skill_ID);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}	

	/**
	 * delete skill by Skill object
	 * @param Ability
	 * @return - true if deleted else false
	 */
	public static boolean deleteSkill(Skill skill){
		return deleteSkillByID(skill.getID());
	}	

	/**
	 * updateSkill by name
	 * @param name - name to search
	 * @return - Skill object if found else null if not
	 */
	public static boolean updateSkill(String ID, String name, String description, String level){
		boolean result = false;
		try {
			result = DataAccess.getInstance().updateSkill(ID, name, description, level);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * findSkill by name
	 * @param name - name to search
	 * @return - Skill object if found else null if not
	 */
	public static List<Skill> findSkill(String skill_ID, String name, String level){
		List<Skill> result = null;
		try {
			result = DataAccess.getInstance().findSkill(skill_ID, name, level);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		return GameObject.ObjectType.SKILL;
	}
}
