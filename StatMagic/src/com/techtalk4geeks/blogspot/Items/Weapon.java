package com.techtalk4geeks.blogspot.Items;

import org.json.JSONException;
import org.json.JSONObject;

import com.techtalk4geeks.blogspot.User;

public class Weapon extends Item
{
	int myPOW;
	int myPrice;
	public Weapon(String name, int POW, int price) {
		super(name); 
		myPOW = POW;
		myPrice = price;
	}
	
	public Weapon(JSONObject json) throws Exception {
		super(json.getString("name"));
		myPOW = json.getInt("POW");
		myPrice = json.getInt("price");
	}
	
	public int getPOW() {
		return myPOW;
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
		json.put("POW", myPOW);
		json.put("price", myPrice);
		return json;
	}

	@Override
	public void change(User user)
	{
		// TODO Auto-generated method stub
		
	}
}
