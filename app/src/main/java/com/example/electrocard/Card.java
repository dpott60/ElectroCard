package com.example.electrocard;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblCard")
public class Card
{
    @PrimaryKey(autoGenerate=true)
    @NonNull
    int cardID;
    int userCreatorID;
    String firstName;
    String lastName;
    int phoneNumber;
    String emailAddress;
    Color color;
}
