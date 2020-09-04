package com.example.task.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities={Basket.class},version = 1)
public abstract class DatabaseHelper extends RoomDatabase {
    public abstract BasketDao cartDao();
}
