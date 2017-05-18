package com.example.david.edificio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by david on 17/05/2017.
 */

public class Datos {
    public static ArrayList<Apartamento> traerApartamentos(Context contexto){
        SQLiteDatabase db;
        String sql,foto,nomenclatura,piso,metros,precio,balcon,sombra;
        ArrayList<Apartamento> apartamentos=new ArrayList<>();

        ApartamentosSQLiteOpenHelper aux=new ApartamentosSQLiteOpenHelper(contexto,"DBApartamentos",null,1);
        db=aux.getReadableDatabase();

        sql="Select foto,nomenclatura,piso,metros,precio,balcon,sombra from Apartamentos";
        Cursor c=db.rawQuery(sql,null);

        if (c.moveToFirst()){
            do {
                foto=c.getString(0);
                nomenclatura=c.getString(1);
                piso=c.getString(2);
                metros=c.getString(3);
                precio=c.getString(4);
                balcon=c.getString(5);
                sombra=c.getString(6);
                Apartamento a=new Apartamento(foto,nomenclatura,piso,metros,precio,balcon,sombra);
                apartamentos.add(a);

            }while (c.moveToNext());
        }

        db.close();
        return apartamentos;
    }

    public static Apartamento buscarApartamentos(Context contexto,String nom){
        SQLiteDatabase db;
        String sql,foto,nomenclatura,piso,metros,precio,balcon,sombra;
        Apartamento a=null;

        ApartamentosSQLiteOpenHelper aux=new ApartamentosSQLiteOpenHelper(contexto,"DBApartamentos",null,1);
        db=aux.getReadableDatabase();

        sql="Select * from Apartamentos where nomenclatura ='"+nom+"'";
        Cursor c=db.rawQuery(sql,null);

        if (c.moveToFirst()){
            foto=c.getString(0);
            nomenclatura=c.getString(1);
            piso=c.getString(2);
            metros=c.getString(3);
            precio=c.getString(4);
            balcon=c.getString(5);
            sombra=c.getString(6);
            a=new Apartamento(foto,nomenclatura,piso,metros,precio,balcon,sombra);

        }

        db.close();
        return a;
    }

}
