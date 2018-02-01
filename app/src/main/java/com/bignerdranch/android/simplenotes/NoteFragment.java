package com.bignerdranch.android.simplenotes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.bignerdranch.android.criminalintent.R;

import java.util.UUID;

/**
 * Created by nrg on 27.11.2017.
 */

public class NoteFragment extends Fragment
{
    private Note mNote;
    private EditText mTitleField,mTextNoteField;
    private Button mDateButton,mSaveButton,mDeleteButton;
    private CheckBox mSolvedCheckBox;

    private static final String ARG_CRIME_ID = "crime_id";

    //
    private TextView mTextViewGoodDate;
    //

    public static NoteFragment newInstance(UUID crimeId)
    {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        NoteFragment fragment = new NoteFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //mNote = new Note();
        //UUID crimeId = (UUID)getActivity().getIntent().getSerializableExtra(NoteActivity.EXTRA_CRIME_ID);
        UUID crimeId = (UUID)getArguments().getSerializable(ARG_CRIME_ID);
        mNote = NoteLab.get(getActivity()).getCrime(crimeId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_note, container, false);
        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTextNoteField = (EditText)v.findViewById(R.id.note_text);

        if(mNote !=null) {
            mTitleField.setText(mNote.getTitle());
            mTextNoteField.setText(mNote.getTextNote());
        }

        Log.d("mylog", "3dswdwe");
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //mNote.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mSaveButton = (Button)v.findViewById(R.id.note_save);
        mSaveButton.setOnClickListener(new View.OnClickListener(){
        @Override
            public void onClick(View view) {
            Log.d("mylog", "Save");
            if(mNote != null){
                mNote.setTitle(mTitleField.getText().toString());
                mNote.setTextNote(mTextNoteField.getText().toString());
            }
            else{
                mNote = new Note();
                mNote.setTitle(mTitleField.getText().toString());
                mNote.setTextNote(mTextNoteField.getText().toString());
                NoteLab.get(getActivity()).getNotes().add(mNote);
            }
            getActivity().finish();
        }
        });

        mDeleteButton = (Button)v.findViewById(R.id.note_delete);
        mDeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("mylog", "Delete");
                NoteLab.get(getActivity()).getNotes().remove(mNote);
                getActivity().finish();
            }
        });

        if(mNote == null)
            mDeleteButton.setVisibility(View.GONE);
        else
            mDeleteButton.setVisibility(View.VISIBLE);

        return v;
    }
}
