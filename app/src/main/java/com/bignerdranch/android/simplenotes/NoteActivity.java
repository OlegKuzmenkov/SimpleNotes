package com.bignerdranch.android.simplenotes;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class NoteActivity extends SingleFragmentActivity {

   private final static String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";

   public static Intent newIntent(Context packageContext, UUID crimeId)
   {
        Intent intent = new Intent(packageContext, NoteActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
   }
   @Override
   protected  Fragment createFragment()
   {
       //return  new NoteFragment();
       UUID crimeId = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
       return NoteFragment.newInstance(crimeId);
   }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
