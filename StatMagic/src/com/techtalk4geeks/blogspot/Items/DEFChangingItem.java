package com.techtalk4geeks.blogspot.Items;

import org.json.JSONException;
import org.json.JSONObject;

import com.techtalk4geeks.blogspot.User;

public class DEFChangingItem extends Item
{
	int changeStatBy;
	public DEFChangingItem(String name, int changeBy) {
		super(name); 
		changeStatBy = changeBy;
	}
	
	public void change(User user) {
		user.addDEF(changeStatBy);
	}

	@Override
	public JSONObject toJSON() throws JSONException
	{
		JSONObject result = super.toJSON();
		result.put("changeStatBy", changeStatBy);
		return result;
	}
}
