package kk.play.chat;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Chat extends ActionBarActivity {
	public static final String YOUR_APPLICATION_ID = "";
	public static final String YOUR_CLIENT_KEY = "";
	public static String[] email_arr;
	private static SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		List<String> emailList = getEmailList();
	}

	private List<String> getEmailList() {
		List<String> lst = new ArrayList();
		Account[] accounts = AccountManager.get(this).getAccounts();

		for (Account account : accounts) {
			if (Patterns.EMAIL_ADDRESS.matcher(account.name).matches()) {
				lst.add(account.name);
			}
		}

		return lst;
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
