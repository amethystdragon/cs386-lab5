package dataAccess;

import helpers.Ability;
import helpers.Account;
import helpers.Item;
import helpers.Skill;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataParser {

	public static List<Ability> parseAbility(File file){
		List<Ability> units = new ArrayList<Ability>();
		Scanner scan;
		try {
			scan = new Scanner(file);
			
			String line = scan.nextLine();
			
			//Read until finding the unit header
			while(line != null && !line.equals("==Unit=="))
				line = scan.nextLine();
			
			if(scan.hasNext()) line = scan.nextLine();//gets the next line
			else line=null;
			
			//Read until the end of the file or until the next header
			while(line != null && !line.contains("==")){
				if(line.equals("")) break;
				String[] lineArray = line.split(",");
				try{
					//units.add(new Unit(lineArray[0], lineArray[1], Integer.parseInt(lineArray[2]), Integer.parseInt(lineArray[3]), Integer.parseInt(lineArray[4])));
				}catch(IndexOutOfBoundsException | NumberFormatException e){ 
				}//Ignore incorrect lines
				if(scan.hasNext()) line = scan.nextLine();//gets the next line
				else line=null;
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return units;
	}
	public static List<Account> parseAccount(File file){
		List<Account> timeshares = new ArrayList<Account>();
		Scanner scan;
		try {
			scan = new Scanner(file);
			
			String line = scan.nextLine();
			
			//Read until finding the unit header
			while(line != null && !line.equals("==Timeshares=="))
				line = scan.nextLine();
			
			if(scan.hasNext()) line = scan.nextLine();//gets the next line
			else line=null;
			
			//Read until the end of the file or until the next header
			while(line != null && !line.contains("==")){
				if(line.equals("")) break;
				String[] lineArray = line.split(",");
				try{
//					timeshares.add(new TimeShare(new Customer(lineArray[0], lineArray[1], null), new Unit(lineArray[2], lineArray[3], 0, 0, 0), Integer.parseInt(lineArray[4])));
				}catch(IndexOutOfBoundsException | NumberFormatException e){ 
				}//Ignore incorrect lines
				if(scan.hasNext()) line = scan.nextLine();//gets the next line
				else line=null;
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return timeshares;
	}
	
	public static List<Character> parseCharacter(File file){
		List<Character> customers = new ArrayList<Character>();
		Scanner scan;
		try {
			scan = new Scanner(file);
			
			String line = scan.nextLine();
			
			//Read until finding the unit header
			while(line != null && !line.equals("==Customers=="))
				line = scan.nextLine();
			
			if(scan.hasNext()) line = scan.nextLine();//gets the next line
			else line=null;
			
			//Read until the end of the file or until the next header
			while(line != null && !line.contains("==")){
				if(line.equals("")) break;
				String[] lineArray = line.split(",");
				try{
//					customers.add(new Customer(lineArray[0], lineArray[1], lineArray[2]));
				}catch(IndexOutOfBoundsException e){ 
				}//Ignore incorrect lines
				if(scan.hasNext()) line = scan.nextLine();//gets the next line
				else line=null;
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return customers;
	}
	
	public static List<Item> parseItem(File file){
		List<Item> customers = new ArrayList<Item>();
		Scanner scan;
		try {
			scan = new Scanner(file);
			
			String line = scan.nextLine();
			
			//Read until finding the unit header
			while(line != null && !line.equals("==Customers=="))
				line = scan.nextLine();
			
			if(scan.hasNext()) line = scan.nextLine();//gets the next line
			else line=null;
			
			//Read until the end of the file or until the next header
			while(line != null && !line.contains("==")){
				if(line.equals("")) break;
				String[] lineArray = line.split(",");
				try{
//					customers.add(new Customer(lineArray[0], lineArray[1], lineArray[2]));
				}catch(IndexOutOfBoundsException e){ 
				}//Ignore incorrect lines
				if(scan.hasNext()) line = scan.nextLine();//gets the next line
				else line=null;
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return customers;
	}
	
	public static List<Skill> parseSkill(File file){
		List<Skill> customers = new ArrayList<Skill>();
		Scanner scan;
		try {
			scan = new Scanner(file);
			
			String line = scan.nextLine();
			
			//Read until finding the unit header
			while(line != null && !line.equals("==Customers=="))
				line = scan.nextLine();
			
			if(scan.hasNext()) line = scan.nextLine();//gets the next line
			else line=null;
			
			//Read until the end of the file or until the next header
			while(line != null && !line.contains("==")){
				if(line.equals("")) break;
				String[] lineArray = line.split(",");
				try{
//					customers.add(new Customer(lineArray[0], lineArray[1], lineArray[2]));
				}catch(IndexOutOfBoundsException e){ 
				}//Ignore incorrect lines
				if(scan.hasNext()) line = scan.nextLine();//gets the next line
				else line=null;
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return customers;
	}
	
	
}
