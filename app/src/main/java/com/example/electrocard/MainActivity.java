package com.example.electrocard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private static ElectroDatabase db;

    public Context myContext;
    //testing to see if github works also
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myContext = this;

        db = Room.databaseBuilder(getApplicationContext(), ElectroDatabase.class, "electro_db").build();

        Button savedCardsBTN = findViewById(R.id.savedcardsBTN);

        savedCardsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent ini = new Intent(myContext, ViewSavedCardsActivity.class);
                startActivity(ini);
            }
        });
        Button viewCardsBTN = findViewById(R.id.viewcardsBTN);

        viewCardsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent ini = new Intent(myContext, ViewCardsActivity.class);
                startActivity(ini);
            }
        });
    }
    public static ElectroDatabase getDB()
    {
        return db;
    }
}
