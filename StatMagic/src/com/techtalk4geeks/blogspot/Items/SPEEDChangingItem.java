package com.techtalk4geeks.blogspot.Items;

import org.json.JSONException;
import org.json.JSONObject;

import com.techtalk4geeks.blogspot.User;

public class SPEEDChangingItem extends Item
{
	int changeStatBy;
	public SPEEDChangingItem(String name, int changeBy) {
		super(name); 
		changeStatBy = changeBy;
	}
	
	public void change(User user) {
		user.addSPEED(changeStatBy);
	}
	
	@Override
	public JSONObject toJSON() throws JSONException
	{
		JSONObject result = super.toJSON();
		result.put("changeStatBy", changeStatBy);
		return result;
	}
}
