package com.techtalk4geeks.blogspot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import org.json.JSONObject;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.techtalk4geeks.blogspot.Items.FunctionalItem;
import com.techtalk4geeks.blogspot.Items.Item;

import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Spinner;

//@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements LocationListener
{
	DatePicker myDatePicker;
	Boolean isSetup = true;
	Boolean isBattle = false;
	Boolean isCard = false;
	ArrayList<Item> inventory = new ArrayList<Item>();
	public int SPEEDHolder = 0;
	private static final long MIN_TIME = 400;
	private static final float MIN_DISTANCE = 1000;
	private View myView;
	//http://stackoverflow.com/questions/18690562/android-spinner-is-null
	public User user;
	public User comNerd = new User("Scientific Nerd", "Nerd", 13, "Winters", 3);

//	public static MainActivity sMainActivity;
	// File file;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
//		sMainActivity = MainActivity.this;
		super.onCreate(savedInstanceState);
		myView = getLayoutInflater().inflate(R.layout.setup, null);
		File file = new File(getFilesDir(), "user.txt");
		if (file.exists())
		{
			isSetup = false;
			isBattle = false;
			isCard = true;
			FileInputStream FIN;
			try
			{
				FIN = openFileInput("user.txt");
				InputStreamReader ISR = new InputStreamReader(FIN);
				BufferedReader br = new BufferedReader(ISR);
				String jsonString = br.readLine();
				JSONObject object = new JSONObject(jsonString);
				user = new User(object);
//				user.sendUserNotification();
				setContentView(R.layout.activity_main);
				setValuesForStatCard();
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
		{
			Item ATMCard = new FunctionalItem("ATM Card", 30);
			inventory.add(ATMCard);
			try
			{
				file.createNewFile();
			} catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setContentView(R.layout.setup);
			// myDatePicker = ((DatePicker)findViewById(R.id.typeSelecter));

			Spinner spinner = ((Spinner) findViewById(R.id.typeSelecter));
			for (String rankName : User.rank_display)
			{
				TextView text = new TextView(this);
				text.setText(rankName);
				spinner.addView(text);
			}
			Button doneButton = (Button) this.findViewById(R.id.done_button);
			// Button useButton1 = (Button) this.findViewById(R.id.use_button1);
//			Button eraseButton = (Button) this.findViewById(R.id.erase_button);
			doneButton.setOnClickListener(new View.OnClickListener()
			{
				public void onClick(View v)
				{
					String name = ((TextView) findViewById(R.id.nameField))
							.getText().toString();
					String rank = ((Spinner) findViewById(R.id.typeSelecter))
							.getSelectedItem().toString();
					String strAge = ((TextView) findViewById(R.id.ageField))
							.getText().toString();
					String city = ((TextView) findViewById(R.id.cityField))
							.getText().toString();
					int age = Integer.parseInt(strAge);
					user = new User(name, rank, age, city, inventory);
					// TODO: Save User
					try
					{
						JSONObject userJSON = user.toJSON();
						String userString = userJSON.toString();
						FileOutputStream FOS = openFileOutput("user.txt", 0);
						OutputStreamWriter OSW = new OutputStreamWriter(FOS);
						OSW.write(userString);
						OSW.flush();
						OSW.close();
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					setContentView(R.layout.activity_main);
					isSetup = false;
					isCard = true;
					setValuesForStatCard();
				}

			});

//			 eraseButton.setOnClickListener(new View.OnClickListener()
//			 {
//			 public void onClick(View v)
//			 {
////			  file.delete(); //POTENTIAL ERROR: FINAL TYPE FOR FILE
//			  throw new NullPointerException();
//			 }
//			 });
		}

		// NotificationCompat.Builder mBuilder =
		// new NotificationCompat.Builder(this)
		// .setSmallIcon(R.drawable.heartsprite)
		// .setContentTitle("POW Stats Are Up")
		// .setContentText("Your POW is now 8!");
		// // Creates an explicit intent for an Activity in your app
		// Intent resultIntent = new Intent(this, MainActivity.class);
		//
		// // The stack builder object will contain an artificial back stack for
		// the
		// // started Activity.
		// // This ensures that navigating backward from the Activity leads out
		// of
		// // your application to the Home screen.
		// TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// // Adds the back stack for the Intent (but not the Intent itself)
		// stackBuilder.addParentStack(MainActivity.class);
		// // Adds the Intent that starts the Activity to the top of the stack
		// stackBuilder.addNextIntent(resultIntent);
		// PendingIntent resultPendingIntent =
		// stackBuilder.getPendingIntent(
		// 0,
		// PendingIntent.FLAG_UPDATE_CURRENT
		// );
		// mBuilder.setContentIntent(resultPendingIntent);
		// NotificationManager mNotificationManager =
		// (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// // mId allows you to update the notification later on.
		// mNotificationManager.notify(10, mBuilder.build());

		// useButton1.setOnClickListener(new View.OnClickListener()
		// {
		// public void onClick(View v)
		// {
		// SPEEDHolder += 1;
		// }
		// });

		// WARNING: METHOD NOT OVER!

		// user.setName(view.getText().toString());
	}

	public void setValuesForStatCard()
	{
		TextView nameView = (TextView) MainActivity.this
				.findViewById(R.id.nameValue);
		nameView.setText(user.getName());
		TextView expView = (TextView) MainActivity.this
				.findViewById(R.id.exp_value);
		expView.setText(String.valueOf(user.getEXP()));
		TextView expMaxView = (TextView) MainActivity.this
				.findViewById(R.id.exp_max_value);
		expMaxView.setText(String.valueOf(user.getMaxEXP()));
		TextView levelView = (TextView) MainActivity.this
				.findViewById(R.id.levelValue);
		levelView.setText(String.valueOf(user.getLevel()));
		TextView rankView = (TextView) MainActivity.this
				.findViewById(R.id.rankValue);
//		Spinner spinner = (Spinner) myView.findViewById(R.id.typeSelecter);
//		Object rankInt = spinner.getSelectedItem();
//		String rankStr = String.valueOf(rankInt);
		rankView.setText(user.getRankName());
		TextView hpView = (TextView) MainActivity.this
				.findViewById(R.id.hpValue);
		hpView.setText(String.valueOf(user.getHP()));
		TextView hpMaxView = (TextView) MainActivity.this
				.findViewById(R.id.hpMaxValue);
		hpMaxView.setText(String.valueOf(user.getMaxHP()));
		TextView spView = (TextView) MainActivity.this
				.findViewById(R.id.spValue);
		spView.setText(String.valueOf(user.getSP()));
		TextView spMaxView = (TextView) MainActivity.this
				.findViewById(R.id.spMaxValue);
		spMaxView.setText(String.valueOf(user.getMaxSP()));
		TextView powView = (TextView) MainActivity.this
				.findViewById(R.id.powValue);
		powView.setText(String.valueOf(user.getPOW()));
		TextView defView = (TextView) MainActivity.this
				.findViewById(R.id.defValue);
		defView.setText(String.valueOf(user.getDEF()));
		TextView speedView = (TextView) MainActivity.this
				.findViewById(R.id.speedValue);
		speedView.setText(String.valueOf(user.getSPEED()));
		TextView weaponView = (TextView) MainActivity.this
				.findViewById(R.id.weaponValue);
		weaponView.setText(String.valueOf(user.getWeapon().getName()));
//		TextView inventory1 = (TextView) MainActivity.this
//				.findViewById(R.id.inventoryText1);
//		inventory1.setText(String.valueOf(user.getInventory().get(0).toString()));
//		TextView inventory2 = (TextView) MainActivity.this
//				.findViewById(R.id.inventoryText2);
//		inventory2.setText(String.valueOf(user.getInventory().get(0).toString()));
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu)
	// {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }
	//
	// private DatePickerDialog.OnDateSetListener datePickerListener
	// = new DatePickerDialog.OnDateSetListener() {
	//
	// // when dialog box is closed, below method will be called.
	// public void onDateSet(DatePicker view, int selectedYear,
	// int selectedMonth, int selectedDay) {
	// int year = selectedYear;
	// int month = selectedMonth;
	// int day = selectedDay;
	//
	// // set selected date into textview
	// tvDisplayDate.setText(new StringBuilder().append(month + 1)
	// .append("-").append(day).append("-").append(year)
	// .append(" "));
	//
	// // set selected date into datepicker also
	// dpResult.init(year, month, day, null);
	//
	// }
	// };

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		if (!isSetup)
		{
			inflater.inflate(R.menu.main, menu);
			return true;
		} else if (isBattle)
		{
			 inflater.inflate(R.menu.battle_menu, menu);
			return true;
		}
		return false;
	}

	public void resetName()
	{
		String name = ((TextView) findViewById(R.id.nameField)).getText()
				.toString();
		user.setName(name);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		switch (item.getItemId())
		{
		case R.id.stat:
			setContentView(R.layout.activity_main);
			setValuesForStatCard();
			return true;
		case R.id.inventory:
			setContentView(R.layout.inventory);
			isCard = false;
			return true;
		case R.id.map:
			setContentView(R.layout.map);
			LocationManager locationManager;
			GoogleMap map;
			map = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map_display)).getMap();
			if (map != null)
			{
				map.addMarker(new MarkerOptions().position(new LatLng(0, 0))
						.title("Marker"));
			}
			locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE,
					this);
			MarkerOptions mo = new MarkerOptions();
			mo.title("here");
			map.setMyLocationEnabled(true);
			// Marker here = map.addMarker(mo);

			isCard = false;
			return true;
		case R.id.battle:
			Intent battle = new Intent(this, BattleActivity.class);
			startActivity(battle);
			isCard = false;
			isBattle = true;
			return true;
		case R.id.settings:
			setContentView(R.layout.settings);
			isCard = false;
			return true;
		case R.id.crash:
			isCard = false;
			throw new NullPointerException(); // Crashes the app
			// case R.id.about:
			// //To Be Implemented
			// return true;
		case R.id.attack:
			return true;
		case R.id.special:
			return true;
		case R.id.inventorybattle:
			return true;
		case R.id.defend:
			return true;
		case R.id.flee:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onLocationChanged(Location arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2)
	{
		// TODO Auto-generated method stub

	}
	
	public User getMainUser() {
		return user;
	}
	
	public User getCOMNerd() {
		return comNerd;
	}
	
	public MainActivity getActivity() {
		return null;
	}
	
}