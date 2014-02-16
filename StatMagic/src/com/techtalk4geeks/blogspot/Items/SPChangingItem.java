package com.techtalk4geeks.blogspot.Items;

import org.json.JSONException;
import org.json.JSONObject;

import com.techtalk4geeks.blogspot.User;

public class SPChangingItem extends Item
{
	int changeStatBy;
	public SPChangingItem(String name, int changeBy) {
		super(name); 
		changeStatBy = changeBy;
	}
	
	public void change(User user) {
		user.addSP(changeStatBy);
	}
	
	@Override
	public JSONObject toJSON() throws JSONException
	{
		JSONObject result = super.toJSON();
		result.put("changeStatBy", changeStatBy);
		return result;
	}
}
