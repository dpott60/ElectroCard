package com.example.electrocard;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Card.class, User.class}, version=1, exportSchema = false)
public abstract class ElectroDatabase extends RoomDatabase
{
    public abstract ElectroDao electroDao();
}