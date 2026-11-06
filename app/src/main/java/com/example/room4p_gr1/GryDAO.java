package com.example.room4p_gr1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GryDAO {
    @Insert
    public void wstawGreDoBazy(Gra gra);

    @Insert
    public void wstawGryDoBazy(Gra ... gry);
    @Delete
    public void usunZBazy(Gra gra);
    @Update
    public void aktualizujGre(Gra gra);
    @Query("SELECT * from gry")
    public List<Gra> zwrocWszystkieGry();

    @Query("SELECT nazwa FROM gry WHERE min= :ile")
    public List<String> zwrocNazwyGierDlaLIczbyOsob(int ile);
}
