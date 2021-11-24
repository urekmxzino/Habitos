package com.aravena.certamenhabitosnicolasaravena.sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.aravena.certamenhabitosnicolasaravena.models.Categoria;

import java.util.ArrayList;

public class DbCategoria extends DbHelper {
    Context context;

    public DbCategoria(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public long insertarCategoria(Categoria categoria) {
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        long res = 0;

        ContentValues valores = new ContentValues();
        valores.put("nombre", categoria.getNombre());


        res = db.insert(DbHelper.TABLE_CATEGORIA, null, valores);

        return res;
    }

    public ArrayList<Categoria> getCategorias() {
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ArrayList<Categoria> categorias = new ArrayList<>();
        Cursor cursor = null;
        Categoria categoria = null;
        cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_CATEGORIA, null);
        if (cursor.moveToFirst()) {
            do {
                categoria = new Categoria(
                        cursor.getInt(0),
                        cursor.getString(1)
                );
                categorias.add(categoria);
            } while (cursor.moveToNext());
        }
        return categorias;

    }

    public Categoria getCategoria(int id) {
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        Categoria categoriaObtenida;
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_CATEGORIA + " WHERE id = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {

            categoriaObtenida = new Categoria(
                    cursor.getInt(0),
                    cursor.getString(1)

            );
            return categoriaObtenida;
        } else {
            return null;
        }




    }
    public int eliminarCategoria ( int id){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        int res = db.delete(TABLE_CATEGORIA, "id = ?", new String[]{String.valueOf(id)});
        return res;
    }


    public int actualizarEditorial (Categoria categoria){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();


        ContentValues valores = new ContentValues();
        valores.put("nombre", categoria.getNombre());


        int resultado = db.update(TABLE_CATEGORIA, valores,
                "id = ?", new String[]{String.valueOf(categoria.getId())});

        return resultado;

    }
}