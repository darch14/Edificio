package com.example.david.edificio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by david on 17/05/2017.
 */

public class ApartamentosSQLiteOpenHelper extends SQLiteOpenHelper{
    private String sql="CREATE TABLE Apartamentos(foto text,nomenclatura text,piso text,metros text,precio text,balcon text,sombra text)";

    public ApartamentosSQLiteOpenHelper(Context contexto, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Apartamentos");
        db.execSQL(sql);
    }
}
