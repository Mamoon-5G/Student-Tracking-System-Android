package com.example.studenttrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewStudent extends AppCompatActivity {

    public TextView studentID_view;
    public DBhelper db;
    RecyclerView recyclerViewstud;
    Button deleted;
//    AttendenceAdapter adapter2;
    RecyclerView recyclerViewMarks;
    ArrayList<String> idmarks ,marks1,marks2,marks3,marks4;
    ArrayList<String> id,name,department,sem,date,phone;
    ViewAdapter adapter;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        Bundle i = getIntent().getExtras();
        String studentId = i.getString("StudentID");
        studentID_view = findViewById(R.id.StudentID_view);
        deleted = findViewById(R.id.DeleteStudentfinal);
        db = new DBhelper(this); // Initialize the db object

        id = new ArrayList<>();
        name = new ArrayList<>();
        department = new ArrayList<>();
        sem = new ArrayList<>();
        date = new ArrayList<>();
        phone = new ArrayList<>();
//         idmarks = new ArrayList<>();
//         marks1 = new ArrayList<>();
//         marks2 = new ArrayList<>();
//         marks3 = new ArrayList<>();
//         marks4 = new ArrayList<>();
//        recyclerViewMarks = findViewById(R.id.RecycleViewMarks);
//        adapter2 = new AttendenceAdapter(this,idmarks,marks1,marks2,marks3,marks4);
//        recyclerViewMarks.setAdapter(adapter2);
//        recyclerViewMarks.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewstud = findViewById(R.id.RecycleViewStudent);
        adapter = new ViewAdapter(this,id,name,department,sem,date,phone);
        recyclerViewstud.setAdapter(adapter);
        recyclerViewstud.setLayoutManager(new LinearLayoutManager(this));


        studentID_view.setText(studentId);
        deleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dbid_view = studentID_view.getText().toString();
                DeleteUser();
            }
        });

        String dbid_view = studentID_view.getText().toString();
        displaydata(dbid_view);
//        displayMarks(dbid_view);









    }

    private void DeleteUser() {

        db.deleteData();


    }

    private void displaydata(String search) {
        Cursor cursor = db.searchData(String.valueOf(search)); // Pass the studentId parameter
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Entry", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                department.add(cursor.getString(2));
                sem.add(cursor.getString(3));
                date.add(cursor.getString(4));
                phone.add(cursor.getString(5));
            }
            adapter.notifyDataSetChanged(); // Notify the adapter after adding data
        }
    }
//    private void displayMarks(String search){
//        Cursor cursor = db.searchMarks(String.valueOf(search)); // Pass the studentId parameter
//        if (cursor.getCount() == 0) {
//            Toast.makeText(this, "No Entry", Toast.LENGTH_SHORT).show();
//            return;
//        } else {
//            while (cursor.moveToNext()) {
//                idmarks.add(cursor.getString(0));
//                marks1.add(cursor.getString(1));
//                marks2.add(cursor.getString(2));
//                marks3.add(cursor.getString(3));
//                marks4.add(cursor.getString(4));
//            }
//            adapter2.notifyDataSetChanged(); // Notify the adapter after adding data
//        }

//    }

}