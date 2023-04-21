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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class addMarksETI extends AppCompatActivity {

    public TextView studentID_view;
    public DBhelper db;
    RecyclerView recyclerViewstud;

    public EditText getMarksETI1,getMarksETI2,getMarksTheory;
    public Button submitETImarks;

    ArrayList<String> id,name,department,sem,date,phone;
    ViewAdapter adapter;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marks_eti);
        Bundle i = getIntent().getExtras();
        String studentId = i.getString("SelectedStudentID");
        getMarksETI1 = (EditText) findViewById(R.id.getmarks_eti1);
        getMarksETI2 = (EditText) findViewById(R.id.getmarks_eti2);
        getMarksTheory = (EditText) findViewById(R.id.getmarks_etitheory);
        submitETImarks = (Button) findViewById(R.id.submitETImarks);



        db = new DBhelper(this); // Initialize the db object

        id = new ArrayList<>();
        name = new ArrayList<>();
        department = new ArrayList<>();
        sem = new ArrayList<>();
        date = new ArrayList<>();
        phone = new ArrayList<>();
        recyclerViewstud = findViewById(R.id.RecycleViewStudent_eti);
        adapter = new ViewAdapter(this,id,name,department,sem,date,phone);
        recyclerViewstud.setAdapter(adapter);
        recyclerViewstud.setLayoutManager(new LinearLayoutManager(this));

        String dbid_view = studentId;
        displaydata(dbid_view);



        submitETImarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dbmarksUT1 = getMarksETI1.getText().toString();
                String dbmarksUT2 = getMarksETI2.getText().toString();
                String dbmarksTheory = getMarksETI1.getText().toString();


                String dbid = dbid_view;
                boolean result = db.insertEtiMarks(dbid,dbmarksUT1,dbmarksUT2,dbmarksTheory);
                if(result == true){
                    Toast.makeText(addMarksETI.this, "Data Entered Successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(addMarksETI.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                }



            }
        });

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


}