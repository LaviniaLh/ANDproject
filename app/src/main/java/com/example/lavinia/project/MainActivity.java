package com.example.lavinia.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createAccount(View v)
    {
        Intent intent = new Intent (this, CreateAccount.class);
        startActivity(intent);
    }

    public void login(View v)
    {
        Intent intent2 = new Intent (this, Login.class);
        startActivity(intent2);
    }
}