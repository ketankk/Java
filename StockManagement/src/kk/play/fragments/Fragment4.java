package kk.play.fragments;

import android.app.Fragment;
import android.os.Bundle;

public class Fragment4 extends Fragment{
	private static final String ARG_SECTION_NUMBER = "section_number";

	private static final String fragment_tag="others";
	public static Fragment1 newInstance(int sectionNumber) {
		Fragment1 fragment = new Fragment1();
		
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Fragment4() {
	}

}
