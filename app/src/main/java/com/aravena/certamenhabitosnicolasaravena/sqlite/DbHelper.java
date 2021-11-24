package com.aravena.certamenhabitosnicolasaravena.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "HabitosDB";
    public static final int DB_VERSION = 7;
    public static final String TABLE_HABITOS = "habitos";
    public static final String TABLE_CATEGORIA = "categorias";
    public static final String TABLE_PRIORIDAD = "prioridades";
    public static final String TABLE_USERS = "usuarios";


    public DbHelper(@Nullable Context context){
        super(context,DB_NAME,null,DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_HABITOS+" (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "descripcion TEXT NOT NULL," +
                "cant TEXT NOT NULL," +
                "prioridad INTEGER NOT NULL," +
                "categoria INTEGER NOT NULL," +
                "hecho INTEGER NOT NULL," +
                "FOREIGN KEY (prioridad) REFERENCES prioridades(id)," +
                "FOREIGN KEY (categoria) REFERENCES categorias(id))");



        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PRIORIDAD + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL)");


        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CATEGORIA + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_USERS+" ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombres TEXT NOT NULL,"+
                "apellidos TEXT NOT NULL,"+
                "email TEXT NOT NULL,"+
                "clave TEXT NOT NULL)");
        sqLiteDatabase.execSQL("INSERT INTO "+TABLE_PRIORIDAD+" (nombre) VALUES ('No tan importante')");
        sqLiteDatabase.execSQL("INSERT INTO "+TABLE_PRIORIDAD+" (nombre) VALUES ('Importante')");
        sqLiteDatabase.execSQL("INSERT INTO "+TABLE_PRIORIDAD+" (nombre) VALUES ('No tan urgente')");
        sqLiteDatabase.execSQL("INSERT INTO "+TABLE_PRIORIDAD+" (nombre) VALUES ('Urgente')");

        sqLiteDatabase.execSQL("INSERT INTO "+TABLE_CATEGORIA+" (nombre) VALUES ('Salud mental')");
        sqLiteDatabase.execSQL("INSERT INTO "+TABLE_CATEGORIA+" (nombre) VALUES ('Salud fisica')");
        sqLiteDatabase.execSQL("INSERT INTO "+TABLE_CATEGORIA+" (nombre) VALUES ('Academico')");
        sqLiteDatabase.execSQL("INSERT INTO "+TABLE_CATEGORIA+" (nombre) VALUES ('Hobby')");

    }
    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase,int i, int i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HABITOS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRIORIDAD);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(sqLiteDatabase);
    }


}
