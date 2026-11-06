package com.example.room4p_gr1;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Gra.class},version = 1)
public abstract class GryDataBase extends RoomDatabase {
    public abstract GryDAO zwrocGryDao();
}
