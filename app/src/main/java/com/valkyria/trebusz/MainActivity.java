package com.valkyria.trebusz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.Objects;

import static java.lang.Object.*;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private int numberOfTabs;
    SharedPreferences dataSP;
    boolean isConf;

    // kaprawy borsuk podgryza pędy młodej sosny
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSP = getSharedPreferences("Kwestionariusz", MODE_PRIVATE);
        boolean yes = dataSP.getBoolean("saved", false);
        if (yes != true) {
            Intent first_setup = new Intent(this, FirstSetupActivity.class);
            startActivity(first_setup);
        }


        numberOfTabs = 3;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return SearchFragment.newInstance();
                case 1:
                    return TimetableFragment.newInstance();
                case 2:
                    return AuthorsFragment.newInstance();
                default:
                    return null;
            }
        }


        @Override
        public int getCount() {
            return numberOfTabs;
        }

    }
}
