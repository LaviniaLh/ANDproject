package com.example.lavinia.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ActionBarDrawerToggle actionBarDrawerToggle;
    Intent intent;
    DrawerLayout drawerLayout;
    DatabaseActivities dba;

    Button button1A, button2A, button3A, button4A, button5A, button6A, button7A, button8A,
            button9A, button10A, button11A, button12A, button13A, button14A, button15A,
            button16A, button17A, button18A, button19A, button20A, button21A, button22A,
            button23A, button24A, button25A, button1B, button2B, button3B, button4B, button5B,
            button6B, button7B, button8B, button9B, button10B, button11B, button12B, button13B,
            button14B, button15B, button16B, button17B, button18B, button19B, button20B,
            button21B, button22B, button23B, button24B, button25B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.new_activity);
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

        button1A=(Button)findViewById(R.id.button1A);
        button2A=(Button)findViewById(R.id.button2A);
        button3A=(Button)findViewById(R.id.button3A);
        button4A=(Button)findViewById(R.id.button4A);
        button5A=(Button)findViewById(R.id.button5A);
        button6A=(Button)findViewById(R.id.button6A);
        button7A=(Button)findViewById(R.id.button7A);
        button8A=(Button)findViewById(R.id.button8A);
        button9A=(Button)findViewById(R.id.button9A);
        button10A=(Button)findViewById(R.id.button10A);
        button11A=(Button)findViewById(R.id.button11A);
        button12A=(Button)findViewById(R.id.button12A);
        button13A=(Button)findViewById(R.id.button13A);
        button14A=(Button)findViewById(R.id.button14A);
        button15A=(Button)findViewById(R.id.button15A);
        button16A=(Button)findViewById(R.id.button16A);
        button17A=(Button)findViewById(R.id.button17A);
        button18A=(Button)findViewById(R.id.button18A);
        button19A=(Button)findViewById(R.id.button19A);
        button20A=(Button)findViewById(R.id.button20A);
        button21A=(Button)findViewById(R.id.button21A);
        button22A=(Button)findViewById(R.id.button22A);
        button23A=(Button)findViewById(R.id.button23A);
        button24A=(Button)findViewById(R.id.button24A);
        button25A=(Button)findViewById(R.id.button25A);

        button1B=(Button)findViewById(R.id.button1B);
        button2B=(Button)findViewById(R.id.button2B);
        button3B=(Button)findViewById(R.id.button3B);
        button4B=(Button)findViewById(R.id.button4B);
        button5B=(Button)findViewById(R.id.button5B);
        button6B=(Button)findViewById(R.id.button6B);
        button7B=(Button)findViewById(R.id.button7B);
        button8B=(Button)findViewById(R.id.button8B);
        button9B=(Button)findViewById(R.id.button9B);
        button10B=(Button)findViewById(R.id.button10B);
        button11B=(Button)findViewById(R.id.button11B);
        button12B=(Button)findViewById(R.id.button12B);
        button13B=(Button)findViewById(R.id.button13B);
        button14B=(Button)findViewById(R.id.button14B);
        button15B=(Button)findViewById(R.id.button15B);
        button16B=(Button)findViewById(R.id.button16B);
        button17B=(Button)findViewById(R.id.button17B);
        button18B=(Button)findViewById(R.id.button18B);
        button19B=(Button)findViewById(R.id.button19B);
        button20B=(Button)findViewById(R.id.button20B);
        button21B=(Button)findViewById(R.id.button21B);
        button22B=(Button)findViewById(R.id.button22B);
        button23B=(Button)findViewById(R.id.button23B);
        button24B=(Button)findViewById(R.id.button24B);
        button25B=(Button)findViewById(R.id.button25B);

        intent = new Intent(NewActivity.this, ConfirmDetails.class);
        final Calendar c = Calendar.getInstance();
        final SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        final SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String emailLC = sharedPref.getString("emailLoginOrCreate", "");
        SharedPreferences sharedPreff = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreff.edit();
        editor.putString("emailNewActivity", emailLC);
        editor.commit();

        dba = new DatabaseActivities(this);

        Cursor cursor = dba.getOpen(emailLC);

        if(cursor.getCount()!=0)
        {
            Intent intent2 = new Intent(NewActivity.this, OpenActivities.class);
            startActivity(intent2);
        }
        cursor.close();

        button1A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 1);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button2A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 2);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button3A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 3);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button4A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {;
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 4);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button5A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 5);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button6A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("number",6);
                intent.putExtra("zoneA", "A");
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button7A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 7);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button8A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 8);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button9A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 9);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button10A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 10);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button11A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("number",11);
                intent.putExtra("zoneA", "A");
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button12A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 12);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button13A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 13);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button14A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 14);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button15A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 15);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button16A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 16);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button17A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 17);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button18A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 18);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button19A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 19);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button20A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 20);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button21A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 21);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button22A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 22);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button23A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 23);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button24A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 24);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button25A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "A");
                intent.putExtra("number", 25);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });

        button1B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "B");
                intent.putExtra("number", 1);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "B");
                intent.putExtra("number", 2);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "B");
                intent.putExtra("number", 3);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button4B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "B");
                intent.putExtra("number", 4);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button5B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "B");
                intent.putExtra("number", 5);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button6B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "B");
                intent.putExtra("number", 6);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button7B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "B");
                intent.putExtra("number", 7);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button8B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "B");
                intent.putExtra("number", 8);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button9B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("zoneA", "B");
                intent.putExtra("number", 9);
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button10B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("number",10);
                intent.putExtra("zoneA", "B");
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button11B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("number",11);
                intent.putExtra("zoneA", "B");
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button12B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("number",12);
                intent.putExtra("zoneA", "B");
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button13B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("number",13);
                intent.putExtra("zoneA", "B");
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button14B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("number",14);
                intent.putExtra("zoneA", "B");
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button15B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("number",15);
                intent.putExtra("zoneA", "B");
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button16B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("number",16);
                intent.putExtra("zoneA", "B");
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button17B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("number",17);
                intent.putExtra("zoneA", "B");
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button18B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("number",18);
                intent.putExtra("zoneA", "B");
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button19B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("number",19);
                intent.putExtra("zoneA", "B");
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button20B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("number",20);
                intent.putExtra("zoneA", "B");
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button21B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("number",21);
                intent.putExtra("zoneA", "B");
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });

        button22B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("number",22);
                intent.putExtra("zoneA", "B");
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button23B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("number",23);
                intent.putExtra("zoneA", "B");
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button24B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("number",24);
                intent.putExtra("zoneA", "B");
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
        button25B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("number",25);
                intent.putExtra("zoneA", "B");
                String datestart = date.format(c.getTime());
                String timestart = time.format(c.getTime());
                intent.putExtra("date",datestart);
                intent.putExtra("time",timestart);
                startActivity(intent);
            }
        });
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.changeSettings:
                Intent intent = new Intent (this, ChangeSettings.class);
                startActivity(intent);
                break;
            case R.id.newActivity:
                break;
            case R.id.completedActivities:
                Intent intent3 = new Intent (this, CompletedActivities.class);
                startActivity(intent3);
                break;
            case R.id.openActivities:
                Intent intent4 = new Intent(this, OpenActivities.class);
                startActivity(intent4);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}