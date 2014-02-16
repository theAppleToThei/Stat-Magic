package com.techtalk4geeks.blogspot.Items;

import org.json.JSONException;
import org.json.JSONObject;

public class TempNoEatPOWItems extends POWChangingItem
{
	int myChance;
	public TempNoEatPOWItems(String name, int changeBy, int chanceOfEating)
	{
		super(name, changeBy);
		myChance = chanceOfEating;
	}
	
	@Override
	public JSONObject toJSON() throws JSONException
	{
		JSONObject result = super.toJSON();
		result.put("changeStatBy", changeStatBy);
		return result;
	}
}
