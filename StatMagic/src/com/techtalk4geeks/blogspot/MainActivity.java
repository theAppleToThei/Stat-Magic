 package com.techtalk4geeks.blogspot;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		User alex = new User("Alex Baratti", Rank.FROG, 1999);
		TextView nameView = (TextView) this.findViewById(R.id.nameValue);
		nameView.setText(alex.getName());
		TextView levelView = (TextView) this.findViewById(R.id.levelValue);
		levelView.setText(String.valueOf(alex.getLevel()));
		TextView hpView = (TextView) this.findViewById(R.id.hpValue);
		hpView.setText(String.valueOf(alex.getHP()));
		TextView spView = (TextView) this.findViewById(R.id.spValue);
		spView.setText(String.valueOf(alex.getSP()));
		TextView powView = (TextView) this.findViewById(R.id.powValue);
		powView.setText(String.valueOf(alex.getPOW()));
		TextView defView = (TextView) this.findViewById(R.id.defValue);
		defView.setText(String.valueOf(alex.getDEF()));
		TextView speedView = (TextView) this.findViewById(R.id.speedValue);
		speedView.setText(String.valueOf(alex.getSPEED()));
//		alex.setName(view.getText().toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
