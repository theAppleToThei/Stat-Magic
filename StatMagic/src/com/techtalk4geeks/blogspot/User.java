package com.techtalk4geeks.blogspot;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.techtalk4geeks.blogspot.Items.FunctionalItem;
import com.techtalk4geeks.blogspot.Items.HealingItem;
import com.techtalk4geeks.blogspot.Items.Item;
import com.techtalk4geeks.blogspot.Items.POWChangingItem;
import com.techtalk4geeks.blogspot.Items.SPChangingItem;
import com.techtalk4geeks.blogspot.Items.SPEEDChangingItem;
import com.techtalk4geeks.blogspot.Items.Weapon;

public class User
{
	String myName = "User.getName()";
	int myLevel = 1;
	int myRank;
	// Date myBirthYear;
	protected int myHP;
	protected int mySP;

	static String myRankName;

	protected int myPOW;
	protected int myDEF;
	protected int mySPEED;
	String myRank2;
	protected String myCity;
	protected int myMaxHP;
	protected int myMaxSP;
	protected int myEXP;
	protected int myMaxEXP;
	Weapon myWeapon;
	Weapon pencil = new Weapon("Pencil", 1);
	Weapon foamSword = new Weapon("Foam Sword", 3);
	Weapon oldfootballshoe = new Weapon("Old Football Shoe", 3);
	int myAge;
	ArrayList<Item> myStuff = new ArrayList<Item>();
	Item hamburger = new HealingItem("Hamburger", 49);
	Item fries = new HealingItem("French Fries", 32);
	Item coffee = new SPEEDChangingItem("Coffee", 2);
	Item broccoli = new POWChangingItem("Broccoli", 4);
	Item oatmeal = new POWChangingItem("Oatmeal", 3);
	Item chocolate = new SPEEDChangingItem("Chocolate", 3);
	Item pizza = new HealingItem("Pizza", 73);
	Item taco = new HealingItem("Taco", 28);
	Item bread = new HealingItem("Bread", 21);
	Item sandwich = new HealingItem("Sandwich", 45);
	Item candy = new SPChangingItem("Candy", 5);
	Item sugarBag = new HealingItem("Bag of Sugar", 45);
	Item egg = new POWChangingItem("Egg", 2);
	Item iceCream = new SPChangingItem("Ice Cream", 50);
	Item sushi = new HealingItem("Sushi", 19);
	Item pasta = new HealingItem("Pasta", 61);
	Item gum = new SPChangingItem("Gum", 4);
	Item chicken = new HealingItem("Chicken", 89);
	Item grapes = new SPChangingItem("Grapes", 27);

	String[] rankStrings = new String[]
	{ "I Do Not Care", "Dork", "Geek", "Nerd", "Jock", "Blonde", "Teacher",
			"Hippie", "Animal Lover", "Shortie", "Stretch", "Hobbit", "Dwarf",
			"Vampire", "Ninja", "Gangster", "Emo", "Professor", "Coder", "Fox",
			"Werewolf", "Zombie", "Narwhal", "Alien", "Swag Master",
			"Princess", "Crafter", "Gamer", "Vlogger", "Predator", "Wrestler",
			"Super Hero", "Rich Person", "Baby", "Grandparent", "Biker", };

	public static ArrayList<String> rank_display = new ArrayList<String>();

	public User(String name, String rank, int age, String city,
			ArrayList<Item> invent)
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
		myRank2 = rank;
		myLevel = 1;
		mySP = (int) (15); // myLevel * 1.5 / 5
		myHP = 17;
		myMaxHP = 17;
		myMaxSP = mySP;
		myCity = city;
		myEXP = 0;
		myMaxEXP = 3;
		myStuff = invent;
		if (myRank == 2)
		{ // IF GEEK
			myPOW = (int) (mySP * 0.7);
			myDEF = (int) (mySP * 0.5);
			myWeapon = foamSword;
			myRankName = "Geek";
		} else if (myRank == 3)
		{ // IF NERD
			myPOW = (int) (mySP * 0.5);
			myDEF = (int) (mySP * 0.7);
			myWeapon = pencil;
			myRankName = "Nerd";
		} else
		{ // OTHERWISE
			myPOW = (int) (mySP * 0.4);
			myDEF = (int) (mySP * 0.6);
			myWeapon = new Weapon("Hand", 1);
		} // DON'T FORGET
		mySPEED = 5;
	}

	public User(String name, String rank, int age, String city, int level)
	{
		myAge = age;
		myName = name;
		myRank = rank_display.indexOf(rank);
		myRank2 = rank;
		myLevel = level;
		mySP = (int) (myLevel * 1.5); // myLevel * 1.5 / 5
		myHP = myLevel * 2;
		myMaxHP = myHP;
		myMaxSP = mySP;
		myCity = city;
		myEXP = 0;
		myMaxEXP = 3;
		if (myRank == 2)
		{ // IF GEEK
			myPOW = (int) (mySP * 0.7);
			myDEF = (int) (mySP * 0.5);
			myWeapon = foamSword;
			myRankName = "Geek";
		} else if (myRank == 3)
		{ // IF NERD
			myPOW = (int) (mySP * 0.5);
			myDEF = (int) (mySP * 0.7);
			myWeapon = pencil;
			myRankName = "Nerd";
		} else
		{ // OTHERWISE
			myPOW = (int) (mySP * 0.4);
			myDEF = (int) (mySP * 0.6);
			myWeapon = new Weapon("Hand", 1);
		} // DON'T FORGET
		mySPEED = 5;
	}

	public User(JSONObject jsonO) throws Exception
	{
		myName = jsonO.getString("myName");
		myAge = jsonO.getInt("myAge");
		myHP = jsonO.getInt("myHP");
		mySP = jsonO.getInt("mySP");
		myPOW = jsonO.getInt("myPOW");
		myDEF = jsonO.getInt("myDEF");
		mySPEED = jsonO.getInt("mySPEED");
		myRank = jsonO.getInt("myRank");
		myCity = jsonO.getString("myCity");
		myLevel = jsonO.getInt("myLevel");
		myEXP = jsonO.getInt("myEXP");
		myMaxEXP = jsonO.getInt("myMaxEXP");
		myMaxHP = jsonO.getInt("myMaxHP");
		myMaxSP = jsonO.getInt("myMaxSP");
		JSONObject weaponJSON = jsonO.getJSONObject("myWeapon");
		myWeapon = new Weapon(weaponJSON);
		JSONArray inventory = jsonO.getJSONArray("myStuff");
		for (int i = 0; i < inventory.length(); i++)
		{
			JSONObject json = inventory.getJSONObject(i);
			String itemClass = json.getString("class");
			// TODO: create object of the class specified in itemClass
			// TODO: set attributes of item
		}
		// TODO Add EXP and Level
	}

	public JSONObject toJSON() throws JSONException
	{
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
		result.put("myLevel", myLevel);
		result.put("myEXP", myEXP);
		result.put("myMaxEXP", myMaxEXP);
		result.put("myMaxHP", myMaxHP);
		result.put("myMaxSP", myMaxSP);
		result.put("myWeapon", myWeapon.toJSON());
		JSONArray inventory = new JSONArray();
		for (Item i : myStuff)
		{
			inventory.put(i.toJSON());
		}
		result.put("myStuff", inventory);
		return result;
	}

	public String getName()
	{
		return myName;
	}

	public String getRankName()
	{
		return myRankName;
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

	int getRank()
	{
		return myRank;
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

	int getMaxHP()
	{
		return myMaxHP;
	}

	int getMaxEXP()
	{
		return myMaxEXP;
	}

	int getEXP()
	{
		return myEXP;
	}

	int getMaxSP()
	{
		return myMaxSP;
	}

	ArrayList<Item> getInventory()
	{
		return myStuff;
	}

	void setMaxHP(int setTo)
	{
		myMaxHP = setTo;
	}

	void setMaxSP(int setTo)
	{
		myMaxSP = setTo;
	}

	void incrementLevel()
	{
		myLevel = myLevel + 1;
	}

	public void healAll()
	{
		myHP = myMaxHP;
		mySP = myMaxSP;
	}

	public void healSP()
	{
		mySP = myMaxSP;
	}

	public Weapon getWeapon()
	{
		return myWeapon;
	}

	public void setMaxEXP(int incrementBy)
	{
		myMaxEXP = myMaxEXP + incrementBy;
	}

	public void sendUserNotification()
	{
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				null).setSmallIcon(R.drawable.nerd_sprite)
				.setContentTitle("My notification")
				.setContentText("Hello World!");

		Intent resultIntent = new Intent(null, MainActivity.class);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(null);

		stackBuilder.addParentStack(MainActivity.class);

		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		// NotificationManager mNotificationManager =
		// (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		//
		// mNotificationManager.notify(null, mBuilder.build());
	}

	public void dealDamage(int damage)
	{
		myHP -= damage;
	}

	public void levelUp()
	{
		setMaxHP(getLevel() * 3 + getMaxHP());
		setMaxSP(getLevel() * 2 + getMaxSP());
		setPOW(myPOW + 3);
		setDEF(myDEF + 3);
		incrementLevel();
		healAll();
		setMaxEXP(getLevel() * 3);
		// NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
		// null).setSmallIcon(R.drawable.nerd_sprite)
		// .setContentTitle("My notification")
		// .setContentText("Hello World!");

		// Intent resultIntent = new Intent(null, MainActivity.class);
		//
		// TaskStackBuilder stackBuilder = TaskStackBuilder.create(null);
		//
		// stackBuilder.addParentStack(MainActivity.class);
		//
		// stackBuilder.addNextIntent(resultIntent);
		// PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
		// PendingIntent.FLAG_UPDATE_CURRENT);
		// mBuilder.setContentIntent(resultPendingIntent);
		// // NotificationManager mNotificationManager =
		// // (NotificationManager)
		// getSystemService(Context.NOTIFICATION_SERVICE);
		// //
		// // mNotificationManager.notify(null, mBuilder.build());
	}

	public void addEXP(int add)
	{
		myEXP += add;
		checkForLevelUp();
	}

	public void saveThyself(Context context) throws Exception
	{
		JSONObject userJSON = toJSON();
		String userString = userJSON.toString();
		FileOutputStream FOS = context.openFileOutput("user.txt", 0);
		OutputStreamWriter OSW = new OutputStreamWriter(FOS);
		OSW.write(userString);
		OSW.flush();
		OSW.close();
	}

	private void checkForLevelUp()
	{
		if (getEXP() >= getMaxEXP())
		{
			levelUp();
		}
	}

}
