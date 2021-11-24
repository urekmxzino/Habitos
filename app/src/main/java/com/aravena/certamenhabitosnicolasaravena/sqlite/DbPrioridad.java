package com.aravena.certamenhabitosnicolasaravena.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.aravena.certamenhabitosnicolasaravena.models.Prioridad;

import java.util.ArrayList;

public class DbPrioridad extends DbHelper {
    Context context;

    public DbPrioridad(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public long insertarPrioridad(Prioridad prioridad) {
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        long res = 0;

        ContentValues valores = new ContentValues();
        valores.put("nombre", prioridad.getNombre());


        res = db.insert(DbHelper.TABLE_PRIORIDAD, null, valores);

        return res;
    }

    public ArrayList<Prioridad> getPrioridades() {
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ArrayList<Prioridad> prioridades = new ArrayList<>();
        Cursor cursor = null;
        Prioridad prioridad = null;
        cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_PRIORIDAD, null);
        if (cursor.moveToFirst()) {
            do {
                prioridad = new Prioridad(
                        cursor.getInt(0),
                        cursor.getString(1)
                );
                prioridades.add(prioridad);
            } while (cursor.moveToNext());
        }
        return prioridades;

    }

    public Prioridad getPrioridad(int id) {
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        Prioridad prioridadObtenida;
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_PRIORIDAD + " WHERE id = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {

            prioridadObtenida = new Prioridad(
                    cursor.getInt(0),
                    cursor.getString(1)

            );
            return prioridadObtenida;
        } else {
            return null;
        }




    }
    public int eliminarPrioridad ( int id){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        int res = db.delete(TABLE_PRIORIDAD, "id = ?", new String[]{String.valueOf(id)});
        return res;
    }


    public int actualizarPrioridad (Prioridad prioridad){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();


        ContentValues valores = new ContentValues();
        valores.put("nombre", prioridad.getNombre());


        int resultado = db.update(TABLE_PRIORIDAD, valores,
                "id = ?", new String[]{String.valueOf(prioridad.getId())});

        return resultado;

    }
}