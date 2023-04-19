package com.example.studenttrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public Button login;
    public EditText logusername , logpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        //Login Button
        login = (Button)findViewById(R.id.button_login);
        //EditText
        logusername = findViewById(R.id.edittext_username);
        logpassword = findViewById(R.id.edittext_password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenHomePage();
                // Verify the admin

            }
        });
    }
    private void OpenHomePage() {
        String username = logusername.getText().toString();
        String password = logpassword.getText().toString();
        if(username.equals("")||password.equals("")){

            Toast.makeText(LoginActivity.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();

        }else{

            //Static Creditails of admin
            if(username.equals("admin")&&password.equals("1234")){
                Toast.makeText(this, "User Succesfully Login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);

            }else{
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        }

        }
    }
