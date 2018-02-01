package com.bignerdranch.android.simplenotes;

import android.support.v4.app.Fragment;

/**
 * Created by nrg on 29.11.2017.
 */

public class NoteListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new NoteListFragment();
    }
}
