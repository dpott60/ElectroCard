package com.example.electrocard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                //if account is verified
                Intent ini = new Intent(myContext, MainActivity.class);
                startActivity(ini);
            }
        });
    }
}
