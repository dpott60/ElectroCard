package com.example.electrocard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewSavedCardsActivity extends AppCompatActivity
{
    private Context myContext;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_saved_cards);
        myContext = this;

        Button addCardBTN = findViewById(R.id.addCardBTN);

        addCardBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent ini = new Intent(myContext, AddCardActivity.class);
                startActivity(ini);
            }
        });
    }
}
