package com.example.lavinia.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static com.example.lavinia.project.R.id.price;

public class Pay extends AppCompatActivity {

    TextView timeO, timeF, timeT, pay;
    DatabaseActivities dba ;
    String email, zone, spot, finishTime, startTime, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.pay);
        myToolbar.setTitleTextColor(Color.BLACK);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        finishTime = timeFormat.format(c.getTime());

        timeO = (TextView) findViewById(R.id.paymentStartTime);
        SharedPreferences sharedTime = PreferenceManager.getDefaultSharedPreferences(this);
        startTime = sharedTime.getString("openTime", "");
        timeO.setText("Start time: "+ startTime);

        timeF =(TextView) findViewById(R.id.paymentFinishTime);
        timeF.setText("Finish time: " + finishTime);

        try {
            Date startDate = timeFormat.parse(startTime);
            Date finishDate = timeFormat.parse(finishTime);
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

            timeT = (TextView) findViewById(R.id.amountOfTime);
            timeT.setText("Total amount of time: " + hours + " hour(s)" + ", " + min
                    + " minute(s).");

            long totalPrice = hours*60 + min;
            pay = (TextView) findViewById(price);
            pay.setText("Price: " + totalPrice + " kr.");
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        email = sharedPref.getString("emailNewActivity", "");
        SharedPreferences sharedPref1 = PreferenceManager.getDefaultSharedPreferences(this);
        zone = sharedPref1.getString("openZone", "");
        SharedPreferences sharedPref2 = PreferenceManager.getDefaultSharedPreferences(this);
        spot = sharedPref2.getString("openSpot", "");
        SharedPreferences sharedPref3 = PreferenceManager.getDefaultSharedPreferences(this);
        date = sharedPref3.getString("openDate", "");
        dba = new DatabaseActivities(this);
    }

    public void pay(View v)
    {
        PackageManager pm = this.getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage("dk.danskebank.mobilepay");
        Cursor cursor = dba.getAllClosed(email);
        if(cursor.getCount()==5) {
            if (cursor.moveToPosition(0))
                    {
                        String emailPay = cursor.getString(0);
                        String zonePay = cursor.getString(1);
                        String spotPay = cursor.getString(2);
                        String datePay = cursor.getString(3);
                        String startPay = cursor.getString(4);
                        dba.deleteOneClosed(emailPay, zonePay, spotPay, datePay, startPay);
                    }
        }
        cursor.close();

        boolean a = dba.closeBooking(email,zone, spot, date, startTime, finishTime );
        if(a)
        {
            Toast.makeText(this, "Activity completed, check Completed activities.", Toast.LENGTH_LONG).show();
        }

        if (intent != null) {
            this.startActivity(intent);
        }
    }
}