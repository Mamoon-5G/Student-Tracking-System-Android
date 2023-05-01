package com.example.studenttrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

   Button logout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        logout = findViewById(R.id.Logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });
















        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navbarr);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homemenu:
                        //Intent 1
                        Intent intent_home = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent_home);
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
}