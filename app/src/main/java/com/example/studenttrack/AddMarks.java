package com.example.studenttrack;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddMarks extends AppCompatActivity {

    private TextView Studentkey,StudentID;
    public EditText insert_marks1,insert_marks2,insert_marks3,insert_marks4;
    public Button SubmitMarks;
    public DBhelper db;

//    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marks);

        Studentkey = findViewById(R.id.addMarksStudentID);
        Intent i = getIntent();
        String StudentId = i.getStringExtra("text");
        Studentkey.setText(StudentId);
        SubmitMarks = findViewById(R.id.button_SaveandNext);
        db = new DBhelper(this);
       
        
        SubmitMarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                insert_marks1 = findViewById(R.id.Subject1);
                insert_marks2 = findViewById(R.id.Subject2);
                insert_marks3 = findViewById(R.id.Subject3);
                insert_marks4 = findViewById(R.id.Subject4);

                StudentID = findViewById(R.id.addMarksStudentID);

                
                String dbmarks1 = insert_marks1.getText().toString();
                String dbmarks2 = insert_marks2.getText().toString();
                String dbmarks3 = insert_marks3.getText().toString();
                String dbmarks4 = insert_marks4.getText().toString();
                int dbStudentID = Integer.parseInt(StudentID.getText().toString());
                boolean insert = db.insertMarks(String.valueOf(dbStudentID),dbmarks1,dbmarks2,dbmarks3,dbmarks4);
                if(insert == true){
                    Toast.makeText(AddMarks.this, "Marks Entered Successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddMarks.this, "Marks Entered UnSuccessfully", Toast.LENGTH_SHORT).show();
                }






            OpenAddAttendence();
            }
        });
        
        
        
    }

    private void OpenAddAttendence() {
        Intent intent = new Intent ( AddMarks.this, AddAttendence.class );
        intent.putExtra ( "text", StudentID.getText().toString());
        startActivity(intent);
    }

}