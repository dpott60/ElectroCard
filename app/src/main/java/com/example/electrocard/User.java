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
    String firstName;
    String lastName;

    public User(String username, String password, String firstName, String lastName)
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
