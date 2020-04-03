package com.example.electrocard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity
{
    public Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myContext = this;

        Button registerBTN = findViewById(R.id.registerBTN);
        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ini = new Intent(myContext, RegisterActivity.class);
                startActivity(ini);
            }
        });

        Button phloginBTN = findViewById(R.id.loginBTN);
        phloginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameET = findViewById(R.id.usernameET);
                EditText passwordET = findViewById(R.id.passwordET);
                String username;
                String password;
                try{
                    username = usernameET.getText().toString();
                    password = passwordET.getText().toString();
                    //if account verified
                    Intent ini = new Intent(myContext, MainActivity.class);
                    startActivity(ini);
                } catch (Error e) {
                    Log.d("Error", "Invalid username/password");
                }
            }
        });
    }
}
