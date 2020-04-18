package com.example.electrocard;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblSavedCard")
public class SavedCards {
    @PrimaryKey
    @NonNull
    int userID;
    int savedCardID;

    public SavedCards(int userID, int savedCardID){
        this.userID = userID;
        this.savedCardID = savedCardID;
    }
}
