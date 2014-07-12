package com.techtalk4geeks.blogspot.Items;

import org.json.JSONException;
import org.json.JSONObject;

import com.techtalk4geeks.blogspot.User;

public class Defensive extends Item
{
	int myDEF;
	int myPrice;
	public Defensive(String name, int DEF, int price) {
		super(name); 
		myDEF = DEF;
		myPrice = price;
	}
	
	public Defensive(JSONObject json) throws Exception {
		super(json.getString("name"));
		myDEF = json.getInt("DEF");
		myPrice = json.optInt("price", 0);
	}
	
	public int getDEF() {
		return myDEF;
	}
	
	public int getPrice() {
		return myPrice;
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("name", myName);
		json.put("DEF", myDEF);
		json.put("price", myPrice);
		return json;
	}

	@Override
	public void change(User user)
	{
		// TODO Auto-generated method stub
		
	}
}
