package com.halim.adam.cashmaster;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Debug;
import android.util.Log;

import java.util.Date;

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /*
    INSERT TO TABLES ---------------------------------------------------------------------------------------------
     */

    /**
     * Insert to table 'category'
     * @param name name of the category
     */
    public void InsertCategory(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO category (name) VALUES ('" + name + "');";
        db.execSQL(sql);
        db.close();
    }

    public void InsertIncome(String name, float price, Date date){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO income (name, price, date) VALUES ('" + name + "', '" + price + "', '" + date +  "');";
        db.close();
    }

    public void InsertIncome(String name, float price){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO income (name, price) VALUES ('" + name + "', '" + price + "');";
        db.execSQL(sql);
        db.close();
    }

    public void InsertJar(String name, int portion){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO jar (name, portion) VALUES ('" + name + "', '" + portion + "');";
        db.execSQL(sql);
        db.close();
    }

    public void InsertSpending(String name, float price, int categoryId, int jarId, Date date){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO spending (name, date, price, categoryId, jarId) VALUES ('" + name + "', '" + date + "', '" + price + "', '" + categoryId + "', '" + jarId +  "');";
        db.execSQL(sql);
        db.close();
    }

    public void InsertSpending(String name, float price, int categoryId, int jarId){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO spending (name, price, categoryId, jarId) VALUES ('" + name + "', '" + price + "', '" + categoryId + "', '" + jarId +  "');";
        db.execSQL(sql);
        db.close();
    }

    /*
    GET FROM TABLES -------------------------------------------------------------------------------------
     */
}
