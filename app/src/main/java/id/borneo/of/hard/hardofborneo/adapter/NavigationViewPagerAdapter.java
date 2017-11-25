package id.borneo.of.hard.hardofborneo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import id.borneo.of.hard.hardofborneo.fragment.EventFragment;
import id.borneo.of.hard.hardofborneo.fragment.OnlineFragment;
import id.borneo.of.hard.hardofborneo.fragment.TimelineFragment;

/**
 *
 */
public class NavigationViewPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<Fragment> fragments = new ArrayList<>();
	private TimelineFragment currentFragment;

	public NavigationViewPagerAdapter(FragmentManager fm) {
		super(fm);
//
		fragments.clear();
		fragments.add(TimelineFragment.newInstance(0));
		fragments.add(EventFragment.newInstance(1));
		fragments.add(OnlineFragment.newInstance(2));
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	/**
	 * Get the current fragment
	 */
	public Fragment getCurrentFragment() {
		return currentFragment;
	}
}