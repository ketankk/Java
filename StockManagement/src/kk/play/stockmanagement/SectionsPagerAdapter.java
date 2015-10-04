package kk.play.stockmanagement;

import kk.play.fragments.Fragment1;
import kk.play.fragments.Fragment2;
import kk.play.fragments.Fragment3;
import kk.play.fragments.Fragment4;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
	private final static int noTabs = 5;

	public SectionsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 1:
			return Fragment3.newInstance(position + 1);
		case 2:
			return Fragment2.newInstance(position + 1);
		case 3:
			return Fragment1.newInstance(position + 1);
		default:

			return Fragment4.newInstance(position + 1);

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
