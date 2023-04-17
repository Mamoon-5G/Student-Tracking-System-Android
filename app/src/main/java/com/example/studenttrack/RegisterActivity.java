package com.example.studenttrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    public EditText rname,rusername,rpassword,rrepassword;
    public Button register;
    public LoginHelper DB;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        rname = findViewById(R.id.edittext_name);
        rusername = findViewById(R.id.edittext_username);
        rpassword = findViewById(R.id.edittext_password);
        rrepassword = findViewById(R.id.edittext_repassword);

        //Button
        //21482 ASHISH
        register = findViewById(R.id.button_register);
        DB = new LoginHelper(this);

        //Performing Actions Listeners

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = rname.getText().toString();
                String username = rusername.getText().toString();
                String password = rpassword.getText().toString();
                String repassword = rrepassword.getText().toString();

                if(name.equals("")||username.equals("")||password.equals("")||repassword.equals("")){

                    Toast.makeText(RegisterActivity.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();

                }
                else{
                    if(password.equals(repassword)){
                      Boolean checkUser = DB.checkUser(username);
                      if(checkUser==false){
                          boolean insert = DB.insertData(name,username,password);
                          if(insert==true){
                              Toast.makeText(RegisterActivity.this, "User registered Succesfully", Toast.LENGTH_LONG).show();
                              finish();
                          }else{
                              Toast.makeText(RegisterActivity.this, "User Registration Failed", Toast.LENGTH_SHORT).show();
                          }
                      }else{
                          Toast.makeText(RegisterActivity.this, "User is Already Exists", Toast.LENGTH_SHORT).show();
                      }
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Password Doesn't Matches", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });



    }
}