package com.example.andrewdoser.cst238srs01;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Person.db";
    public static final String TABLE_NAME = "person_table";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "MONTH";
    public static final String COL_3 = "DAY";
    public final static String DATABASE_CREATE = "create table " + TABLE_NAME + "(" +"ID"+ " int PRIMARY KEY AUTOINCREMENT, " + " NAME text," + " MONTH text," + " DAY text" + ")";
    public DatabaseHelper DBHelper;
    public static SQLiteDatabase m_ObjDataBase; // This is global variable to access across the applicaiton

    public static void createDBInstance(Context pContext){
        if(DBHelper == null) {
            DBHelper = new WLDBHandler(pContext); // This will be your DB Handler Class
            m_cObjDataBase = DBHelper.openAndCreateDataBase(); // Initialze the DB Note: openAndCreateDataBase is a utility method created by you to do everything an return the DB object
        }
    }
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, int month, int day){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1, name);
        values.put(COL_2, month);
        values.put(COL_3, day);
        long result = db.insert(TABLE_NAME, null, values);
        if(result == -1)
            return false;
        else
            return true;
    }
}
