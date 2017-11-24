package com.example.lavinia.project;


import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.StringBuilderPrinter;

import static com.example.lavinia.project.DatabaseActivities.TABLE_NAME2;

/**
 * Created by Geanina on 11/18/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "park.db";
    public static final String TABLE_NAME = "park_table";
    public static final String COL_1 = "FIRST_NAME";
    public static final String COL_2 = "LAST_NAME";
    public static final String COL_3 = "E_MAIL";
    public static final String COL_4 = "PASSWORD";
    public static final String COL_5 = "REGISTRATION_PLATE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(FIRST_NAME TEXT NOT NULL, LAST_NAME TEXT NOT NULL," +
                "E_MAIL TEXT PRIMARY KEY NOT NULL, PASSWORD TEXT NOT NULL, REGISTRATION_PLATE TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +  TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String fname, String lname, String email, String password, String regplate)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,fname);
        contentValues.put(COL_2,lname);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,password);
        contentValues.put(COL_5,regplate);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateData(String fname, String lname, String email, String password, String regplate)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,fname);
        contentValues.put(COL_2,lname);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,password);
        contentValues.put(COL_5,regplate);
        db.update(TABLE_NAME, contentValues, "E_MAIL = ?", new String[] {email});
        return true;
    }

    public boolean findData(String email, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+COL_3+", "+COL_4+" FROM "+ TABLE_NAME +
                " WHERE E_MAIL =? AND PASSWORD = ?", new String[] {email,password});

        if (cursor.getCount()==0)
            return false;
        else
            return true;
    }

    public Cursor getData(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME + " WHERE E_MAIL = ? ",
                new String[] {email});
        return cursor;
    }
}