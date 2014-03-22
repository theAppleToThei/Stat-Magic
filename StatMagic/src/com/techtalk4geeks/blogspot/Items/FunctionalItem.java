package com.techtalk4geeks.blogspot.Items;

import com.techtalk4geeks.blogspot.User;

public class FunctionalItem extends Item
{
	int myValue;

	public FunctionalItem(String name, int value)
	{ // ATM CARD
		super(name);
		myValue = value;
	}

	@Override
	public void change(User user)
	{

	}

}
