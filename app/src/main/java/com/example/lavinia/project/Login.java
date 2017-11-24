package com.example.lavinia.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.password;

public class Login extends AppCompatActivity {

    DatabaseHelper db;
    EditText email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
    }

    public void login2(View v)
    {
        email = (EditText) findViewById(R.id.emailLogin);
        pass = (EditText) findViewById(R.id.passLogin);

        if(!(email.getText().toString().equals("")||
                pass.getText().toString().equals("")))
        {
            boolean a = db.findData(email.getText().toString(), pass.getText().toString());
            if (a)
            {
                Intent intent = new Intent (Login.this, NewActivity.class);
                startActivity(intent);

                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("emailLoginOrCreate", email.getText().toString());
                editor.commit();
            }
            else
            {
                Toast.makeText(this, "Email or password incorrect", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this, "Complete all fields", Toast.LENGTH_LONG).show();
        }
    }
}