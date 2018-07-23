package ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija;

import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.vpViewPager);
        viewPager.setOffscreenPageLimit(2);

        tabLayout = (TabLayout) findViewById(R.id.tlTabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("MOVIES"));
        tabLayout.addTab(tabLayout.newTab().setText("TV SHOWS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        search = (SearchView) findViewById(R.id.svSearch);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final String s = newText;
                displaySearchResults(s);
                return true;
            }
        });
    }

    public void displaySearchResults(String newText) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        android.support.v4.app.Fragment f;

        Bundle bundle = new Bundle();
        bundle.putString("query", newText);

        if(tabLayout.getSelectedTabPosition() == 0) {
            f = new FragmentSearchMovies();
        }
        else {
            f = new FragmentSearchTvShows();
        }

        f.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment2, f);
        fragmentTransaction.addToBackStack(null);

        if(newText.length() >= 3) {
            tabLayout.setVisibility(View.INVISIBLE);
            viewPager.setVisibility(View.INVISIBLE);
            fragmentManager.popBackStack();
            fragmentTransaction.commit();
        }
        else {
            tabLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);
            fragmentManager.popBackStack();
        }
    }
}
