package helpers;

public class Skill {
	
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
	private Skill(int ID, String name, String description, int level)
	{
		this.ID = ID;
		this.name = name;
		this.description = description;
		this.level = level;
	}
	
	public static boolean addSkill(String name, String description, int level){
		boolean added = false;
		int ID = getNewID();
		if(findSkill(name) == null){
			//TODO sql command to add skill to database
			//set result = true if added
		}
		return added;
	}
	
	/**
	 * delete skill by name
	 * @param name
	 * @return - true if deleted else false
	 */
	public static boolean deleteSkill(String name){
		boolean result = false;
		//TODO query sql for ability name if found delete row if successful result = true 
		
		return result;
	}
	
	/**
	 * delete Skill by ID
	 * @param Skill_ID
	 * @return - true if deleted else false
	 */
	public static boolean deleteSkill(int skill_ID){
		boolean result = false;
		//TODO query sql for ability by ID if found delete row if successful result = true 
		
		return result;
	}	
	
	/**
	 * delete skill by Skill object
	 * @param Ability
	 * @return - true if deleted else false
	 */
	public static boolean deleteSkill(Skill skill){
		return deleteSkill(skill.getID());
	}	
	
	/**
	 * findSkill by name
	 * @param name - name to search
	 * @return - Skill object if found else null if not
	 */
	public static Skill findSkill(String name){
		Skill result = null;
		//TODO sql command to find skill with given name
		//result = result of sql query
		return result;
	}
	
	/**
	 * findSkill by id
	 * @param id - id to search
	 * @return - Skill object if found else null if not
	 */
	public static Skill findSkill(int ID){
		Skill result = null;
		//TODO sql command to find ability with given ID
		//result = result of sql query
		return result;
	}
	
	private static int getNewID(){
		int id = 1;
		//TODO sql command to get collection of all used IDs
		//while collection contains(id){ id++}
		return id;
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
}
