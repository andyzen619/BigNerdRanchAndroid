package com.android.andydesk.criminalintent;

import android.support.v4.app.Fragment;

import com.android.andydesk.criminalintent.Fragments.CrimeFragment;

public class CrimeActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
