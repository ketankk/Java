package kk.play.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity{
Button button1;
Button button2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
	}

	

	public void selectFrag(View view)
	
	{
		
		Fragment fr;
		if(view==findViewById(R.id.button1))
		
			fr=new FragmentOne();
		else fr=new FragmentTwo();
		FragmentManager fm=getFragmentManager();
		FragmentTransaction ft=fm.beginTransaction();
		ft.replace(R.id.fragment_replace, fr);
		ft.commit();
		
		
		
		
		
		
		
	}



	
}
