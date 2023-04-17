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

public class AddAttendence extends AppCompatActivity {

    public EditText insert_attend1,insert_attend2,insert_attend3,insert_attend4;
    public Button finish_insert;
    public TextView StudentIDattend;
    DBhelper Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendence);
        StudentIDattend = findViewById(R.id.AttendenceID);
        Intent i = getIntent();
        String StudentId = i.getStringExtra("text");
        StudentIDattend.setText(StudentId);
        finish_insert = findViewById(R.id.finishData);
        Db = new DBhelper(this);
        finish_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // How to insert the database information in device

                insert_attend1 = findViewById(R.id.attendence1);
                insert_attend2 = findViewById(R.id.attendence2);
                insert_attend3 = findViewById(R.id.attendence3);
                insert_attend4 = findViewById(R.id.attendence4);

                int dbAttendenceID = Integer.parseInt(StudentIDattend.getText().toString());
                String dbAttendance1 = insert_attend1.getText().toString();
                String dbAttendance2 = insert_attend2.getText().toString();
                String dbAttendance3 = insert_attend3.getText().toString();
                String dbAttendance4 = insert_attend4.getText().toString();

                boolean insert = Db.insertAttendence(String.valueOf(dbAttendenceID),dbAttendance1,dbAttendance2,dbAttendance3,dbAttendance4);
                if(insert){
                    Toast.makeText(AddAttendence.this, "Inserted All Data", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddAttendence.this,"Not Inserted",Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(AddAttendence.this,HomeActivity.class);
                startActivity(intent);




            }
        });

    }

}