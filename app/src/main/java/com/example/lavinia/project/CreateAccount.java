package com.example.lavinia.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.inset;
import static android.R.attr.password;
import static com.example.lavinia.project.R.id.confirmPassword;

public class CreateAccount extends AppCompatActivity {

    DatabaseHelper database;
    EditText firstname, lastname, email, regplate;
    EditText password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        database = new DatabaseHelper(this);
    }

    public void createAccount2(View v)
    {
        password = (EditText) findViewById(R.id.editPassword);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        firstname = (EditText) findViewById(R.id.editFirstName);
        lastname = (EditText) findViewById(R.id.editLastName);
        email = (EditText) findViewById(R.id.editEmail);
        regplate = (EditText) findViewById(R.id.editRegPlate);

                if(!(firstname.getText().toString().equals("")||
                        lastname.getText().toString().equals("")||
                        email.getText().toString().equals("")||
                        password.getText().toString().equals("")||
                        regplate.getText().toString().equals("")))

                    if(password.getText().toString().equals(confirmPassword.getText().toString())) {
                        boolean a = database.insertData(firstname.getText().toString(),
                                lastname.getText().toString(), email.getText().toString(),
                                password.getText().toString(), regplate.getText().toString());
                        if (a) {
                            Intent intent = new Intent(CreateAccount.this, NewActivity.class);
                            startActivity(intent);
                            Toast.makeText(CreateAccount.this, "Account created", Toast.LENGTH_LONG).show();

                            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("emailLoginOrCreate", email.getText().toString());
                            editor.commit();
                        }
                    }
                    else
                        {
                            Toast.makeText(CreateAccount.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                        }
                else
                {
                    Toast.makeText(CreateAccount.this, "Complete all fields.", Toast.LENGTH_SHORT).show();
                }
            }
}