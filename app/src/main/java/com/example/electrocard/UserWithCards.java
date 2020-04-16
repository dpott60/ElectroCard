package com.example.electrocard;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithCards
{
    @Embedded
    public User user;
    @Relation(
            parentColumn = "userId",
            entityColumn = "userCreatorID"
    )
    public List<Card> cards;
}
