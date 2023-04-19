package com.example.studenttrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Date;



public class HomeActivity extends AppCompatActivity {

    private TextView text;
    private TextView student_counts;
    private DBhelper DB;
    private ImageButton addstudent;
    public ImageButton subjects;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        //Accesing Views
        text = findViewById(R.id.Greet);
        addstudent = findViewById(R.id.addStudent);
        subjects = (ImageButton)findViewById(R.id.subjects_button);
        //Objects
        DB = new DBhelper(this);
         SetGreeting(); // Sets the Greet message to admin
          setStudent(); // Sets the Total Students to dashboard
        Intent intentlog = getIntent();
        String sendusername = (String)intentlog.getExtras().getString("username");
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navbarr);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homemenu:
                        //Intent 1
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.searchmenu:
                        Intent intent2 = new Intent(getApplicationContext(),SearchActivity.class);
                        startActivity(intent2);
                        //Intent 2
                        break;
                    case R.id.profilemenu:
                        //Intent 3
                        Intent intent3 = new Intent(getApplicationContext(),ProfileActivity.class);
                        intent3.putExtra("username",sendusername);
                        startActivity(intent3);

                        break;
                }
                return true;
            }
        });

        addstudent.setOnClickListener(v -> OpenAddStudent());

        subjects.setOnClickListener(v -> OpenSubjects());
        //Admins Account Counting
    }

    private void OpenSubjects() {

        Intent intent = new Intent(HomeActivity.this,Subjects.class);
        startActivity(intent);

    }

    private void SetGreeting() {

        Date dt = new Date();


        //Greet Message for user
        int hours = dt.getHours();
        String greeting = null;
        if(hours>=1 && hours<=12){
            greeting = "Good Morning";
        } else if(hours>=12 && hours<=16){
            greeting = "Good Afternoon";
        } else if(hours>=16 && hours<=21){
            greeting = "Good Evening";
        } else if(hours>=21 && hours<=24){
            greeting = "Good Night";
        }


        //Setting text to user's Home page
        text.setText(greeting);
    }

    private void OpenAddStudent() {
        Intent intent = new Intent(HomeActivity.this , AddStudent.class);
        startActivity(intent);
    }


    private void setStudent(){
        student_counts = findViewById(R.id.students_data);
        int count = DB.CountStudent();
        String stringValue = String.valueOf(count);
        student_counts.setText("Total Students : 0"+stringValue);

    }



}
