package ba.unsa.etf.nedim_tarakcija.rubiconassignment_nedimtarakcija;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.vpViewPager);
        viewPager.setOffscreenPageLimit(2);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tlTabLayout);
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
    }
}
