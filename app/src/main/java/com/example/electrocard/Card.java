package com.example.electrocard;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblCard")
public class Card
{
    @PrimaryKey
    @NonNull
    int cardID;

    int userCreatorID;
    String firstName;
    String lastName;
    String phoneNumber;
    String emailAddress;
    int backgroundID;

    public Card(int cardID, int userCreatorID, String firstName, String lastName, String phoneNumber, String emailAddress, int backgroundID)
    {
        this.cardID = cardID;
        this.userCreatorID = userCreatorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.backgroundID = backgroundID;
    }
}
