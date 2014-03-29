package com.techtalk4geeks.blogspot.Items;

import com.techtalk4geeks.blogspot.User;

public class Weapon extends Item
{
	int myPOW;
	public Weapon(String name, int POW) {
		super(name); 
		myPOW = POW;
	}
	
	public int getPOW() {
		return myPOW;
	}

	@Override
	public void change(User user)
	{
		// TODO Auto-generated method stub
		
	}
}
