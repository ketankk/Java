package kk.play.volleytest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	String urlString = "http://androidsrc.net/api/sample_files/sample.html";
	String urlJSON = "http://androidsrc.net/api/sample_files/sample.json";
	Button stringLoaderBtn, jsonLoaderBtn,button;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv=(TextView)findViewById(R.id.textView1);
		stringLoaderBtn=(Button)findViewById(R.id.buttonLoadString);
		stringLoaderBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NetworkStringLoader loader=new NetworkStringLoader(MainActivity.this, tv);		
				loader.requestString(urlString);
			}
		});
		jsonLoaderBtn=(Button)findViewById(R.id.buttonJSONRequest);
		jsonLoaderBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NetworkJSONLoader loader=new NetworkJSONLoader(MainActivity.this, tv);
				loader.requestJSON(urlJSON);
			}
		});
		button=(Button)findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
ProgressDialog dialog=new ProgressDialog(MainActivity.this);
dialog.setTitle("GG");
dialog.setCancelable(true);
dialog.setMessage("Coming...");
dialog.show();
		}
		});


	}



	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.main, menu); return true; }
	 * 
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { // Handle
	 * action bar item clicks here. The action bar will // automatically handle
	 * clicks on the Home/Up button, so long // as you specify a parent activity
	 * in AndroidManifest.xml. int id = item.getItemId(); if (id ==
	 * R.id.action_settings) { return true; } return
	 * super.onOptionsItemSelected(item); }
	 */
}
