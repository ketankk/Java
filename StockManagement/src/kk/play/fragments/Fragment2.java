package kk.play.fragments;

import kk.play.stockmanagement.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment2 extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";

	public static Fragment2 newInstance(int sectionNumber) {
		Fragment2 fragment = new Fragment2();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Fragment2() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.cycles, container, false);
		return rootView;
	}

}