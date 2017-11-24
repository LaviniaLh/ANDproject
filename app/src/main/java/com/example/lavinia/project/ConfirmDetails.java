package com.example.lavinia.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ConfirmDetails extends AppCompatActivity {

    TextView spot, zone, time;
    DatabaseActivities db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_details);
        db = new DatabaseActivities(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle(R.string.confirm_details);
        myToolbar.setTitleTextColor(Color.BLACK);

        spot = (TextView) findViewById(R.id.confirmSpot);
        spot.setText("Spot: " + getIntent().getExtras().getInt("number"));

        zone = (TextView) findViewById(R.id.confirmZone);
        zone.setText("Zone : " +getIntent().getStringExtra("zoneA"));

        time =(TextView) findViewById(R.id.confirmTime);
        time.setText("Start Time: " + getIntent().getStringExtra("time"));

    }

    public void confirmDetails(View v) {
        int number = getIntent().getExtras().getInt("number");
        String zoneA = getIntent().getStringExtra("zoneA");
        String time = getIntent().getStringExtra("time");
        String date = getIntent().getStringExtra("date");

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String email = sharedPref.getString("emailNewActivity", "");

        boolean a = db.newBooking(email, zoneA, String.valueOf(number), date, time);
        if (a) {
            Intent intent = new Intent(this, OpenActivities.class);
            startActivity(intent);
        }
    }
}