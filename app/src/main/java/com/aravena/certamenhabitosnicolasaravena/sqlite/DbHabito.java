package com.aravena.certamenhabitosnicolasaravena.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.aravena.certamenhabitosnicolasaravena.models.Categoria;
import com.aravena.certamenhabitosnicolasaravena.models.Habito;
import com.aravena.certamenhabitosnicolasaravena.models.Prioridad;

import java.util.ArrayList;

public class DbHabito  extends DbHelper{
    Context context;
    public DbHabito(@Nullable Context context){
        super(context);
        this.context = context;
    }



    public long insertarHabito(Habito habito){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();


        long res = 0;

        ContentValues valores = new ContentValues();
        valores.put("nombre",habito.getNombre());
        valores.put("descripcion",habito.getDescripcion());
        valores.put("cant",habito.getCantidad());
        valores.put("prioridad",habito.getPrioridad().getId());
        valores.put("categoria",habito.getCategoria().getId());
        valores.put("hecho",habito.getHecho());



        res = db.insert(DbHelper.TABLE_HABITOS,null,valores);


        return res;
    }

    public ArrayList<Habito> getHabitos(){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();



        ArrayList<Habito> habitos = new ArrayList<>();
        Cursor cursor = null;
        Habito habito = null;
        DbPrioridad dbP = new DbPrioridad(context);
        DbCategoria dbcat = new DbCategoria(context);

        cursor = db.rawQuery("SELECT * FROM "+DbHelper.TABLE_HABITOS,null);
        if(cursor.moveToFirst()){
            do{
                Prioridad pri = dbP.getPrioridad(cursor.getInt(5));
                Categoria cat = dbcat.getCategoria(cursor.getInt(4));


                habito = new Habito(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        pri,
                        cat,
                        cursor.getInt(6)
                );
                habitos.add(habito);
            }while(cursor.moveToNext());
        }
        return habitos;
    }

    public int eliminarHabito(int id){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        int res = db.delete(TABLE_HABITOS,"id = ?",new String[]{ String.valueOf(id) });
        return res;
    }

    public int actualizarHabito(Habito habito){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();


        ContentValues valores = new ContentValues();
        valores.put("nombre",habito.getNombre());
        valores.put("descripcion",habito.getDescripcion());
        valores.put("cant",habito.getCantidad());
        valores.put("categoria",habito.getCategoria().getId());
        valores.put("prioridad",habito.getPrioridad().getId());
        valores.put("hecho",habito.getHecho());

        int resultado = db.update(TABLE_HABITOS,valores,
                "id = ?", new String[] { String.valueOf(habito.getId()) } );

        return resultado;

    }

}
