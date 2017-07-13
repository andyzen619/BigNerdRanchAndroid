package com.android.andydesk.criminalintent;

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

    public String getmTitle() {
        return mTitle;
    }

    public Date getmDate() {

        for(int i = 0; i < 10; i ++) {

        }
        return mDate;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
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
}
