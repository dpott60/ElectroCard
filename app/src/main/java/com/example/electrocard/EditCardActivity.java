package com.example.electrocard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class EditCardActivity extends AppCompatActivity
{
    public static final String NAME_KEY = "name";
    public static final String PHONE_KEY = "number";
    public static final String EMAIL_KEY = "email";
    public static final String BG_KEY = "background";
    private Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);
        myContext = this;

        Intent ini = getIntent();
        TextView nameET = findViewById(R.id.nameET);
        TextView numberET = findViewById(R.id.numberET);
        TextView emailET = findViewById(R.id.emailET);
        LinearLayout lin = findViewById(R.id.editcardLIN);

        lin.setBackgroundResource(ini.getIntExtra(BG_KEY, R.drawable.back));
        nameET.setText(ini.getStringExtra(NAME_KEY));
        numberET.setText(ini.getStringExtra(PHONE_KEY));
        emailET.setText(ini.getStringExtra(EMAIL_KEY));


        Button cancelEditBTN = findViewById(R.id.cancelEditBTN);
        cancelEditBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent ini = new Intent(myContext, ViewCardsActivity.class);
                startActivity(ini);
            }
        });

        Spinner colorSPN = (Spinner) findViewById(R.id.colorSPN);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(EditCardActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.colors));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSPN.setAdapter(spinnerAdapter);
    }
}
