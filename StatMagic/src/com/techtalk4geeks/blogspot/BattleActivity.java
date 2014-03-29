package com.techtalk4geeks.blogspot;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class BattleActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dev_battle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.battle_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		switch (item.getItemId())
		{
		case R.id.attack:
			return true;
		case R.id.special:
			return true;
		case R.id.inventorybattle:
			return true;
		case R.id.defend:
			return true;
		case R.id.flee:
			Intent main = new Intent(this, MainActivity.class);
			startActivity(main);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
