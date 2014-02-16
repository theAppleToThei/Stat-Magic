package com.techtalk4geeks.blogspot.Items;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.json.JSONException;
import org.json.JSONObject;

import com.techtalk4geeks.blogspot.User;

public abstract class Item
{
	protected String myName;

	public Item(String name)
	{
		myName = name;
	}

	public String getName()
	{
		return myName;
	}

	public abstract void change(User user);

	public JSONObject toJSON() throws JSONException
	{
		JSONObject result = new JSONObject();
		result.put("myName", myName);
		result.put("class", this.getClass().getName());
		return result;
	}
	
	public static Item fromJSON(JSONObject json) throws Exception  {
		String name = json.getString("myName");
		String className = json.getString("class");
		Class<?> itemClass = Class.forName(className);
		Constructor<Item> ctor = (Constructor<Item>) itemClass.getConstructor(String.class);
	    Item instance = ctor.newInstance(name);
	    return instance;
	}
	                              
}
