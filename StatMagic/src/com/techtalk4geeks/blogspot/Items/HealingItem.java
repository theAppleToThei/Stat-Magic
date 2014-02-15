package com.techtalk4geeks.blogspot.Items;

import com.techtalk4geeks.blogspot.User;

public class HealingItem extends Item
{
	int myHPChange;
	public HealingItem(String name, int changeHP)
	{
		super(name); 
		myHPChange = changeHP;
	}
	
	public void heal(User user) {
		user.healHP(myHPChange);
	}
}
