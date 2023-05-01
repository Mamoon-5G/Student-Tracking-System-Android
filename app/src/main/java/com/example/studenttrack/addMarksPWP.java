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

public class addMarksPWP extends AppCompatActivity {

    public DBhelper db;
    public EditText getMarksPWP1,getMarksPWP2,getMarksTheoryPWP,PWPAttendence;
    public Button submitPWPmarks;
    public TextView id,name;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marks_pwp);
        Bundle i = getIntent().getExtras();
        String studentId = i.getString("SelectedStudentID");
        getMarksPWP1 = (EditText) findViewById(R.id.getmarks_pwp1);
        getMarksPWP2 = (EditText) findViewById(R.id.getmarks_pwp2);
        getMarksTheoryPWP = (EditText) findViewById(R.id.getmarks_pwptheory);
        submitPWPmarks = (Button) findViewById(R.id.submitPWPmarks);
        PWPAttendence = (EditText) findViewById(R.id.getattendence_pwptheory);



        db = new DBhelper(this); // Initialize the db object

        id = findViewById(R.id.studentsetIDPWP);
        name = findViewById(R.id.StudentSetNamePWP);
        String dbid_view = studentId;
        displaydata(dbid_view);



        submitPWPmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dbmarksUT1 = getMarksPWP1.getText().toString();
                String dbmarksUT2 = getMarksPWP2.getText().toString();
                String dbmarksTheory = getMarksTheoryPWP.getText().toString();
                String etiAttendence = PWPAttendence.getText().toString();


                String dbid = dbid_view;
                boolean result = db.insertPwpMarks(dbid,dbmarksUT1,dbmarksUT2,dbmarksTheory);
                if(result == true){
                    Toast.makeText(addMarksPWP.this, "Data Entered Successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(addMarksPWP.this, "Not Inserted", Toast.LENGTH_SHORT).show();
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