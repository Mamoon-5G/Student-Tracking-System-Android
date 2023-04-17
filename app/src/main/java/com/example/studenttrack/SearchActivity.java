package com.example.studenttrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    public AutoCompleteTextView autoCompleteTextView;
    public ArrayList<String> getPrimarykey;
    public RecyclerView recyclerView;
    public Button viewStudent;

    public ArrayList<String> studID,studName,studDep;
    MyAdapter adapter_1;

    public DBhelper database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();


        autoCompleteTextView = findViewById(R.id.searchh);
        viewStudent = findViewById(R.id.ViewStudent);




        database = new DBhelper(this);

        getPrimarykey = database.getPrimarykey();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, getPrimarykey);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.searchh);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);


        viewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedStudentId = autoCompleteTextView.getText().toString();
                if (!selectedStudentId.isEmpty()) {
                    Intent i = new Intent(SearchActivity.this, ViewStudent.class);
                    i.putExtra("StudentID", selectedStudentId);
                    startActivity(i);
                } else {
                    Toast.makeText(SearchActivity.this, "Please select a student ID", Toast.LENGTH_SHORT).show();
                }
            }
        });





        studID = new ArrayList<>();
        studName = new ArrayList<>();
        studDep = new ArrayList<>();
        recyclerView = findViewById(R.id.RecyleBolte);
        adapter_1 = new MyAdapter(this,studID,studName,studDep);
        recyclerView.setAdapter(adapter_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();




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
                        startActivity(intent3);
                        break;

                }
                return true;
            }
        });
    }

    private void displaydata() {
        Cursor cursor = database.getdata();
        if (cursor.getCount() == 0) {
            Toast.makeText(SearchActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                studID.add(cursor.getString(0));
                studName.add(cursor.getString(1));
                studDep.add(cursor.getString(2));

            }

        }
    }
}