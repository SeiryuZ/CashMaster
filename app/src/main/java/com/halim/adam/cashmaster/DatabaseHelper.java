package com.halim.adam.cashmaster;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Debug;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cash_master.db";
    private static final int DATABASE_VERSION = 3;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE category ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE );";
        db.execSQL(sql);
        sql = "CREATE TABLE income ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, price NUMERIC, date TEXT DEFAULT (date('now')));";
        db.execSQL(sql);
        sql = "CREATE TABLE jar ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, portion INTEGER );";
        db.execSQL(sql);
        sql = "CREATE TABLE spending ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, date TEXT DEFAULT (date('now')), price NUMERIC, categoryId INTEGER, jarId INTEGER, FOREIGN KEY(categoryId) REFERENCES category(id) ON DELETE SET NULL );";
        db.execSQL(sql);
        sql = "INSERT INTO income (name, price) VALUES ('Weekly', '300000')";
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
