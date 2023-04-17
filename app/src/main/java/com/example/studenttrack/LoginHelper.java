package com.example.studenttrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Login.db";

    public LoginHelper(Context context) {
        super(context, "Login.db" ,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("Create table users(name TEXT ,username TEXT primary key ,password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {

        MyDB.execSQL("Drop Table if exists users");

    }

    public boolean insertData(String name ,String username , String password) {
        SQLiteDatabase MyDB = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Username", username);
        contentValues.put("Password", password);
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean checkUser(String username){
        SQLiteDatabase myDB = getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from users where username = ?",new String[] {username});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }

    }

    public Boolean checkuserpass(String username , String password){

        SQLiteDatabase myDB = getWritableDatabase();
        Cursor cursor= myDB.rawQuery("Select * from users where username = ? and password = ?",new String[] {username,password});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }


    public int openUser(){

        SQLiteDatabase myDB = getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT COUNT(*) FROM users;", null);
        cursor.moveToFirst();
        Integer count = cursor.getInt(0);
        cursor.close();
        myDB.close();

        return count;
    }




}
