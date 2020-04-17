package com.example.electrocard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class RegisterActivity extends AppCompatActivity
{
    public Context myContext;
    private ElectroDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = LoginActivity.getDB();

        myContext = this;

        Button makeAccountBTN = findViewById(R.id.makeAccountBTN);
        makeAccountBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newUsernameET = findViewById(R.id.newUsernameET);
                EditText newPasswordET = findViewById(R.id.newPasswordET);
                EditText firstNameET = findViewById(R.id.firstNameET);
                EditText lastNameET = findViewById(R.id.lastNameET);
                String username = newUsernameET.getText().toString();
                String password = newPasswordET.getText().toString();
                String firstName = firstNameET.getText().toString();
                String lastName = lastNameET.getText().toString();

                threadCreateUser(username, password, firstName, lastName);
            }
        });
    }
    private void threadCreateUser(final String username, final String password, final String firstName, final String lastName)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                User register = new User(username, password, firstName, lastName);
                List<User> checkUser = db.electroDao().findUserByUsername(username);

                if(checkUser.isEmpty())
                {
                    db.electroDao().insertUser(register);
                    Intent ini = new Intent(myContext, MainActivity.class);
                    LoginActivity.setLoggedInUserID(register.userID);
                    LoginActivity.setLoggedInUsername(username);
                    startActivity(ini);
                }
                else
                {
                    Log.d("USER ALREADY IN DB", "User already created");
                    final TextView checkTV = findViewById(R.id.checkTV);
                    checkTV.post(new Runnable() {
                        @Override
                        public void run() {
                            checkTV.setText("Username already exists!");
                        }
                    });
                }

            }
        }).start();
    }
}
