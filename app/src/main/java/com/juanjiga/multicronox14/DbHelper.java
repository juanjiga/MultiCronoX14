package com.juanjiga.multicronox14;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NOMBRE_BD = "basedatosM.db";
    private static final String TABLA1 ="CREATE TABLE tiempos" +
            "(id INT PRIMARY KEY, nombre TEXT, numero INT, segundosTot INT)";

    public DbHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA1);
        onCreate(db);
    }
    public void insertar(int id, int segundosTot) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("id", id);
            valores.put("segundosTot", segundosTot);
            db.insert("tiempos", null, valores);
            db.close();
        }
    }
    public void modificar(int id, int segundosTot){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("id", id);
        valores.put("segundosTot", segundosTot);
        db.update("tiempos", valores, "id=" + id, null);
        db.close();
    }
    public void borrar(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("tiempos", "id="+id, null);
        db.close();
    }

}




