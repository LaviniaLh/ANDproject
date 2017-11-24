package com.example.lavinia.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Geanina on 22-Nov-17.
 */

public class DatabaseSpots extends SQLiteOpenHelper{

    public static final String DATABASE_NAME3 = "spots.db";
    public static final String TABLE_NAME3 = "spots_table";
    public static final String ZONE = "ZONE";
    public static final String SPOT = "SPOT";

    public DatabaseSpots(Context context) {super(context, DATABASE_NAME3, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME3 + "(ZONE TEXT,SPOT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +  TABLE_NAME3);
        onCreate(db);
    }

    public boolean fillSpot (String zone, String spot)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ZONE, zone);
        contentValues.put(SPOT, spot);

        long result = db.update(TABLE_NAME3, contentValues, null,null);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean freeSpot(String zone, String spot)
    {
        SQLiteDatabase db = getWritableDatabase();

        long result = db.delete(TABLE_NAME3, ZONE + " = ? and " + SPOT + " = ?", new String[] {zone, spot});

        if(result == -1)
            return false;
        else
            return true;
    }

//    public boolean isFree(String zone, String spot)
//    {
//        SQLiteDatabase db = getWritableDatabase();
//
//        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME3 + " WHERE ZONE = ? AND SPOT = ? ", new String[] {zone,spot});
//
//        if(cursor.getCount()>0)
//            return false;
//        else
//            return true;
//    }
}