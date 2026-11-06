package com.example.room4p_gr1;

import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button buttonDodaj, buttonAktualizacja;
    EditText editTextNAzwa, editTextMin, editTextMax, editTExtWiek;
    Spinner spinnerKategoria;
    ListView listViewGry;
    List<Gra> gryList;
    ArrayAdapter<Gra> arrayAdapter;
    Gra kliknietagra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        GryDataBase gryDataBase = Room.databaseBuilder(
                getApplicationContext(),
                GryDataBase.class,
                "gry_db"
        ).
                allowMainThreadQueries()
                .build();
        /*gryDataBase.zwrocGryDao().wstawGryDoBazy(
                new Gra("Uno",2,12,6,"imprezowa")
        );

         */

        buttonDodaj = findViewById(R.id.button);
        buttonAktualizacja = findViewById(R.id.buttonAktualizacja);
        editTextMin = findViewById(R.id.editTextNumberMin);
        editTextNAzwa = findViewById(R.id.editTextNazwa);
        editTExtWiek =findViewById(R.id.editTextNumberWiek);
        editTextMax = findViewById(R.id.editTextNumberMax);
        spinnerKategoria = findViewById(R.id.spinner);
        buttonDodaj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Gra gra = new Gra(
                                editTextNAzwa.getText().toString(),
                                Integer.parseInt(editTextMin.getText().toString()),
                                Integer.parseInt(editTextMax.getText().toString()),
                                Integer.parseInt(editTExtWiek.getText().toString()),
                                spinnerKategoria.getSelectedItem().toString()
                        );
                        gryDataBase.zwrocGryDao().wstawGreDoBazy(gra);
                    }
                }
        );
        listViewGry = findViewById(R.id.listViewGry);
        gryList = gryDataBase.zwrocGryDao().zwrocWszystkieGry();
        arrayAdapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                gryList
        );
        listViewGry.setAdapter(arrayAdapter);
        listViewGry.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        gryDataBase.zwrocGryDao().usunZBazy(gryList.get(i));
                        gryList.remove(i);
                        arrayAdapter.notifyDataSetChanged();
                        return false;
                    }
                }

        );
        listViewGry.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        kliknietagra = gryList.get(i);
                        editTextNAzwa.setText(kliknietagra.nazwa);
                        editTextMin.setText(""+kliknietagra.minLiczbaOsob);
                        editTextMax.setText(""+kliknietagra.maksLiczbaOsob);
                        editTExtWiek.setText(""+kliknietagra.kategoriaWiekowa);
                       //TODO: spinner
                        buttonAktualizacja.setVisibility(VISIBLE);
                    }
                }
        );
        buttonAktualizacja.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        kliknietagra.nazwa = editTextNAzwa.getText().toString();
                        //TODO: pozostałe pola formularza
                        gryDataBase.zwrocGryDao().aktualizujGre(kliknietagra);
                    }
                }
        );

    }
}