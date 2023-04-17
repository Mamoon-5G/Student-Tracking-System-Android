package com.example.studenttrack;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;


import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {


    public static final String name = "Student_InfoNew.db";
    private static final String ID_1 = "id";
    private static final String ID_2 = "id";
    private static final String ID_3 = "Id";

    public DBhelper(Context context) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create table students_details(" + ID_1 + " INTEGER primary key , NAME TEXT,DEPARTMENT TEXT,SEMESTER TEXT,DATE TEXT,Phone_Number INTEGER)");
        db.execSQL("Create table marks_details (" + ID_2 + " INTEGER primary key, MARKS1 TEXT , MARKS2 TEXT,MARKS3 TEXT,MARKS4 TEXT)");
        db.execSQL("Create table attendence_details (" + ID_3 + " INTEGER primary key, ATTENDENCE1 TEXT , ATTENDENCE2 TEXT,ATTENDENCE3 TEXT,ATTENDENCE4 TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop table if exists students_details");
        db.execSQL("Drop table if exists marks_details");
        db.execSQL("Drop Table if exists attendence_details");


    }

    //Methodd to add the Student Details to the Database

    Boolean insertStudent(String id, String name, String department, String semester, String date, String phone) {

        SQLiteDatabase myDb = getWritableDatabase();
        ContentValues student = new ContentValues();
        student.put("id", id);
        student.put("Name", name);
        student.put("Department", department);
        student.put("Semester", semester);
        student.put("Date", date);
        student.put("Phone_Number", phone);
        long result = myDb.insert("students_details", null, student);
        if (result == -1) {
            return false;
        } else {
            return true;
        }


    }

    // Method to Add Marks in Database

    Boolean insertMarks(String id, String Marks1, String Marks2, String Marks3, String Marks4) {
        SQLiteDatabase myDb = getWritableDatabase();
        ContentValues Marks = new ContentValues();
        Marks.put("id", id);
        Marks.put("MARKS1", Marks1);
        Marks.put("MARKS2", Marks2);
        Marks.put("MARKS3", Marks3);
        Marks.put("MARKS4", Marks4);

        long result = myDb.insert("marks_details", null, Marks);
        if (result == -1) {
            return false;
        } else {
            return true;
        }


    }

    Boolean insertAttendence(String id, String Attendence1, String Attendence2, String Attendence3, String Attendence4) {
        SQLiteDatabase myDb = getWritableDatabase();
        ContentValues Attendence = new ContentValues();
        Attendence.put("Id", id);
        Attendence.put("ATTENDENCE1", Attendence1);
        Attendence.put("ATTENDENCE2", Attendence2);
        Attendence.put("ATTENDENCE3", Attendence3);
        Attendence.put("ATTENDENCE4", Attendence4);

        long result = myDb.insert("attendence_details", null, Attendence);
        if (result == -1) {
            return false;
        } else {
            return true;
        }


    }


    public Boolean CheckDuplicateID(String id) {
        SQLiteDatabase myDB = getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from students_details where id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }


    @SuppressLint("Range")
    public ArrayList<String> getPrimarykey() {
        SQLiteDatabase myDb = getReadableDatabase();
        ArrayList<String> getPrimarykey = new ArrayList<>();
        Cursor cursor = myDb.query("students_details", null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            getPrimarykey.add(cursor.getString(cursor.getColumnIndex(ID_1)));
            cursor.moveToNext();
        }


        cursor.close();
        return getPrimarykey;
    }

    public int CountStudent() {

        SQLiteDatabase myDB = getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT COUNT(*) FROM students_details;", null);
        cursor.moveToFirst();
        Integer count = cursor.getInt(0);
        cursor.close();
        myDB.close();

        return count;
    }


    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select id,NAME,DEPARTMENT from students_details", null);
        return cursor;
    }

    public Cursor searchData(String studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM students_details WHERE id=?";
        Cursor cursor = db.rawQuery(query, new String[]{studentId});
        return cursor;
    }

    public Cursor searchMarks(String studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM marks_details WHERE id=?";
        Cursor cursor = db.rawQuery(query, new String[]{studentId});
        return cursor;
    }

    public void deleteData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM  students_details;");

    }
}