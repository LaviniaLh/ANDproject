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
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.lavinia.project.R.id.zoneA;

public class OpenActivities extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    TextView openSpot, openZone, openDate, openTime;
    DatabaseActivities dba ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_activities);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.open_activities);
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

        openSpot = (TextView) findViewById(R.id.openCarSpot);
        openZone = (TextView) findViewById(R.id.openZone);
        openDate = (TextView) findViewById(R.id.openDate);
        openTime = (TextView) findViewById(R.id.startTime);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String email = sharedPref.getString("emailNewActivity", "");

        SharedPreferences sharedPrefZone = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences sharedPrefSpot = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences sharedPrefDate = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences sharedPrefTime = PreferenceManager.getDefaultSharedPreferences(this);


        dba = new DatabaseActivities(this);
        Cursor cursor = dba.getOpen(email);
        if(cursor.moveToFirst() && (cursor.getCount()!=0))
        {
            openZone.setText("Zone: " + cursor.getString(1));
            SharedPreferences.Editor editor = sharedPrefZone.edit();
            editor.putString("openZone", cursor.getString(1));
            editor.commit();

            openSpot.setText("Spot no.: " + cursor.getString(2));
            SharedPreferences.Editor editor2 = sharedPrefSpot.edit();
            editor2.putString("openSpot", cursor.getString(2));
            editor2.commit();

            openDate.setText("Date: " +cursor.getString(3));
            SharedPreferences.Editor editor3 = sharedPrefDate.edit();
            editor3.putString("openDate", cursor.getString(3));
            editor3.commit();

            openTime.setText("Time: " +cursor.getString(4));
            SharedPreferences.Editor editor4 = sharedPrefTime.edit();
            editor4.putString("openTime", cursor.getString(4));
            editor4.commit();

        }
        else
        {
            openZone.setText("Zone: ");
            openSpot.setText("Spot no.: ");
            openDate.setText("Date: ");
            openTime.setText("Time: ");
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
                Intent intent3 = new Intent(this, CompletedActivities.class);
                startActivity(intent3);
                break;
            case R.id.openActivities:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void finishOpenActivity(View v)
    {
        Intent intent = new Intent (this, Pay.class);
        startActivity(intent);
    }
}