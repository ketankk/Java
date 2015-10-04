package kk.play.stockmanagement;

import kk.play.adapters.CyclesAdapter;
import kk.play.database.MySQLiteHelper;
import android.app.SearchManager;
import android.app.AppOpsManager.OnOpChangedListener;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	CyclesAdapter cycleAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cycleAdapter = new CyclesAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(cycleAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.search)
				.getActionView();
		if (searchView != null) {
			searchView.setSearchableInfo(searchManager
					.getSearchableInfo(getComponentName()));
			Log.d("H", "gg");
		}
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Intent addItem=new Intent(this,AddItem.class);
		startActivity(addItem);
		return super.onOptionsItemSelected(item);
	}

}
