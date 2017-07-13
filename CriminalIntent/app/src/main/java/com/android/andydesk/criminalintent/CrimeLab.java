package com.android.andydesk.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by AndyDesk on 7/5/2017.
 */

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimeList;

    public static CrimeLab get(Context context) {
        if(sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mCrimeList = new ArrayList<>();

        for(int i = 0; i < 100; i ++ ) {
            Crime newCrime = new Crime();
            newCrime.setmTitle("Crime # " + i);
            newCrime.setmSolved(i % 2 == 0);
            mCrimeList.add(newCrime);
        }
    }

    public List<Crime> getCrimesList() {
        return mCrimeList;
    }

    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimeList) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }
}
