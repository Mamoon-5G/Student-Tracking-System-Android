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

public class addMarksMAD extends AppCompatActivity {

    public DBhelper db;
    public EditText getMarksMAD1,getMarksMAD2,getMarksTheoryMAD,MADAttendence;
    public Button submitMADmarks;

    public TextView id,name;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marks_mad);
        Bundle i = getIntent().getExtras();
        String studentId = i.getString("SelectedStudentID");
        getMarksMAD1 = (EditText) findViewById(R.id.getmarks_mad1);
        getMarksMAD2 = (EditText) findViewById(R.id.getmarks_mad2);
        getMarksTheoryMAD = (EditText) findViewById(R.id.getmarks_madtheory);
        submitMADmarks = (Button) findViewById(R.id.submitMADmarks);
        MADAttendence = (EditText) findViewById(R.id.getattendence_madtheory);



        db = new DBhelper(this); // Initialize the db object

        id = findViewById(R.id.studentsetIDMAD);
        name = findViewById(R.id.StudentSetNameMAD);
        String dbid_view = studentId;
        displaydata(dbid_view);



        submitMADmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dbmarksUT1 = getMarksMAD1.getText().toString();
                String dbmarksUT2 = getMarksMAD2.getText().toString();
                String dbmarksTheory = getMarksTheoryMAD.getText().toString();
                String madAttendence = MADAttendence.getText().toString();


                String dbid = dbid_view;
                boolean result = db.insertMadMarks(dbid,dbmarksUT1,dbmarksUT2,dbmarksTheory);
                if(result == true){
                    Toast.makeText(addMarksMAD.this, "Data Entered Successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(addMarksMAD.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                }

                boolean addattendence = db.insertMADAttendence(dbid,madAttendence);
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