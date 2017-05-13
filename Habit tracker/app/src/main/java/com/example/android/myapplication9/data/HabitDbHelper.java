package com.example.android.myapplication9.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "habit.db";
    private static final int DATABASE_VERSION = 1;


  public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE_HABIT =  "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " ("
                + HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitContract.HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitContract.HabitEntry.COLUMN_HABIT_COUNT + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_TABLE_HABIT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
