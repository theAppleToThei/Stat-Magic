package com.techtalk4geeks.blogspot.Items;

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
}
