package com.android.andydesk.criminalintent;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by AndyDesk on 6/28/2017.
 */

public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private boolean mRequiresPolice;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getDate() {

        return DateFormat.getDateInstance().format(mDate) ;
    }

    public boolean ismSolved() {
        return mSolved;
    }


    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    public UUID getId() {
        return mId;
    }

    public boolean requiresPolice() {
        return mRequiresPolice;
    }

    public void setmRequiresPolice(boolean requirePolice) {
        mRequiresPolice = requirePolice;
    }
}
