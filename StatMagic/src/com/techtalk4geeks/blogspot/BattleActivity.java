package com.techtalk4geeks.blogspot;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.ActionBar;
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
	TextView line1;
	TextView line2;
	TextView line3;
	ImageView battleSprite;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dev_battle);
		ActionBar actionBar = getActionBar();
		battleSprite = (ImageView) BattleActivity.this
				.findViewById(R.id.battleSprite);
		
		line1 = (TextView) BattleActivity.this
				.findViewById(R.id.firstline);
		
		line2 = (TextView) BattleActivity.this
				.findViewById(R.id.secondline);
		
		line3 = (TextView) BattleActivity.this
				.findViewById(R.id.thirdline);
		
		line1.setText(String.valueOf(""));
		line2.setText(String.valueOf(""));
		line3.setText(String.valueOf(""));
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
			act.comNerd.dealDamage(act.user.getWeapon().getPOW() + act.user.getPOW() - act.comNerd.getDEF());
			setContentView(R.layout.dev_battle);
			if (act.comNerd.getHP() <= 0)
			{
				// TODO: Say YOU WIN!
				line1.setText("YOU WON!");
				line2.setText(String.valueOf(act.user.getName()));
				line3.setText(String.valueOf("gained " + act.comNerd.getLevel() * 2 + " EXP."));
				playFanfare();
				flickerBattleSprite();
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
			return true;
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

	private void playFanfare()
	{
		mp = MediaPlayer.create(BattleActivity.this, R.raw.fanfare);
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

}

//class myAsyncTask extends AsyncTask    {
//	 
//    myAsyncTask()    {
//    }
//
//    // Executed on a special thread and all your
//    // time taking tasks should be inside this method
//    @Override
//    protected void doInBackground() {
//        myData = myParser.getDataFromWeb(); 
//        return null;
//    }
//    
//}   