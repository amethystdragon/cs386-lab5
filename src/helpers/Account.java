package helpers;

import gui.GUI;
import gui.GUI.ObjectType;

public class Account {
	//id of account - primary key - unique
	private int ID;
	
	//login name of account - unique
	private String account_name;
	
	//password to login to account
	private String password;
	
	//email associated with account
	private String email;
	
	//first name of account holder
	private String first_name;
	
	//last name of account holder
	private String last_name;
	
	//ingame currency associated with account
	private int ingame_currency;
	
	/**
	 * private Constructor
	 */
	private Account(int ID, String accountName, String password, String email,
			String fName, String lName, int currency){
		this.ID = ID;
		this.account_name = accountName;
		this.password = password;
		this.email = email;
		this.first_name = fName;
		this.last_name = lName;
		this.ingame_currency = currency;
	}
	
	/**
	 * factory method to add new account to db
	 * @param accountName
	 * @param password
	 * @param email
	 * @param fName
	 * @param lName
	 * @param currency
	 * @return
	 */
	public static boolean addAccount(String accountName, String password, String email,
			String fName, String lName, int currency){
		boolean added = false;
		int id = getNewID();
		//TODO sql command to add account to db
		//if added set added = true
		return added;
	}
	
	/**
	 * delete account by id
	 * @param ID
	 * @return - true if deleted else false
	 */
	public static boolean deleteAccount(int ID){
		boolean deleted = false;
		//TODO sql command to delete account by id in db
		return deleted;
	}
	
	/**
	 * delete account by account_name
	 * @param accountName
	 * @return true if deleted else false
	 */
	public static boolean deleteAccount(String accountName){
		boolean deleted = false;
		//TODO sql command to delete account by account_name in db
		return deleted;
	}
	
	/**
	 * delete account by object
	 * @param account
	 * @return true if deleted else false
	 */
	public static boolean deleteAccount(Account account){
		return deleteAccount(account.getID());
	}
	
	/**
	 * method to change contact info of this account
	 * @param fName
	 * @param lName
	 * @param email
	 * @return - true if changed else false
	 */
	public boolean changeContactInfo(String fName, String lName, String email){
		boolean changed = false;
		//TODO sql command to change contact info
		return changed;
	}
	
	/**
	 * returns account with account_name and pass or null if not in db
	 * @param accountName
	 * @param password
	 * @return - account associated with name and pass or null if not in db
	 */
	public static Account login(String accountName, String password){
		Account login = null;
		//TODO sql command to find account with name and pass
		return login;
	}
	
	/**
	 * method to change currency of this account in db
	 * @return - true if changed else false
	 */
	public boolean changeCurrency(){
		boolean changed = false;
		//TODO sql command to change currency in db
		//if changed this.ingame_Currency = new currency value
		return changed;
	}
	
	/**
	 * method to find account by id
	 * @param ID
	 * @return account object or null if not found
	 */
	public static Account findAccount(int ID){
		Account account = null;
		//TODO sql command to find account by id
		return account;
	}
	
	/**
	 * method to find account by account_name
	 * @param accountName
	 * @return
	 */
	public static Account findAccount(String accountName){
		Account account = null;
		//TODO sql command to find account by name
		return account;
	}
	
	/**
	 * function to get next unused account id in db
	 * @return
	 */
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
	
	public String getPassword(){
		return this.password;
	}
	
	public String getAccountName(){
		return this.account_name;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getFirstName(){
		return this.first_name;
	}
	
	public String getLastName(){
		return this.last_name;
	}
	
	public int getCurrency(){
		return this.ingame_currency;
	}

	public ObjectType getType() {
		return GUI.ObjectType.ACCOUNT;
	}
}
