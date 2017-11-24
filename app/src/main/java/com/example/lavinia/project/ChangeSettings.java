package com.example.lavinia.project;

import android.accounts.Account;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.R.attr.password;
import static com.example.lavinia.project.R.id.confirmPassword;

public class ChangeSettings extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener
{
    DatabaseHelper db;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    TextView emailChange;
    EditText first, last, pass,confpass,regplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_settings);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.change_settings);
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

        db = new DatabaseHelper(this);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String emailNA = sharedPref.getString("emailNewActivity", "");

        emailChange = (TextView) findViewById(R.id.changeEmail);
        emailChange.setText(emailNA);

        first = (EditText) findViewById(R.id.editChangeFirstName);
        last = (EditText) findViewById(R.id.editChangeLastName);
        pass = (EditText) findViewById(R.id.editChangePassword);
        confpass = (EditText) findViewById(R.id.editChangePasswordConfirm);
        regplate = (EditText) findViewById(R.id.editChangeRegPlate);

        Cursor cursor = db.getData(emailNA);
        if(cursor.moveToFirst())
        {
            String firstName = cursor.getString(0);
            String lastName = cursor.getString(1);
            String password = cursor.getString(2);
            String plate = cursor.getString(3);

            first.setText(firstName);
            last.setText(lastName);
            pass.setText(password);
            confpass.setText(password);
            regplate.setText(plate);

        }
        cursor.close();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.changeSettings:
                break;
            case R.id.newActivity:
                Intent intent = new Intent (this, NewActivity.class);
                startActivity(intent);
                break;
            case R.id.completedActivities:
                Intent intent2 = new Intent (this, CompletedActivities.class);
                startActivity(intent2);
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

    public void saveChangedSettings (View v)
    {
        String getemail = emailChange.getText().toString();

        if(!(first.getText().toString().equals("")||
                last.getText().toString().equals("")||
                pass.getText().toString().equals("")||
                confpass.getText().toString().equals("")||
                regplate.getText().toString().equals("")))

            if(pass.getText().toString().equals(confpass.getText().toString())) {
                boolean a = db.updateData(first.getText().toString(),
                        last.getText().toString(), getemail,
                        pass.getText().toString(), regplate.getText().toString());
                if (a) {
                    Toast.makeText(ChangeSettings.this, "Settings changed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangeSettings.this, NewActivity.class);
                    startActivity(intent);
                }
            }
            else
            {
                Toast.makeText(ChangeSettings.this, "Password do not match.", Toast.LENGTH_SHORT).show();
            }
        else
        {
            Toast.makeText(ChangeSettings.this, "Complete all fields.", Toast.LENGTH_SHORT).show();
        }
    }
}