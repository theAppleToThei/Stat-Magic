package com.techtalk4geeks.blogspot;

import android.support.v4.app.Fragment;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;

public class Battle extends Activity
{
	TextView line1;
	TextView line2;
	TextView line3;
	ImageView battleSprite;
	static MainActivity act = MainActivity.getActivity();
	MediaPlayer mp = new MediaPlayer();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		act.comNerd.setLevel(act.user.getLevel() + 10);
		setContentView(R.layout.dev_battle);
		ActionBar actionBar = getActionBar();
		actionBar.show();
		line1 = (TextView) Battle.this.findViewById(R.id.firstline);
		line2 = (TextView) Battle.this.findViewById(R.id.secondline);
		line3 = (TextView) Battle.this.findViewById(R.id.thirdline);
		battleSprite = (ImageView) Battle.this
				.findViewById(R.id.battleSprite);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
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
			int fate = rollDice();
			if (fate == 0)
			{ // IF MISSED
				line1.setText(act.user.getName());
				line2.setText("missed");
				line3.setText("");
			} else if (fate == 1 || fate == 2 || fate == 3 || fate == 4)
			{
				act.comNerd.dealDamage(act.user.getWeapon().getPOW()
						+ act.user.getPOW() - act.comNerd.getDEF());
				line1.setText(act.user.getName());
				line2.setText("attacks and deals ");
				line3.setText(act.user.getWeapon().getPOW() + act.user.getPOW()
						- act.comNerd.getDEF() + " HP of damage!");
				if (act.comNerd.getHP() <= 0)
				{
					// TODO: Say YOU WIN!
					line1.setText("YOU WON!");
					line2.setText(String.valueOf(act.user.getName()));
					line3.setText(String.valueOf("gained "
							+ act.comNerd.getLevel() * 2 + " EXP."));
					playFanfare();
					flickerBattleSprite();
					act.user.changeMoneyBy(act.comNerd.getLevel() * 2);
					act.user.addEXP(act.comNerd.getLevel() * 2);
					try
					{
						act.user.saveThyself(this);
					} catch (Exception e)
					{
						e.printStackTrace();
					}

					// myAsyncTask myWebFetch = new myAsyncTask();
					// myWebFetch.execute();
					Intent main = new Intent(this, MainActivity.class);
					startActivity(main);
				} else
				{
					act.comNerd.dealDamage(act.user.getWeapon().getPOW()
							+ act.user.getPOW() * 2 - act.comNerd.getDEF());
					line1.setText("WOAAAH!!");
					line2.setText(act.user.getName());
					line3.setText("dealt "
							+ (int) (act.user.getWeapon().getPOW()
									+ act.user.getPOW() * 2 - act.comNerd
										.getDEF()) + " damage!");
					if (act.comNerd.getHP() <= 0)
					{
						// TODO: Say YOU WIN!
						line1.setText("YOU WON!");
						line2.setText(String.valueOf(act.user.getName()));
						line3.setText(String.valueOf("gained "
								+ act.comNerd.getLevel() * 2 + " EXP."));
						playFanfare();
						flickerBattleSprite();
						act.user.changeMoneyBy(act.comNerd.getLevel() * 2);
						act.user.addEXP(act.comNerd.getLevel() * 2);
						try
						{
							act.user.saveThyself(this);
						} catch (Exception e)
						{
							e.printStackTrace();
						}

						// myAsyncTask myWebFetch = new myAsyncTask();
						// myWebFetch.execute();
						Intent main = new Intent(this, MainActivity.class);
						startActivity(main);
					}
				}
				return true;
			}
		case R.id.special:
			return true;
		case R.id.inventorybattle:
			return true;
		case R.id.defend:
			line1.setText(act.user.getName());
			line2.setText("is on guard.");
			return true;
		case R.id.flee:
			line1.setText(act.user.getName());
			line2.setText("ran away.");
			Intent main = new Intent(this, MainActivity.class);
			startActivity(main);

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void flickerBattleSprite()
	{
		battleSprite.setVisibility(0);
		SystemClock.sleep(50);
		battleSprite.setVisibility(100);
		SystemClock.sleep(50);
		battleSprite.setVisibility(0);
		SystemClock.sleep(50);
		battleSprite.setVisibility(100);
		SystemClock.sleep(50);
		battleSprite.setVisibility(0);
	}

	private void addEncounter()
	{
		line1.setText(String.valueOf("Encountered the "
				+ act.comNerd.getRankName() + "!"));
		line2.setText(String.valueOf(""));
		line3.setText(String.valueOf(""));
	}

	public static class PlaceholderFragment extends Fragment
	{

		public PlaceholderFragment()
		{
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_battle__stage,
					container, false);
			return rootView;
		}
	}

	private void playFanfare()
	{
		mp = MediaPlayer.create(Battle.this, R.raw.fanfare);
		mp.setOnCompletionListener(new OnCompletionListener()
		{

			@Override
			public void onCompletion(MediaPlayer mp)
			{
				// TODO Auto-generated method stub
				mp.release();
			}
		});
		mp.start();
	}

	public int rollDice()
	{
		int dice;
		dice = (int) (Math.random() * 6);
		return dice;
	}
}
