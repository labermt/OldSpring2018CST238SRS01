package com.example.andrewdoser.cst238srs01;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

public class PersonDbHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PersonDB.Person.TABLE_NAME + " (" +
                    PersonDB.Person._ID + " INTEGER PRIMARY KEY," +
                    PersonDB.Person.COLUMN_NAME + " TEXT," +
                    PersonDB.Person.COLUMN_MONTH + " TEXT," +
                    PersonDB.Person.COLUMN_DAY + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PersonDB.Person.TABLE_NAME;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Person.db";


    public PersonDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    public boolean insertData(String name, String month, String day){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PersonDB.Person.COLUMN_NAME, name);
        values.put(PersonDB.Person.COLUMN_MONTH, month);
        values.put(PersonDB.Person.COLUMN_DAY, day);
        long result = db.insert(PersonDB.Person.TABLE_NAME, null, values);
        if(result == -1)
            return false;
        else
            return true;
    }
}
