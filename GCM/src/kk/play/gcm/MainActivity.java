package kk.play.gcm;

import java.io.IOException;

import com.google.android.gcm.GCMRegistrar;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView lblMessage;
	Controller aController;

	AsyncTask<Void, Void, Void> mRegisterTask;
	static String name;
	static String email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		aController = (Controller) getApplicationContext();

		if (!aController.isConnectingToInternet()) {
			aController.showAlertDialogue(MainActivity.this,
					"Internet Connection Error", "Please connect to internet",
					false);
			return;
		}
		Intent i = getIntent();
		name = i.getStringExtra("name");
		email = i.getStringExtra("email");
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);
		lblMessage = (TextView) findViewById(R.id.lblmsg);

		registerReceiver(mHandleMessageReceiver, new IntentFilter(
				Config.DISPLAY_MESSAGE_ACTION));
		final String regId = GCMRegistrar.getRegistrationId(this);
		if (regId.equals("")) {
			GCMRegistrar.register(this, Config.GOOGLE_SENDER_ID);
		} else {
			if (GCMRegistrar.isRegisteredOnServer(this)) {
				Toast.makeText(getApplicationContext(), "Already registered",
						Toast.LENGTH_LONG).show();
			} else {
				final Context context = this;
				mRegisterTask = new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						try {
							aController.Register(context, name, email, regId);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						mRegisterTask = null;
					}

				};
				mRegisterTask.execute(null, null, null);

			}
		}
	}

	private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {

			String newMessage = intent.getExtras().getString(
					Config.EXTRA_MESSAGE);
			aController.acquireWakeLock(getApplicationContext());
			lblMessage.append(newMessage + " ");
Toast.makeText(getApplicationContext(), "Got Message"+newMessage, Toast.LENGTH_LONG).show();
			aController.releaseWakeLock();
		}

	};

	@Override
	protected void onDestroy() {
		// Cancel AsyncTask
		if (mRegisterTask != null) {
			mRegisterTask.cancel(true);
		}
		try {
			// Unregister Broadcast Receiver
			unregisterReceiver(mHandleMessageReceiver);

			// Clear internal resources.
			GCMRegistrar.onDestroy(this);

		} catch (Exception e) {
			Log.e("UnRegister Receiver Error", "> " + e.getMessage());
		}
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
