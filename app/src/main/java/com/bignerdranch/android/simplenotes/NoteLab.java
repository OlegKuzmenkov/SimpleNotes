package com.bignerdranch.android.simplenotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by nrg on 29.11.2017.
 */

public class NoteLab
{
    final String LOG_TAG = "myLogs";

    private static NoteLab sNoteLab;
    private List<Note> mNotes;
    private DBHelper mDBHelper;

    public static NoteLab get(Context context)
    {
        if(sNoteLab == null)
            sNoteLab = new NoteLab(context);
        return sNoteLab;
    }

    private NoteLab(Context context)
    {
      mNotes = new ArrayList<>();
      /*for(int i=0;i<1;i++)
      {
          Note crime = new Note();
          crime.setTitle("Note #"+ i);
          crime.setTextNote("121312312");
          crime.setSolved(i%2 == 0);//для каждого второго преступления
          mNotes.add(crime);
      }*/
        mDBHelper = new DBHelper(context);
        // создаем объект для данных
        ContentValues cv = new ContentValues();
        // подключаемся к БД
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        Log.d(LOG_TAG, "--- Insert in mytable: ---");
        // подготовим данные для вставки в виде пар: наименование столбца - значение
        //db.delete("mytable",null,null);


        Log.d(LOG_TAG, "--- Rows in mytable: ---");
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query("mytable", null, null, null, null, null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int titleColIndex = c.getColumnIndex("title");
            int noteColIndex = c.getColumnIndex("note");
            int j=0;
            do {
                // получаем значения по номерам столбцов и пишем все в лог

                Log.d(LOG_TAG,
                        "ID = " + c.getInt(idColIndex) +
                                ", title = " + c.getString(titleColIndex) +
                                ", note = " + c.getString(noteColIndex));

                Note note = new Note();
                note.setTitle(c.getString(titleColIndex));
                note.setTextNote(c.getString(noteColIndex));
                mNotes.add(note);

                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        } else
            Log.d(LOG_TAG, "0 rows");
        c.close();
        }


    public void upDateDB()
    {
        ContentValues cv = new ContentValues();
        // подключаемся к БД
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        Log.d(LOG_TAG, "--- Insert in mytable: ---");
        // подготовим данные для вставки в виде пар: наименование столбца - значение
        db.delete("mytable",null,null);
        for(Note note : mNotes)
        {
            cv.put("title", note.getTitle());
            cv.put("note", note.getTextNote());
            db.insert("mytable", null, cv);
        }

        //cv.put("title", "Магазин");
        //cv.put("note", "Купить помидоры");
        // вставляем запись и получаем ее ID
        //long rowID = db.insert("mytable", null, cv);
        //Log.d(LOG_TAG, "row inserted, ID = " + rowID);
    }

    public List<Note> getNotes()
    {
        return mNotes;
    }
    public Note getCrime(UUID id)
    {
     for(Note note : mNotes)
     {
         if(note.getId().equals(id))
             return note;
     }
     return null;
    }
}
