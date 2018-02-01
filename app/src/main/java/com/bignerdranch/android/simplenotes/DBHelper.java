package com.bignerdranch.android.simplenotes;

/**
 * Created by nrg on 28.01.2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by nrg on 27.01.2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    final String LOG_TAG = "myLogs";

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(LOG_TAG, "--- onCreate database ---");
        // создаем таблицу с полями
        sqLiteDatabase.execSQL("create table mytable ("
                + "id integer primary key autoincrement,"
                + "title text,"
                + "note text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
