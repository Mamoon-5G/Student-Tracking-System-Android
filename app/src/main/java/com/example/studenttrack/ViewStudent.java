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
    RecyclerView recyclerViewstud,recyclerViewETI,recyclerViewMAD,recyclerViewWDP,recyclerViewPWP;
    Button deleted;
    ArrayList<String> id,name,department,sem,date,phone;
    ArrayList<String> ut1ETI,ut2ETI,theoryETI;
    ArrayList<String> ut1MAD,ut2MAD,theoryMAD;
    ArrayList<String> ut1WDP,ut2WDP,theoryWDP;
    ArrayList<String> ut1PWP,ut2PWP,theoryPWP;
    ViewAdapter adapter;
    ETIAdapter etiAdapter;

    MADAdapter madAdapter;
    WDPAdapter wdpAdapter;
    PWPAdapter pwpAdapter;
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

        ut1ETI = new ArrayList<>();
        ut2ETI = new ArrayList<>();
        theoryETI = new ArrayList<>();


        ut1MAD = new ArrayList<>();
        ut2MAD = new ArrayList<>();
        theoryMAD = new ArrayList<>();



        ut1WDP = new ArrayList<>();
        ut2WDP = new ArrayList<>();
        theoryWDP = new ArrayList<>();

        ut1PWP = new ArrayList<>();
        ut2PWP = new ArrayList<>();
        theoryPWP = new ArrayList<>();


        recyclerViewstud = findViewById(R.id.RecycleViewStudent);
        recyclerViewETI = findViewById(R.id.RecycleViewETIMarks);
        recyclerViewMAD = findViewById(R.id.RecycleViewMADMarks);
        recyclerViewWDP = findViewById(R.id.RecycleViewWDPMarks);
        recyclerViewPWP = findViewById(R.id.RecycleViewPWPMarks);

        adapter = new ViewAdapter(this,id,name,department,sem,date,phone);
        etiAdapter = new ETIAdapter(this,ut1ETI,ut2ETI,theoryETI);//ETI
        madAdapter = new MADAdapter(this,ut1MAD,ut2MAD,theoryMAD);//MAD
        wdpAdapter = new WDPAdapter(this,ut1WDP,ut2WDP,theoryWDP);//WDP
        pwpAdapter = new PWPAdapter(this,ut1PWP,ut2PWP,theoryPWP);//PWP

        recyclerViewstud.setAdapter(adapter);
        recyclerViewstud.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewETI.setAdapter(etiAdapter);
        recyclerViewETI.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewMAD.setAdapter(madAdapter);
        recyclerViewMAD.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewWDP.setAdapter(wdpAdapter);
        recyclerViewWDP.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewPWP.setAdapter(wdpAdapter);
        recyclerViewPWP.setLayoutManager(new LinearLayoutManager(this));


        studentID_view.setText(studentId);


        String dbid_view = studentID_view.getText().toString();
        displaydata(dbid_view);
        DisplayETI(dbid_view);
        DisplayMAD(dbid_view);
        DisplayWDP(dbid_view);
        DisplayPWP(dbid_view);









    }

    private void DisplayPWP(String search) {
        Cursor cursor = db.searchPWP(String.valueOf(search));
        if (cursor.getCount() == 0) {
            //Set text view
            return;
        } else {
            while (cursor.moveToNext()) {
                ut1PWP.add(cursor.getString(0));
                ut2PWP.add(cursor.getString(1));
                theoryPWP.add(cursor.getString(2));

            }
            adapter.notifyDataSetChanged(); // Notify the adapter after adding data
        }
    }

    private void DisplayWDP(String search) {
        Cursor cursor = db.searchWDP(String.valueOf(search));
        if (cursor.getCount() == 0) {
            //Set text view
            return;
        } else {
            while (cursor.moveToNext()) {
                ut1WDP.add(cursor.getString(0));
                ut2WDP.add(cursor.getString(1));
                theoryWDP.add(cursor.getString(2));

            }
            adapter.notifyDataSetChanged(); // Notify the adapter after adding data
        }
    }

    private void DisplayMAD(String search){
        Cursor cursor = db.searchMAD(String.valueOf(search));
        if (cursor.getCount() == 0) {
            //Set text view
            return;
        } else {
            while (cursor.moveToNext()) {
                ut1MAD.add(cursor.getString(0));
                ut2MAD.add(cursor.getString(1));
                theoryMAD.add(cursor.getString(2));

            }
            adapter.notifyDataSetChanged(); // Notify the adapter after adding data
        }

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

    private void DisplayETI(String search){
        Cursor cursor = db.searchETI(String.valueOf(search));
        if (cursor.getCount() == 0) {
            //Set text view
            return;
        } else {
            while (cursor.moveToNext()) {
                ut1ETI.add(cursor.getString(0));
                ut2ETI.add(cursor.getString(1));
                theoryETI.add(cursor.getString(2));

            }
            adapter.notifyDataSetChanged(); // Notify the adapter after adding data
        }

    }


}