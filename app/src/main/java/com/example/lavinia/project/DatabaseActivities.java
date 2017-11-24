package com.example.lavinia.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.util.Calendar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.lavinia.project.DatabaseHelper.DATABASE_NAME;
import static com.example.lavinia.project.R.id.price;
import static com.example.lavinia.project.R.id.startTime;
import static com.example.lavinia.project.R.string.pay;

/**
 * Created by Geanina on 22-Nov-17.
 */

public class DatabaseActivities extends SQLiteOpenHelper{

    public static final String DATABASE_NAME2 = "activities.db";
    public static final String TABLE_NAME2 = "activities_table";
    public static final String E_MAIL = "E_MAIL";
    public static final String ZONE = "ZONE";
    public static final String SPOT = "SPOT";
    public static final String DATE = "DATE";
    public static final String START_TIME = "START_TIME";
    public static final String FINISH_TIME = "FINISH_TIME";
    public static final String CLOSED = "CLOSED";

    public DatabaseSpots dbspots;

    public DatabaseActivities(Context context) {
        super(context, DATABASE_NAME2, null, 2);
        dbspots = new DatabaseSpots(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME2 + "(E_MAIL TEXT NOT NULL, ZONE TEXT NOT NULL, " +
                "SPOT TEXT NOT NULL, DATE TEXT NOT NULL, START_TIME TEXT NOT NULL, FINISH_TIME TEXT, CLOSED text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +  TABLE_NAME2);
        onCreate(db);
    }

    public boolean newBooking(String email, String zone, String spot, String date,
                              String start_time)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(E_MAIL, email);
        contentValues.put(ZONE, zone);
        contentValues.put(SPOT, spot);
        contentValues.put(DATE, date);
        contentValues.put(START_TIME, start_time);
        contentValues.put(CLOSED, "false");

        Cursor cursor = getOpen(email);
        if(cursor.getCount()>0) {
            return false;
        }

        boolean a =false;
        if(dbspots.fillSpot(zone, spot)) {
            long result = db.insert(TABLE_NAME2, null, contentValues);
            if (result == -1)
                a = false;
            else
                a= true;
        }
        return a;
    }

    public boolean closeBooking(String email, String zone, String spot, String date,
                                String start_time, String finish_time)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(E_MAIL,email);
        contentValues.put(ZONE, zone);
        contentValues.put(SPOT, spot);
        contentValues.put(DATE, date);
        contentValues.put(START_TIME, start_time);
        contentValues.put(FINISH_TIME, finish_time);
        contentValues.put(CLOSED, "true");

        boolean a=false;
        if(dbspots.freeSpot(zone,spot)) {
            long result = db.update(TABLE_NAME2, contentValues, "E_MAIL = ? and ZONE = ? and SPOT= ?",
                    new String[]{email, zone, spot});
            if (result == -1)
                a= false;
            else
                a= true;
        }
        return a;
    }

    public Cursor getAllClosed(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from "+ TABLE_NAME2 + " where CLOSED= ?  and E_MAIL = ?";
        Cursor cursor = db.rawQuery(query, new String[] {"true", email});
        return cursor;
    }

    public Cursor getOpen(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ TABLE_NAME2 + " where CLOSED=? and E_MAIL = ?",
                new String[] {"false",email});
        return cursor;
    }

    public boolean deleteAllClosed(String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME2, E_MAIL + "= ? " + " AND CLOSED = ?" ,new String[] {email, "true"});
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean deleteOneClosed(String email, String zone, String spot,String date, String starttime)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME2, E_MAIL + " = ? and " + ZONE + " = ? and " + SPOT + " = ? and "+
                DATE + " = ? and " + START_TIME + " = ?", new String[] {email, zone, spot, date, starttime});
        if(result == -1)
            return false;
        else
            return true;
    }

    public long getPrice(String start, String finish)
    {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        long totalPrice=0;
        try {
            Date startDate = timeFormat.parse(start);
            Date finishDate = timeFormat.parse(finish);
            long difference = finishDate.getTime() - startDate.getTime();

            if(difference<0)
            {
                Date dateMax = timeFormat.parse("24:00:00");
                Date dateMin = timeFormat.parse("00:00:00");
                difference=(dateMax.getTime() -startDate.getTime() )+(finishDate.getTime()
                        -dateMin.getTime());
            }

            int days = (int) (difference / (1000*60*60*24));
            int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
            int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);

            totalPrice = hours*60 + min;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return totalPrice;
    }
}