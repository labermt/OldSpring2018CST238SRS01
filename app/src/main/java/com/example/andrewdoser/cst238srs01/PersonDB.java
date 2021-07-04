package com.example.andrewdoser.cst238srs01;

import android.provider.BaseColumns;

public final class PersonDB {
    private PersonDB(){}

    public static class Person implements BaseColumns{
        public static final String TABLE_NAME = "Birthday";
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_MONTH = "Month";
        public static final String COLUMN_DAY = "Day";

    }
}
