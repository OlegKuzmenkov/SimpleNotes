package com.bignerdranch.android.simplenotes;

import java.util.Date;
import java.util.UUID;

/**
 * Created by nrg on 27.11.2017.
 */

public class Note {
    private UUID mId;
    private String mTitle;
    private  String mTextNote;
    private Date mDate;
    private boolean mSolved;

    public Note()
    {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTextNote() { return mTextNote;}

    public void setTextNote(String title) {
        mTextNote = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
