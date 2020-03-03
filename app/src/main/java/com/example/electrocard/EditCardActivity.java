package com.example.electrocard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EditCardActivity extends AppCompatActivity
{
    public static final String NAME_KEY = "name";
    public static final String PHONE_KEY = "number";
    public static final String EMAIL_KEY = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);
    }
}
