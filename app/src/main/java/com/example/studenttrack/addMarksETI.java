package com.example.studenttrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class addMarksETI extends AppCompatActivity {

    public DBhelper db;
    public EditText getMarksETI1,getMarksETI2,getMarksTheoryETI,ETIAttendence;
    public Button submitETImarks;

    public TextView id,name;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marks_eti);
        Bundle i = getIntent().getExtras();
        String studentId = i.getString("SelectedStudentID");
        getMarksETI1 = (EditText) findViewById(R.id.getmarks_eti1);
        getMarksETI2 = (EditText) findViewById(R.id.getmarks_eti2);
        getMarksTheoryETI = (EditText) findViewById(R.id.getmarks_etitheory);
        submitETImarks = (Button) findViewById(R.id.submitETImarks);
        ETIAttendence = (EditText) findViewById(R.id.getattendence_etitheory);



        db = new DBhelper(this); // Initialize the db object

        id = findViewById(R.id.studentsetIDETI);
        name = findViewById(R.id.StudentSetNameETI);
        String dbid_view = studentId;
        displaydata(dbid_view);



        submitETImarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dbmarksUT1 = getMarksETI1.getText().toString();
                String dbmarksUT2 = getMarksETI2.getText().toString();
                String dbmarksTheory = getMarksTheoryETI.getText().toString();
                String etiAttendence = ETIAttendence.getText().toString();


                String dbid = dbid_view;
                boolean result = db.insertEtiMarks(dbid,dbmarksUT1,dbmarksUT2,dbmarksTheory);
                if(result == true){
                    Toast.makeText(addMarksETI.this, "Data Entered Successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(addMarksETI.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                }

                boolean addattendence = db.insertETIAttendence(dbid,etiAttendence);
                if(addattendence == true){
                    //Nothing
                }else{
                    //Nothing
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
                id.setText(cursor.getString(0));
                name.setText(cursor.getString(1));

            }

        }
    }


}