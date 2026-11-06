package com.example.room4p_gr1;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "gry")
public class Gra {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nazwa;
    @ColumnInfo(name = "min")
    public int minLiczbaOsob;
    public int maksLiczbaOsob;
    public int kategoriaWiekowa;
    public String kategoria;

    public Gra() {
    }

    public Gra(String nazwa, int minLiczbaOsob, int maksLiczbaOsob, int kategoriaWiekowa, String kategoria) {
        this.nazwa = nazwa;
        this.minLiczbaOsob = minLiczbaOsob;
        this.maksLiczbaOsob = maksLiczbaOsob;
        this.kategoriaWiekowa = kategoriaWiekowa;
        this.kategoria = kategoria;
    }

    @Override
    public String toString() {
        return "Gra{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", minLiczbaOsob=" + minLiczbaOsob +
                ", maksLiczbaOsob=" + maksLiczbaOsob +
                ", kategoriaWiekowa=" + kategoriaWiekowa +
                ", kategoria='" + kategoria + '\'' +
                '}';
    }


}
