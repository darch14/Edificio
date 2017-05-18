package com.example.david.edificio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Lista_Apartamentos extends AppCompatActivity {
    private ListView ls;
    private ArrayList<Apartamento> apartamentos;
    private AdaptadorApartamentos adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__apartamentos);

        ls=(ListView)findViewById(R.id.lsApartamentos);
        apartamentos=Datos.traerApartamentos(getApplicationContext());
        adapter=new AdaptadorApartamentos(getApplicationContext(),apartamentos);
        ls.setAdapter(adapter);
    }
}
