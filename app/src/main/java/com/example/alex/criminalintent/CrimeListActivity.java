package com.example.alex.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by Alex on 25.08.2016.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
