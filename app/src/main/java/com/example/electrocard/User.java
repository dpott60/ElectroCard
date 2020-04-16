package com.example.electrocard;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblUser")
public class User
{
    @PrimaryKey(autoGenerate=true)
    @NonNull
    int userID;
    String username;
    String password;
    int[] userCards;
}
