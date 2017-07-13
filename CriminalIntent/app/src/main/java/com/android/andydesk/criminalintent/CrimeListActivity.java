package com.android.andydesk.criminalintent;

import android.support.v4.app.Fragment;

import com.android.andydesk.criminalintent.Fragments.CrimeListFragment;

/**
 * Created by AndyDesk on 7/6/2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
