package kk.play.stockmanagement;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

public class SearchResultsActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		handleIntent(getIntent());
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		handleIntent(intent);
	}
private void handleIntent(Intent intent){
	if(intent.ACTION_SEARCH.equals(intent.getAction())){
		String query=intent.getStringExtra(SearchManager.QUERY);
	}
}
}
