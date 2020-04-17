package com.example.electrocard;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    public Context myContext;
    //testing to see if github works also
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myContext = this;

        Button savedCardsBTN = findViewById(R.id.savedcardsBTN);
        TextView welcomeTV = findViewById(R.id.welcomeTV);

        welcomeTV.setText("Welcome, " + LoginActivity.getLoggedInUsername() + "!");

        savedCardsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ini = new Intent(myContext, ViewSavedCardsActivity.class);
                startActivity(ini);
            }
        });
        Button viewCardsBTN = findViewById(R.id.viewcardsBTN);

        viewCardsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ini = new Intent(myContext, ViewCardsActivity.class);
                startActivity(ini);
            }
        });
    }
}
