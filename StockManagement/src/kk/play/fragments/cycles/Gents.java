package kk.play.fragments.cycles;

import java.util.List;

import kk.play.database.CyclesDBHandler;
import kk.play.database.MySQLiteHelper;
import kk.play.entity.Cycles;
import kk.play.stockmanagement.ImageDownload;
import kk.play.stockmanagement.R;
import android.app.ActionBar.LayoutParams;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Gents extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";

	private static final String fragment_tag="gents";
	private static final String type="Gents";

	public static Gents newInstance(int sectionNumber) {
		Gents fragment = new Gents();
		
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Gents() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.cycles, container, false);

		
		String url="https://www.google.co.in/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png";

		
		//Populate View
		TableLayout layout=(TableLayout)rootView.findViewById(R.id.table);

		//Get list of all cycles of type=Gents;
		CyclesDBHandler gentsHandler=new CyclesDBHandler(getActivity());
		List<Cycles> cycleList=gentsHandler.getCyclesByType(type);		

		//for(Cycles cycle:cycleList){
		TableRow row=new TableRow(getActivity());
		
		ImageView itemImage=new ImageView(getActivity());
		TextView itemName=new TextView(getActivity());
		TextView itemDetail=new TextView(getActivity());
		Button decre=new Button(getActivity());
		Button incre=new Button(getActivity());
		
		new ImageDownload(itemImage).execute(url);
		itemName.setText(cycleList.size()+"gnt");
		    
		row.addView(itemName);
		row.addView(itemImage);
		layout.addView(row);
		//}
		return rootView;
	}

}
