package com.example.android.myapplication9.data;

import android.provider.BaseColumns;

public final class HabitContract {

    public static final class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habit";

        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_HABIT_NAME = "habit";

        public static final String COLUMN_HABIT_COUNT = "count";
    }

}
