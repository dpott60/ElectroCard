package com.example.electrocard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class LoginActivity extends AppCompatActivity
{
    private static ElectroDatabase db;
    public Context myContext;
    public String loggedInUsername;
    public int loggedInUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myContext = this;
        db = Room.databaseBuilder(getApplicationContext(), ElectroDatabase.class, "electro_db").build();

        threadLoadUsers();

        Button registerBTN = findViewById(R.id.registerBTN);
        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ini = new Intent(myContext, RegisterActivity.class);
                startActivity(ini);
            }
        });

        Button loginBTN = findViewById(R.id.loginBTN);
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameET = findViewById(R.id.usernameET);
                EditText passwordET = findViewById(R.id.passwordET);
                TextView statusTV = findViewById(R.id.statusTV);
                String username = usernameET.getText().toString();
                String password = passwordET.getText().toString();

                threadCheckUser(statusTV, username, password);
            }
        });
    }
    public static ElectroDatabase getDB()
    {
        return db;
    }

    private void threadCheckUser(final TextView statusTV, final String username, final String password)
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                List<User> userList = db.electroDao().getLoginUsers(username);

                if (userList.isEmpty())
                {
                    Log.d("LoginCheck", "An account with that username does not exist.");
                    statusTV.post(new Runnable() {
                        @Override
                        public void run() {
                            statusTV.setText("An account with that username does not exist.");
                        }
                    });
                }
                else
                {
                    for (User user : userList)
                    {
                        Log.d("LoginCheck", user.username + " " + user.password);
                        if (password.equals(user.password))
                        {
                            loggedInUsername = user.username;
                            loggedInUserID = user.userID;

                            Intent ini = new Intent(myContext, MainActivity.class);
                            startActivity(ini);
                        }
                        else
                        {
                            Log.d("LoginCheck", "Can't find user or pass");
                            statusTV.post(new Runnable() {
                                @Override
                                public void run() {
                                    statusTV.setText("Invalid Username/Password");
                                }
                            });
                        }
                    }
                }
            }
        }).start();
    }

    private void threadLoadUsers()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {

                if (db.electroDao().getAllUsers().isEmpty())
                {
                    db.electroDao().insertUser(new User(0001, "testuser", "pass123"));
                    db.electroDao().insertUser(new User(0002, "admin", "pass"));
                }
            }
        }).start();
        //cardList.add(new CardInfo("Jenny", "", "867-5309", "jenny@email.com", 1234, R.drawable.blueback));
    }

    public String getLoggedInUsername()
    {
        return loggedInUsername;
    }
    public int getLoggedInUserID()
    {
        return loggedInUserID;
    }
}
