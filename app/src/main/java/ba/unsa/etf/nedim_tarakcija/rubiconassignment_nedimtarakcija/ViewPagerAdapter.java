package ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int tabCount;

    public ViewPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            FragmentMovies fragmentMovies = new FragmentMovies();
            return fragmentMovies;
        }
        else if(position == 1) {
            FragmentTvShows fragmentTvShows = new FragmentTvShows();
            return fragmentTvShows;
        }
        else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
