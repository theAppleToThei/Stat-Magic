package com.techtalk4geeks.blogspot;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.techtalk4geeks.blogspot.Items.HealingItem;
import com.techtalk4geeks.blogspot.Items.Item;
import com.techtalk4geeks.blogspot.Items.POWChangingItem;
import com.techtalk4geeks.blogspot.Items.SPEEDChangingItem;

public class User
{
	String myName = "User.getName()";
	int myLevel = 1;
	int myRank;
	// Date myBirthYear;
	protected int myHP;
	protected int mySP;

	protected int myPOW;
	protected int myDEF;
	protected int mySPEED;
	protected String myCity;
	int myAge;
	ArrayList<Item> myStuff = new ArrayList<Item>();
	Item Hamburger = new HealingItem("Hamburger", 49);
	Item Fries = new HealingItem("French Fries", 32);
	Item Coffee = new SPEEDChangingItem("Coffee", 2);
	Item Broccoli = new POWChangingItem("Broccoli", 4);
	Item Oatmeal = new POWChangingItem("Oatmeal", 3);
	Item Chocolate = new SPEEDChangingItem("Chocolate", 3);
	Item Pizza = new HealingItem("Pizza", 73);
	Item Taco = new HealingItem("Taco", 28);
	Item Bread = new HealingItem("Bread", 21);
	Item Sandwich = new HealingItem("Sandwich", 45);
	String[] rankStrings = new String[]
	{ "I Do Not Care", "Dork", "Geek", "Nerd", "Jock", "Blonde", "Teacher",
			"Hippie", "Animal Lover", "Shortie", "Stretch", "Hobbit", "Dwarf",
			"Vampire", "Ninja", "Gangster", "Emo", "Professor", "Coder", "Fox",
			"Werewolf", "Zombie", "Narwhal", "Alien", "Swag Master",
			"Princess", "Crafter", "Gamer", "Vlogger", "Predator", "Wrestler",
			"Super Hero", "Rich Person", "Baby", "Grandparent", "Biker", };

	ArrayList<Item> inventory = new ArrayList<Item>();
	public static ArrayList<String> rank_display = new ArrayList<String>();

	public User(String name, String rank, int age, String city)
	{
		for (int i = 0; i < rankStrings.length; i++)
		{
			rank_display.add(rankStrings[i]);
		}
		// myBirthYear = birthYear;
		// Date date = new Date(System.currentTimeMillis()) + 2100;
		myAge = age;
		myName = name;
		myRank = rank_display.indexOf(rank);
		// myLevel = (int)(age * 2);
		mySP = (int) (15); // myLevel * 1.5 / 5
		myHP = (int) (17);
		myCity = city;
		if (myRank == 2)
		{ // IF GEEK
			myPOW = (int) (mySP * 0.7);
			myDEF = (int) (mySP * 0.5);
		} else if (myRank == 3)
		{ // IF NERD
			myPOW = (int) (mySP * 0.5);
			myDEF = (int) (mySP * 0.7);
		} else
		{ // OTHERWISE
			myPOW = (int) (mySP * 0.4);
			myDEF = (int) (mySP * 0.6);
		} // DON'T FORGET
		mySPEED = 5;
	}

	public User(JSONObject jsonO) throws Exception
	{
		myName = jsonO.getString("myName");
		myAge = jsonO.getInt("myAge");
		myAge = jsonO.getInt("myHP");
		mySP = jsonO.getInt("mySP");
		myPOW = jsonO.getInt("myPOW");
		myDEF = jsonO.getInt("myDEF");
		mySPEED = jsonO.getInt("mySPEED");
		myRank = jsonO.getInt("myRank");
		myCity = jsonO.getString("myCity");
		JSONArray inventory = jsonO.getJSONArray("inventory");
		for (int i = 0; i < inventory.length(); i++)
		{
			JSONObject json = inventory.getJSONObject(i);
			String itemClass = json.getString("class");
		}
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject result = new JSONObject();
		result.put("myName", myName);
		result.put("myAge", myAge);
		result.put("myHP", myHP);
		result.put("mySP", mySP);
		result.put("myPOW", myPOW);
		result.put("myDEF", myDEF);
		result.put("mySPEED", mySPEED);
		result.put("myRank", myRank);
		result.put("myCity", myCity);
		JSONArray inventory = new JSONArray();
		for (Item i : myStuff) {
			inventory.put(i.toJSON());
		}
		result.put("inventory", inventory);
		return result;
	}
	
	public String getName()
	{
		return myName;
	}

	public void setName(String name)
	{
		myName = name;
	}

	public int getLevel()
	{
		return myLevel;
	}

	int getHP()
	{
		return myHP;
	}

	void setHP(int myHP)
	{
		this.myHP = myHP;
	}

	public void healHP(int HP)
	{
		myHP = myHP + HP;
	}

	public void addSP(int SP)
	{
		mySP = mySP + SP;
	}

	public void addPOW(int POW)
	{
		myPOW = myPOW + POW;
	}

	public void addDEF(int DEF)
	{
		myDEF += DEF;
	}

	public void addSPEED(int SPEED)
	{
		mySPEED += SPEED;
	}

	int getSP()
	{
		return mySP;
	}

	void setSP(int mySP)
	{
		this.mySP = mySP;
	}

	int getPOW()
	{
		return myPOW;
	}

	void setPOW(int myPOW)
	{
		this.myPOW = myPOW;
	}

	int getDEF()
	{
		return myDEF;
	}

	void setDEF(int myDEF)
	{
		this.myDEF = myDEF;
	}

	int getSPEED()
	{
		return mySPEED;
	}

	public void changeSPEEDby(int changedBy)
	{
		mySPEED = mySPEED + changedBy;
	}

	void setSPEED(int mySPEED)
	{
		this.mySPEED = mySPEED;
	}
}
