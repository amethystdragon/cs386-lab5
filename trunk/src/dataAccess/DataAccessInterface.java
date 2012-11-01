package dataAccess;

import helpers.Ability;
import helpers.Account;
import helpers.Character;
import helpers.Item;
import helpers.Skill;

import java.util.List;


public interface DataAccessInterface {

	
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
	public List<Account> searchUser(String username, String fname, String lname, String email);

	/**
	 * 
	 * @author Karl
	 * 
	 * @param name
	 * @param race
	 * @param username
	 * @return
	 */
	public List<Character> searchCharacter(String name, Character.Race race, String username);
	
	/**
	 * 
	 * @author Karl
	 * 
	 * @param name
	 * @return
	 */
	public List<Item> searchItem(String name, Item.Rarity rarity, String ability);
	
	/**
	 - Malcolm
	 * @param name
	 * @param level
	 * @return
	 */
	public List<Skill> searchSkill(String name, String level);
	
	
	/**
	 * Joe
	 * @param name
	 * @param level
	 * @return
	 */
	public List<Ability> searchAbility(String name, int level);
	
	//ADD
	
	/**
	 * Will
	 * @param username
	 * @param user
	 * @return
	 */
	public boolean addUser(String username, Account user);
	
	
	/**
	 * Karl
	 * @param name
	 * @param Character
	 * @return
	 */
	public boolean addCharacter(String name, Character Character);
	
	
	/**
	 * Karl
	 * @param name
	 * @param item
	 * @return
	 */
	public boolean addItem(String name, Item item);
	
	/**
	 * Malcolm
	 * @param name
	 * @param skill
	 * @return
	 */
	public boolean addSkill(String name, Skill skill);
	
	/**
	 * Joe
	 * @param name
	 * @param ability
	 * @return
	 */
	public boolean addAbility(String name, Ability ability);
	
	
	//EDIT
	/**
	 * Will
	 * @param username
	 * @param user
	 * @return
	 */
	public boolean editUser(String username, Account user);
	
	
	/**
	 * Karl
	 * @param name
	 * @param Character
	 * @return
	 */
	public boolean editCharacter(String name, Character Character);
	
	
	/**
	 * Karl
	 * @param name
	 * @param item
	 * @return
	 */
	public boolean editItem(String name, Item item);
	
	/**
	 * Malcolm
	 * @param name
	 * @param skill
	 * @return
	 */
	public boolean editSkill(String name, Skill skill);
	
	/**
	 * Joe
	 * @param name
	 * @param ability
	 * @return
	 */
	public boolean editAbility(String name, Ability ability);
	
	
	
	//DELETE
	/**
	 * Will
	 * @param username
	 * @param user
	 * @return
	 */
	public boolean deleteUser(String username);
	
	
	/**
	 * Karl
	 * @param name
	 * @param Character
	 * @return
	 */
	public boolean deleteCharacter(String name);
	
	
	/**
	 * Karl
	 * @param name
	 * @param item
	 * @return
	 */
	public boolean deleteItem(String name);
	
	/**
	 * Malcolm
	 * @param name
	 * @param skill
	 * @return
	 */
	public boolean deleteSkill(String name);
	
	/**
	 * Joe
	 * @param name
	 * @param ability
	 * @return
	 */
	public boolean deleteAbility(String name);
}
