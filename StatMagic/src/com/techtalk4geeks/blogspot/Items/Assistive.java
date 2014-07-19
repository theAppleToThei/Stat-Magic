package com.techtalk4geeks.blogspot.Items;

import org.json.JSONException;
import org.json.JSONObject;

import com.techtalk4geeks.blogspot.User;

public class Assistive extends Item
{
	int myStat;
	int myPrice;
	public Assistive(String name, int statusNo, int price) {
		super(name); 
		myStat = statusNo;
		myPrice = price;
	}
	
	public Assistive(JSONObject json) throws Exception {
		super(json.getString("name"));
		myStat = json.optInt("Stat", 0);
		myPrice = json.optInt("price", 0);
	}
	
	public int getStatusEffect() {
		return myStat;
	}

	public int getPrice()
	{
		return myPrice;
	}
	
	public String getPriceString()
	{
		String myPriceString = String.valueOf(myPrice);
		return myPriceString;
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("name", myName);
		json.put("Stat", myStat);
		json.put("price", myPrice);
		return json;
	}

	@Override
	public void change(User user)
	{
		// TODO Auto-generated method stub
		
	}
}
