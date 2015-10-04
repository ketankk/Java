package kk.play.adapters;

import kk.play.fragments.cycles.Gents;
import kk.play.fragments.cycles.Kids;
import kk.play.fragments.cycles.Ladies;
import kk.play.fragments.cycles.Others;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class CyclesAdapter extends FragmentPagerAdapter {
	private final static int noTabs = 4;

	public CyclesAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		Log.d("pos", ("kk"+position));

		switch (position) {
		case 1:
			return Gents.newInstance(position );
		case 2:
			return Ladies.newInstance(position );
		case 3:
			return Kids.newInstance(position );
		
			default:
				return Others.newInstance(position );

		}
	}

	@Override
	public int getCount() {
		return noTabs;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case 0:
			return "Gents";
		case 1:
			return "Ladies";
		case 2:
			return "Kids";
		default:
			return "Others";
		}

	}
}
