package com.android.andydesk.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.android.andydesk.criminalintent.Fragments.CrimeFragment;

import java.util.List;
import java.util.UUID;

/**
 * Created by AndyDesk on 8/3/2017.
 */

public class CrimePagerActivity extends AppCompatActivity {
    public static ViewPager mViewPager;
    private List<Crime> mCrimes;
    private static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";
    private Button mFirstButton;
    private Button mLastButton;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        setContentView(R.layout.activity_crime_pager);
        mFirstButton = (Button) findViewById(R.id.first_button);
        mLastButton = (Button) findViewById(R.id.last_button);
        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager);
        mCrimes = CrimeLab.get(this).getCrimesList();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }

    public Intent newIntent(UUID crimeId) {
        Intent intent = new Intent(getApplicationContext(), CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;

    }




}
