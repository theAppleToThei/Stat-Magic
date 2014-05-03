package com.techtalk4geeks.blogspot;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.ActionBar;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BattleActivity extends Activity
{
	MediaPlayer mp = new MediaPlayer();
	static String oppenent;
	static String battleIntro = "The " + oppenent + " attacked!";
	static MainActivity act = MainActivity.getActivity();
	Button dev_battle;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battle);

		// addEncounter();

		dev_battle = (Button) BattleActivity.this
				.findViewById(R.id.test_dummy_button);

		dev_battle.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Intent battleScreen = new Intent(BattleActivity.this, Battle.class);
				startActivity(battleScreen);
			}

		});
	}
	
}

// class myAsyncTask extends AsyncTask {
//
// myAsyncTask() {
// }
//
// // Executed on a special thread and all your
// // time taking tasks should be inside this method
// @Override
// protected void doInBackground() {
// myData = myParser.getDataFromWeb();
// return null;
// }
//
// }   