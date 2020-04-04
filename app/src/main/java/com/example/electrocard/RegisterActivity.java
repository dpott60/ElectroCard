package com.example.electrocard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    public Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myContext = this;

        Button makeAccountBTN = findViewById(R.id.makeAccountBTN);
        makeAccountBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newUsernameET = findViewById(R.id.newUsernameET);
                EditText newPasswordET = findViewById(R.id.newPasswordET);
                String username;
                String password;
                try{
                    username = newUsernameET.getText().toString();
                    password = newPasswordET.getText().toString();
                    if (username != null && password != null) {
                        Intent ini = new Intent(myContext, MainActivity.class);
                        startActivity(ini);
                    }

                } catch (Error e) {
                    Log.d("Error", "Invalid username/password");
                }
            }
        });
    }
}
