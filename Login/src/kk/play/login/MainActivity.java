package kk.play.login;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.params.HttpParams;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings.NameValueTable;
import android.support.v4.util.Pair;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity {
	EditText user;
	EditText pass;
	Button submit;
	String userName, password;
	TextView text1;
	TextView text2;
	private String response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		user = (EditText) findViewById(R.id.user);
		pass = (EditText) findViewById(R.id.pass);
		submit = (Button) findViewById(R.id.button);
		text1 = (TextView) findViewById(R.id.passText);
		text2 = (TextView) findViewById(R.id.userText);

		submit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				userName = user.getText().toString();
				password = pass.getText().toString();
				try {
					CheckConn();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

	void CheckConn() throws IOException {

		ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInf = connManager.getActiveNetworkInfo();
		if (netInf != null && netInf.isConnected()) {
			Post();
		} else {
			Toast.makeText(getApplicationContext(), "No Network Access",
					Toast.LENGTH_LONG).show();
		}
	}

	void Post() throws IOException {

		String urlLocal = "http://10.0.2.2/web/Android/login/action.php";
		String urlServer = "";
		URL url = new URL(urlLocal);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);

		OutputStream os = conn.getOutputStream();

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,
				"UTF-8"));

		Uri.Builder builder = new Uri.Builder().appendQueryParameter("user",
				userName).appendQueryParameter("pass", password);
		String query = builder.build().getEncodedQuery();

		conn.connect();
		writer.write(query);
		writer.flush();
		writer.close();
		os.close();

		String line = "", response = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		while ((line = br.readLine()) != null) {
			response += line;
		}

		Log.v("s", response + " " + query);

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
