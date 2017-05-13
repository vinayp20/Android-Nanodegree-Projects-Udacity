package com.example.android.myapplication9;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.myapplication9.data.HabitContract;
import com.example.android.myapplication9.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mHabitDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHabitDbHelper = new HabitDbHelper(this);
    }

    // method for inserting a new habit

    private void insertHabit() {

        SQLiteDatabase db = mHabitDbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, "Walking");
        contentValues.put(HabitContract.HabitEntry.COLUMN_HABIT_COUNT, 0);
        long insertionId = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, contentValues);

    }

    private Cursor habitRead() {

        SQLiteDatabase db = mHabitDbHelper.getReadableDatabase();

        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_HABIT_NAME,
                HabitContract.HabitEntry.COLUMN_HABIT_COUNT,
        };
        Cursor c = null;
        try {
            c = db.query(
                    HabitContract.HabitEntry.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );


            if (c == null) {
                return null;
            } else {
                return c;
            }
        } finally {
            c.close();
        }
    }


}
