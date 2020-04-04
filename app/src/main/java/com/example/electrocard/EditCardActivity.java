package com.example.electrocard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        Intent ini = getIntent();
        TextView nameTV = findViewById(R.id.nameTV);
        TextView numberTV = findViewById(R.id.numberTV);
        TextView emailTV = findViewById(R.id.emailTV);

        nameTV.setText(ini.getStringExtra(NAME_KEY));
        numberTV.setText(ini.getStringExtra(PHONE_KEY));
        emailTV.setText(ini.getStringExtra(EMAIL_KEY));


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
