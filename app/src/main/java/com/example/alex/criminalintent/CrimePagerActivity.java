package com.example.alex.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Alex on 29.08.2016.
 */
public class CrimePagerActivity extends FragmentActivity {

    private ViewPager viewerPager;
    private ArrayList<Crime> crimeList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewerPager = new ViewPager(this);
        viewerPager.setId(R.id.viewPager);
        setContentView(viewerPager);

        crimeList = CrimeData.getCrimes(this).getCrimeList();

        FragmentManager fm = getSupportFragmentManager();
        viewerPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = crimeList.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return crimeList.size();
            }
        });

        UUID newId = (UUID) getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for (int i=0; i< crimeList.size();i++)
        {
            if (crimeList.get(i).getId().equals(newId))
            {
                viewerPager.setCurrentItem(i);
                break;
            }
        }
    }
}
