package com.example.studenttrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public Button register,login;
    public EditText logusername , logpassword;

    public LoginHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        //Buttons
        register = findViewById(R.id.register_button);
        login = findViewById(R.id.button_login);
        //EditText
        logusername = findViewById(R.id.edittext_username);
        logpassword = findViewById(R.id.edittext_password);
        DB = new LoginHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenHomePage();

            }


        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenRegister();
            }
        });



    }
    // To Open the Register page for user
    private void OpenRegister() {

        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
    //to Open the new Home page for  user
    private void OpenHomePage() {
        String username = logusername.getText().toString();
        String password = logpassword.getText().toString();
        if(username.equals("")||password.equals("")){

            Toast.makeText(LoginActivity.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();

        }else{
            Boolean checkuserpass = DB.checkuserpass(username,password);
            if(checkuserpass==true){
                Toast.makeText(this, "User Succesfully Login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);

            }
        }

        }
    }
