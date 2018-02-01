package com.bignerdranch.android.simplenotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.android.criminalintent.R;

import java.util.List;

/**
 * Created by nrg on 29.11.2017.
 */

public class NoteListFragment extends Fragment {
    // пока пусто
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>
    {
        private List<Note> mNotes;

        public CrimeAdapter(List<Note> notes)
        {
            mNotes = notes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_note, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position)
        {
            Note note = mNotes.get(position);
            //holder.mTitleTextView.setText(note.getTitle());
            holder.bindCrime(note);
        }

        @Override
        public int getItemCount()
        {
            return mNotes.size();
        }
    }
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        //public TextView mTitleTextView;
        private TextView mTitleTextView;
        private TextView mNoteTextView;
        //private CheckBox mSolvedCheckBox;
        private Note mNote;

        public void bindCrime(Note note)
        {
            mNote = note;
            mTitleTextView.setText(mNote.getTitle());
            mNoteTextView.setText(mNote.getTextNote());
            //mSolvedCheckBox.setChecked(mNote.isSolved());
        }

        public CrimeHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            //mTitleTextView = (TextView)itemView;
            mTitleTextView = (TextView)itemView.findViewById(R.id.list_item_crime_title_text_view);
            mNoteTextView = (TextView)itemView.findViewById(R.id.list_item_crime_date_text_view);
            //mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_solved_check_box);
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(getActivity(), mNote.getTitle()+" clicked!", Toast.LENGTH_SHORT).show();
            Log.i("SystemMessage", "OnClicked!!!"+ mNote.getTitle());

            //Intent intent = new Intent(getActivity(), NoteActivity.class);
            Intent intent = NoteActivity.newIntent(getActivity(), mNote.getId());
            startActivity(intent);
        }
    }

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private Button mCreateButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list,container, false );
        mCrimeRecyclerView = (RecyclerView)view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        mCreateButton = (Button)view.findViewById(R.id.note_create);
        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = NoteActivity.newIntent(getActivity(), null);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI()
    {
        NoteLab noteLab = NoteLab.get(getActivity());
        noteLab.upDateDB();
        List<Note> notes = noteLab.getNotes();

        if(mAdapter == null)
        {
            mAdapter = new CrimeAdapter(notes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }
        else
            mAdapter.notifyDataSetChanged();

    }
}
