package com.example.electrocard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditCardActivity extends AppCompatActivity
{
    public static final String NAME_KEY = "name";
    public static final String PHONE_KEY = "number";
    public static final String EMAIL_KEY = "email";
    private Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);
        myContext = this;

        Button cancelEditBTN = findViewById(R.id.cancelEditBTN);
        cancelEditBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent ini = new Intent(myContext, ViewCardsActivity.class);
                startActivity(ini);
            }
        });
    }
}
