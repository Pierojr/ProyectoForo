package com.example.proyectologinbasico;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.Nullable;

public class DataHelper extends SQLiteOpenHelper {

    public  DataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE juegos(id INTERGER PRIMARY KEY, nombre TEXT, plataforma TEXT, genero TEXT, nota INTERGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS juegos");
        db.execSQL("CREATE TABLE juegos(id INTERGER PRIMARY KEY, nombre TEXT, plataforma TEXT, genero TEXT, nota INTERGER )");
    }
}
