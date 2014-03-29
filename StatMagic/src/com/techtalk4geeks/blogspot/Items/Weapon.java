package com.techtalk4geeks.blogspot.Items;

import org.json.JSONException;
import org.json.JSONObject;

import com.techtalk4geeks.blogspot.User;

public class Weapon extends Item
{
	int myPOW;
	public Weapon(String name, int POW) {
		super(name); 
		myPOW = POW;
	}
	
	public Weapon(JSONObject json) throws Exception {
		super(json.getString("name"));
		myPOW = json.getInt("POW");
	}
	
	public int getPOW() {
		return myPOW;
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("name", myName);
		json.put("POW", myPOW);
		return json;
	}

	@Override
	public void change(User user)
	{
		// TODO Auto-generated method stub
		
	}
}
