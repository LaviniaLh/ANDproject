package com.example.lavinia.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class CompletedActivities extends AppCompatActivity implements
            NavigationView.OnNavigationItemSelectedListener {
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    DatabaseActivities dba ;
    TextView date,startTime,finishTime, price, zone, carSpot;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_activities);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.completed_activities);
        myToolbar.setTitleTextColor(Color.BLACK);
        myToolbar.setNavigationIcon(R.mipmap.ic_list_black_24dp);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_list_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, myToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

        dba = new DatabaseActivities(this);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        email = sharedPref.getString("emailNewActivity", "");
        Cursor cursor = dba.getAllClosed(email);
        int i=1;
        if(cursor.moveToFirst())
        {
            do {
                if(i ==1)
                {
                    date = (TextView) findViewById(R.id.actOneDate);
                    startTime = (TextView) findViewById(R.id.actOneStartTime);
                    finishTime = (TextView) findViewById(R.id.actOneFinishTime);
                    price = (TextView) findViewById(R.id.actOnePrice);
                    zone = (TextView) findViewById(R.id.actOneZone);
                    carSpot = (TextView) findViewById(R.id.actOneCarSpot);

                    SharedPreferences sharedZone = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor1 = sharedZone.edit();
                    editor1.putString("zone1", cursor.getString(1));
                    editor1.commit();

                    SharedPreferences sharedSpot = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor2 = sharedSpot.edit();
                    editor2.putString("spot1", cursor.getString(2));
                    editor2.commit();

                    SharedPreferences sharedDate = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor3 = sharedDate.edit();
                    editor3.putString("date1", cursor.getString(3));
                    editor3.commit();

                    SharedPreferences sharedTime = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor4 = sharedTime.edit();
                    editor4.putString("time1", cursor.getString(4));
                    editor4.commit();
                }
                if(i ==2)
                {
                    date = (TextView) findViewById(R.id.actTwoDate);
                    startTime = (TextView) findViewById(R.id.actTwoStartTime);
                    finishTime = (TextView) findViewById(R.id.actTwoFinishTime);
                    price = (TextView) findViewById(R.id.actTwoPrice);
                    zone = (TextView) findViewById(R.id.actTwoZone);
                    carSpot = (TextView) findViewById(R.id.actTwoCarSpot);
                }
                if(i ==3)
                {
                    date = (TextView) findViewById(R.id.actThreeDate);
                    startTime = (TextView) findViewById(R.id.actThreeStartTime);
                    finishTime = (TextView) findViewById(R.id.actThreeFinishTime);
                    price = (TextView) findViewById(R.id.actThreePrice);
                    zone = (TextView) findViewById(R.id.actThreeZone);
                    carSpot = (TextView) findViewById(R.id.actThreeCarSpot);
                }
                if(i ==4)
                {
                    date = (TextView) findViewById(R.id.actFourDate);
                    startTime = (TextView) findViewById(R.id.actFourStartTime);
                    finishTime = (TextView) findViewById(R.id.actFourFinishTime);
                    price = (TextView) findViewById(R.id.actFourPrice);
                    zone = (TextView) findViewById(R.id.actFourZone);
                    carSpot = (TextView) findViewById(R.id.actFourCarSpot);
                }
                if(i ==5)
                {
                    date = (TextView) findViewById(R.id.actFiveDate);
                    startTime = (TextView) findViewById(R.id.actFiveStartTime);
                    finishTime = (TextView) findViewById(R.id.actFiveFinishTime);
                    price = (TextView) findViewById(R.id.actFivePrice);
                    zone = (TextView) findViewById(R.id.actFiveZone);
                    carSpot = (TextView) findViewById(R.id.actFiveCarSpot);
                }
                date.setText("Date: " + cursor.getString(3));
                startTime.setText("Start time: " + cursor.getString(4));
                finishTime.setText("Finish time: " + cursor.getString(5));
                price.setText("Price: " + String.valueOf(dba.getPrice(cursor.getString(4),cursor.getString(5))) + "kr");
                zone.setText("Zone: " + cursor.getString(1));
                carSpot.setText("Parking spot: " + cursor.getString(2));
                i++;
            }while(cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.changeSettings:
                Intent intent = new Intent (this, ChangeSettings.class);
                startActivity(intent);
                break;
            case R.id.newActivity:
                Intent intent2= new Intent (this, NewActivity.class);
                startActivity(intent2);
                break;
            case R.id.completedActivities:
                break;
            case R.id.openActivities:
                Intent intent3 = new Intent(this, OpenActivities.class);
                startActivity(intent3);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void delete(View v)
    {
        String date, start, zone, spot;

        Cursor cursor = dba.getAllClosed(email);
        if(cursor.getCount()==0)
        {
            Intent intent2 = new Intent (this, NewActivity.class);
            startActivity(intent2);
        }
        else {
            switch (v.getId()) {
                default:
                    break;
                case R.id.textOne:
                    cursor.moveToFirst();
                    date = cursor.getString(3);
                    start = cursor.getString(4);
                    zone = cursor.getString(1);
                    spot = cursor.getString(2);
                    if (dba.deleteOneClosed(email, zone, spot, date, start))
                        Toast.makeText(this, "Activity deleted", Toast.LENGTH_LONG).show();
                    break;
                case R.id.textTwo:
                    cursor.moveToPosition(1);
                    date = cursor.getString(3);
                    start = cursor.getString(4);
                    zone = cursor.getString(1);
                    spot = cursor.getString(2);
                    if (dba.deleteOneClosed(email, zone, spot, date, start))
                        Toast.makeText(this, "Activity deleted", Toast.LENGTH_LONG).show();
                    break;
                case R.id.textThree:
                    cursor.moveToPosition(2);
                    date = cursor.getString(3);
                    start = cursor.getString(4);
                    zone = cursor.getString(1);
                    spot = cursor.getString(2);
                    if (dba.deleteOneClosed(email, zone, spot, date, start))
                        Toast.makeText(this, "Activity deleted", Toast.LENGTH_LONG).show();
                    break;
                case R.id.textFour:
                    cursor.moveToPosition(3);
                    date = cursor.getString(3);
                    start = cursor.getString(4);
                    zone = cursor.getString(1);
                    spot = cursor.getString(2);
                    if (dba.deleteOneClosed(email, zone, spot, date, start))
                        Toast.makeText(this, "Activity deleted", Toast.LENGTH_LONG).show();
                    break;
                case R.id.textFive:
                    cursor.moveToPosition(4);
                    date = cursor.getString(3);
                    start = cursor.getString(4);
                    zone = cursor.getString(1);
                    spot = cursor.getString(2);
                    if (dba.deleteOneClosed(email, zone, spot, date, start))
                        Toast.makeText(this, "Activity deleted", Toast.LENGTH_LONG).show();
                    break;
                case R.id.textAll:
                    if (dba.deleteAllClosed(email))
                        Toast.makeText(this, "Activities deleted", Toast.LENGTH_LONG).show();
                    break;
            }
            cursor.close();
            Intent intent = new Intent(this, CompletedActivities.class);
            startActivity(intent);
        }
    }
}