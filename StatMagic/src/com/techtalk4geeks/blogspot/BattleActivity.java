package com.techtalk4geeks.blogspot;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class BattleActivity extends Activity
{
	MediaPlayer mp = new MediaPlayer();
	static String oppenent;
	static String battleIntro = "The " + oppenent + " attacked!";
	static MainActivity act = MainActivity.getActivity();
	ImageView battleSprite = (ImageView) BattleActivity.this
			.findViewById(R.id.battleSprite);
	TextView line1 = (TextView) BattleActivity.this
			.findViewById(R.id.firstline);

	TextView line2 = (TextView) BattleActivity.this
			.findViewById(R.id.secondline);
	
	TextView line3 = (TextView) BattleActivity.this
			.findViewById(R.id.thirdline);

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
			act.comNerd.dealDamage(act.user.getWeapon().getPOW());
			setContentView(R.layout.dev_battle);
			if (act.comNerd.getHP() <= 0)
			{
				// TODO: Say YOU WIN!
				line1.setText(String.valueOf("YOU WIN!"));

				line2.setText(String.valueOf(""));
				
				line3.setText(String.valueOf(""));
			}
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
